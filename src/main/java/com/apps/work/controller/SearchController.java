package com.apps.work.controller;

import com.apps.work.model.Page;
import com.apps.work.service.AppService;
import com.apps.work.service.PageService;
import com.apps.work.util.AppConstants;
import net.minidev.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private AppService appService;
    //@Secured("USER")
    @RequestMapping(value = "/s", method = RequestMethod.GET)
    public String renderPageList(ModelMap model, @RequestParam String str, @RequestParam(required = false) String page) {
        List<Map<String, String>> listPosts = new ArrayList();
        try {
            Analyzer analyzer = new StandardAnalyzer();
            Path indexPath = Paths.get(AppConstants.INDEXING_DIR);
            Directory directory = FSDirectory.open(indexPath);
            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);
            MultiFieldQueryParser parser = new MultiFieldQueryParser(
                    new String[] {AppConstants.TITLE, AppConstants.BODY}, analyzer);
            Query query = parser.parse(str);
            ScoreDoc[] hits = isearcher.search(query, ireader.numDocs()).scoreDocs;
            int offset, count = 0;

            if(page == null || page.equals("")) {
                page = "1";
            }
            int currentPage = Integer.parseInt(page);
            offset = (currentPage - 1) * AppConstants.RECORDS_PER_PAGE;
            count = Math.min(hits.length - offset, AppConstants.RECORDS_PER_PAGE);

            for(int i = offset; i < count + offset; i++) {
                ScoreDoc hit = hits[i];
                Map<String, String> posts = new HashMap<>();
                Document hitDoc = isearcher.doc(hit.doc);
                posts.put(AppConstants.ID, hitDoc.get(AppConstants.ID));
                posts.put(AppConstants.SEO_URI, hitDoc.get(AppConstants.SEO_URI));
                posts.put(AppConstants.TITLE, hitDoc.get(AppConstants.TITLE));
                posts.put(AppConstants.BODY, hitDoc.get(AppConstants.BODY));
                posts.put(AppConstants.AUTHOR, hitDoc.get(AppConstants.AUTHOR));
                posts.put(AppConstants.DATE, hitDoc.get(AppConstants.DATE));
                listPosts.add(posts);
            }

            ireader.close();
            directory.close();
            float totalPages = hits.length / AppConstants.RECORDS_PER_PAGE;
            int prevPage = 1;
            int nextPage = 1;
            if(currentPage > 1) {
                prevPage = currentPage - 1;
            }
            if(totalPages > currentPage) {
                nextPage = currentPage + 1;
            }
            model.addAttribute("articles", listPosts);
            model.addAttribute("prevPage", prevPage);
            model.addAttribute("nextPage", nextPage);
            model.addAttribute("str", str);
            model.addAttribute("pagesMain", appService.getPageTitles());
            model.addAttribute("catMain", appService.getCategoryList());
        } catch(Exception e) {
            logger.error(e);
        }
        return "search";
    }

}

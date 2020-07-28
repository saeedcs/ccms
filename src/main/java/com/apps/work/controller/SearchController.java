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

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
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
            ScoreDoc[] hits = isearcher.search(query, AppConstants.RECORDS_PER_PAGE).scoreDocs;
            int offset = 0;
            if(page != null && !page.equals("")) {
                offset = Integer.parseInt(page) * AppConstants.RECORDS_PER_PAGE;
            }
            /*for (ScoreDoc hit : hits) {
                Map<String, String> posts = new HashMap<>();
                Document hitDoc = isearcher.doc(hit.doc);
                posts.put(AppConstants.TITLE, hitDoc.get(AppConstants.TITLE));
                posts.put(AppConstants.BODY, hitDoc.get(AppConstants.BODY));
                listPosts.add(posts);
            }*/
            int count = Math.min(hits.length - offset, AppConstants.RECORDS_PER_PAGE);
            for(int i = count; i < (count + AppConstants.RECORDS_PER_PAGE); i++) {
                ScoreDoc hit = hits[i];
                Map<String, String> posts = new HashMap<>();
                Document hitDoc = isearcher.doc(hit.doc);
                posts.put(AppConstants.TITLE, hitDoc.get(AppConstants.TITLE));
                posts.put(AppConstants.BODY, hitDoc.get(AppConstants.BODY));
                listPosts.add(posts);
            }
            ireader.close();
            directory.close();

            model.addAttribute("articles", listPosts);
        } catch(Exception e) {
            logger.error(e);
        }
        return "search";
    }

}

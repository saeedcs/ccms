package com.apps.work.controller;

import com.apps.work.model.Article;
import com.apps.work.model.Comment;
import com.apps.work.service.AppService;
import com.apps.work.service.ArticleService;
import net.minidev.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AppService appService;

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderArticleList(ModelMap model) {
        try {
            model.addAttribute("articles", articleService.getArticleList());
            model.addAttribute("pagesMain", appService.getPageTitles());
        } catch(Exception e) {
            logger.error(e);
        }
        return "article";
    }

    //@Secured("USER")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewArticle(ModelMap model, @RequestParam String id) {
        try {
            Optional<Article> articleOptional = articleService.getArticle(Integer.parseInt(id));
            if (articleOptional.isPresent()) {
                model.addAttribute("article", articleOptional.get());
            }
        } catch (Exception e) {
            logger.error(e);
        }
        model.addAttribute("pagesMain", appService.getPageTitles());
        return "view-article";
    }

    //@Secured("USER")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createArticle(ModelMap model, @RequestParam(required=false) String id) {
        try {
            if(id != null && !id.equals("")) {
                Optional<Article> articleOptional = articleService.getArticle(Integer.parseInt(id));
                if (articleOptional.isPresent()) {
                    model.addAttribute("article", articleOptional.get());
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        model.addAttribute("pagesMain", appService.getPageTitles());
        return "create-article";
    }

    //@Secured("USER")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> postCreateArticle(ModelMap model, @RequestBody(required = false) Article article) {
        JSONObject result = new JSONObject();
        try {

            articleService.createArticle(article);

        } catch (Exception e) {
            logger.error(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //@Secured("USER")
    @RequestMapping(value = "/add-comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> addComment(ModelMap model, @RequestParam String comment,
                                                 @RequestParam String articleId) {
        JSONObject result = new JSONObject();
        try {
            articleService.addComment(comment, articleId);

        } catch (Exception e) {
            logger.error(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    //@Secured("USER")
    @RequestMapping(value = "/check-seo-uri", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> checkSeoUri(ModelMap model, @RequestParam String seoUri, @RequestParam(required = false) String id) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        try {
            if(seoUri != null && !seoUri.equals("")) {
                if(id == null || id.equals("")) {
                    id = new String("0");
                }
                Optional<Article> articleOptional = Optional.ofNullable(articleService.findBySeoUriAndIdNot(seoUri, id));
                if (articleOptional.isPresent()) {
                    resultMap.put("exists", true);
                    return resultMap;
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return resultMap;
    }

    //@Secured("USER")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> postDeleteArticle(@RequestParam String id) {
        JSONObject result = new JSONObject();
        try {
            articleService.deleteArticle(id);
            result.put("deleted", true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}

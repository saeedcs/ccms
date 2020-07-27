package com.apps.work.controller;

import com.apps.work.model.Article;
import com.apps.work.model.Comment;
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

import java.util.Optional;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ArticleService articleService;

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderArticleList(ModelMap model) {
        try {
            model.addAttribute("articles", articleService.getArticleList());
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
        return "create-article";
    }

    //@Secured("USER")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> postCreateArticle(ModelMap model, @RequestBody(required = false) Article article) {
        JSONObject result = new JSONObject();
        try {



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
}

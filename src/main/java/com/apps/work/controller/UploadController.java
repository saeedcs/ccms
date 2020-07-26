package com.apps.work.controller;

import com.apps.work.model.ForgetPassword;
import com.apps.work.model.Role;
import com.apps.work.model.User;
import com.apps.work.service.AuthService;
import com.apps.work.util.AppConstants;
import com.apps.work.util.CcmsUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    private Log logger = LogFactory.getLog(this.getClass());

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String uploadMain(ModelMap model) {
        try {

        } catch(Exception e) {
            logger.error(e);
        }
        return "upload";
    }

    //@Secured("USER")
    @RequestMapping(value = "/file-upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> fileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            System.out.println("Uploaded filename is " + file.getOriginalFilename());
            String newFilename = CcmsUtil.formatUploadedFilename(file.getOriginalFilename());
            file.transferTo(Paths.get(AppConstants.UPLOAD_FOLDER + newFilename));
            resultMap.put(AppConstants.LOCATION_KEY, "/upload/file?filename=" + newFilename);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch(Exception e) {
            logger.error(e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@Secured("USER")
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public void file(HttpServletResponse response, @RequestParam String filename) {
        try {
            Path file = Paths.get(AppConstants.UPLOAD_FOLDER + filename);
            response.setContentType(Files.probeContentType(file));
            response.setContentLength(((Long)Files.size(file)).intValue());
            IOUtils.copy(Files.newInputStream(file), response.getOutputStream());
        } catch(Exception e) {
            logger.error(e);
        }
    }

}

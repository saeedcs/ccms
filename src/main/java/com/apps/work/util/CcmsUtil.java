package com.apps.work.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.lucene.index.IndexWriterConfig;

public class CcmsUtil {

    public static boolean isValidEmail(String email) {
        if(email != null) {
            return Pattern.matches(AppConstants.REGEX, email);
        }
        return false;
    }

    public static boolean isValidPasswords(String password, String confirmPassword) {
        if(password != null && confirmPassword != null) {
            if(password.length() >= 6 && password.equals(confirmPassword)) {
                return true;
            }
        }
        return false;
    }

    public static String formatUploadedFilename(String originalFilename) {
        String[] filenameArr = originalFilename.split("\\.");
        return filenameArr[0] + Calendar.getInstance().getTimeInMillis() + "." + filenameArr[1];
    }

    public static void makeSearchIndex(String id, String seoUri, String title, String body, String author, String date) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        Path indexPath = Paths.get(AppConstants.INDEXING_DIR);
        Directory directory = FSDirectory.open(indexPath);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        doc.add(new Field(AppConstants.ID, id, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.SEO_URI, seoUri, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.TITLE, title, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.BODY, body, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.AUTHOR, author, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.DATE, date, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();
    }

    public static void updateSearchIndex(String id, String seoUri, String title, String body, String author, String date) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        Path indexPath = Paths.get(AppConstants.INDEXING_DIR);
        Directory directory = FSDirectory.open(indexPath);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        doc.add(new Field(AppConstants.ID, id, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.SEO_URI, seoUri, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.TITLE, title, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.BODY, body, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.AUTHOR, author, TextField.TYPE_STORED));
        doc.add(new Field(AppConstants.DATE, date, TextField.TYPE_STORED));
        iwriter.updateDocument(new Term(AppConstants.ID, id), doc);
        iwriter.close();
    }
}

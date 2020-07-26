package com.apps.work.util;

import java.util.Calendar;
import java.util.regex.Pattern;

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
}

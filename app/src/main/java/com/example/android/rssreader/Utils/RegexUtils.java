package com.example.android.rssreader.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    private static final String EMPTY_STRING = "";
    private static final String IMAGE_TAG = "<img src=([\\w\\W]+?)/>";
    private static final String IMAGE_TAG_2 = "<img src=([\\w\\W]+?)>";

    public static String getImageFromString(String textWithImageString) {
        final Pattern pattern = Pattern.compile(IMAGE_TAG, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(textWithImageString.trim());
        if (matcher.find()) {
            return matcher.group(1).replaceAll("\"", "");
        }
        final Pattern pattern2 = Pattern.compile(IMAGE_TAG_2, Pattern.DOTALL);
        final Matcher matcher2 = pattern2.matcher(textWithImageString.trim());
        if (matcher2.find()) {
            return matcher2.group(1).replaceAll("\"", "");
        }
        return EMPTY_STRING;
    }

}

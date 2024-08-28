package com.site.blog.my.core.util;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;

public class PatternUtil {

    /**
     * 判断是否是有效的邮箱地址
     *
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        // 使用 ESAPI 验证邮箱地址
        try {
            return ESAPI.validator().isValidInput("Email", emailStr, "Email", 255, false);
        } catch (IntrusionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证只包含中英文和数字的字符串
     *
     * @param keyword
     * @return
     */
    public static Boolean validKeyword(String keyword) {
        // 使用 ESAPI 验证关键词
        try {
            return ESAPI.validator().isValidInput("Keyword", keyword, "Keyword", 255, false);
        } catch (IntrusionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否是有效的URL
     *
     * @param urlString
     * @return
     */
    public static boolean isURL(String urlString) {
        // 使用 ESAPI 验证URL
        try {
            return ESAPI.validator().isValidInput("URL", urlString, "URL", 2000, false);
        } catch (IntrusionException e) {
            throw new RuntimeException(e);
        }
    }
}

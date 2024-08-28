package com.site.blog.my.core.util;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.springframework.util.StringUtils;

import java.net.URI;

public class MyBlogUtils {

    public static URI getHost(URI uri) {
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

    private static final Encoder encoder = ESAPI.encoder();

    public static String cleanString(String value) {
        if (!StringUtils.hasText(value)) {
            return "";
        }

        // 对字符串进行HTML编码，防止XSS攻击
        value = encoder.encodeForHTML(value);

        // JavaScript 编码
        // value = encoder.encodeForJavaScript(value);

        // URL 编码
        // value = encoder.encodeForURL(value);

        return value;
    }
}

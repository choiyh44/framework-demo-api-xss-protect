/**
 * 
 */
package com.x2bee.demo.base.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.LookupTranslator;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2022. 4. 19.
 *
 */
public class XssProtectUtils {
    private static CharSequenceTranslator translator;
    
    static {
        final Map<CharSequence, CharSequence> initialMap = new HashMap<>();
        initialMap.put("<", "&lt;");
        initialMap.put(">", "&gt;");
        initialMap.put("&", "&amp;");
        initialMap.put("\"", "&quot;");
        initialMap.put("#", "&#35;");
        initialMap.put("\'", "&#39;");
        Map<CharSequence, CharSequence> CUSTOM_CHARS_ESCAPE = Collections.unmodifiableMap(initialMap);
        translator = new LookupTranslator(CUSTOM_CHARS_ESCAPE);
    }
    
    public static String escapeHtml(String text) {
        return translator.translate(text);
    }
    
    public static boolean matchesHtml(String text) {
        return text.matches(".*[<|>|&|\"|#|'].*");
    }
}

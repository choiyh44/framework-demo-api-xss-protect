package com.x2bee.demo.base.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.LookupTranslator;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.x2bee.demo.base.util.RequestUtils;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2021. 9. 8.
 *
 */
public class HTMLCharacterEscapes extends CharacterEscapes {
    private static final long serialVersionUID = 3183064165511484165L;

    private final int[] asciiEscapes;

    private final CharSequenceTranslator translator;

    public HTMLCharacterEscapes() {

        // 1. XSS 방지 처리할 특수 문자 지정
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;

        // 2. XSS 방지 처리 특수 문자 인코딩 값 지정
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

    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
        Boolean noXssFilter = RequestUtils.getAttribute("NoXssFilter");
        if (noXssFilter != null && noXssFilter) {
            return new SerializedString(Character.toString((char) ch));
        }
        else {
            return new SerializedString(translator.translate(Character.toString((char) ch)));
            //return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char) ch)));
        }

    }
    
}

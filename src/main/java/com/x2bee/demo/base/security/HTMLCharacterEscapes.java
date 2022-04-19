package com.x2bee.demo.base.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
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
        asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;

        // 2. XSS 방지 처리 특수 문자 인코딩 값 지정
        // 여기에서 커스터마이징 가능
        final Map<CharSequence, CharSequence> initialMap = new HashMap<>();
//        initialMap.put("(", "&#40;");
//        initialMap.put(")", "&#41;");
//        initialMap.put("#", "&#35;");
//        initialMap.put("\'", "&#39;");
        Map<CharSequence, CharSequence> CUSTOM_CHARS_ESCAPE = Collections.unmodifiableMap(initialMap);
        translator = new AggregateTranslator(
            new LookupTranslator(EntityArrays.BASIC_ESCAPE),  // <, >, &, " 는 여기에 포함됨
            new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE),
            new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE),
            // 여기에서 커스터마이징 가능
            new LookupTranslator(CUSTOM_CHARS_ESCAPE)
        );
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
            return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char) ch)));
            // 참고 - 커스터마이징이 필요하다면 아래 메서드를 사용한다.
            //return new SerializedString(translator.translate(Character.toString((char) ch)));
        }

    }
    
}

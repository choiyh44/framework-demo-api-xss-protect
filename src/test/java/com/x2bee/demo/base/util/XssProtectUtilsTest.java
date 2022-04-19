/**
 * 
 */
package com.x2bee.demo.base.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2022. 4. 19.
 *
 */
@Slf4j
class XssProtectUtilsTest {

    @Test
    void excapeHtml() {
        String test = "11<22>33#44'55&66\"77";
        String result = "11&lt;22&gt;33&#35;44&#39;55&amp;66&quot;77";
        log.debug("XssProtectUtils.escapeHtml(): {}", XssProtectUtils.escapeHtml(test));
        log.debug("StringEscapeUtils.escapeHtml4(): {}", StringEscapeUtils.escapeHtml4(test));
        assertEquals(result, XssProtectUtils.escapeHtml(test));
    }

    @Test
    void matchesHtml_lt_true() {
        String test = "11<22>33#44'55";
        assertTrue(XssProtectUtils.matchesHtml(test));
    }

    @Test
    void matchesHtml_gt_true() {
        String test = "1122>33#44'55";
        assertTrue(XssProtectUtils.matchesHtml(test));
    }

    @Test
    void matchesHtml_hash_true() {
        String test = "112233#44'55";
        assertTrue(XssProtectUtils.matchesHtml(test));
    }

    @Test
    void matchesHtml_single_quote_true() {
        String test = "a<223344'55";
        assertTrue(XssProtectUtils.matchesHtml(test));
    }

    @Test
    void matchesHtml_amp_true() {
        String test = "a&22334455";
        assertTrue(XssProtectUtils.matchesHtml(test));
    }

    @Test
    void matchesHtml_quot_true() {
        String test = "a\"22334455";
        assertTrue(XssProtectUtils.matchesHtml(test));
    }

    @Test
    void matchesHtml_false() {
        String test = "1122334455";
        assertTrue(!XssProtectUtils.matchesHtml(test));
    }

}

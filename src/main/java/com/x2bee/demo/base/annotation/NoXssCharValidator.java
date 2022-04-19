/**
 * 
 */
package com.x2bee.demo.base.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.x2bee.demo.base.util.XssProtectUtils;

/**
 * Html 문자열(< > & " ' # ')이 포함되지 않았음을 확인하는 Validator이다.
 * @author choiyh44
 * @version 1.0
 * @since 2022. 4. 19.
 *
 */
public class NoXssCharValidator implements ConstraintValidator<NoXssChar, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !XssProtectUtils.matchesHtml(value);
    }
}

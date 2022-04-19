/**
 * 
 */
package com.x2bee.demo.app.controller;

import com.x2bee.demo.base.annotation.NoXssChar;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2021. 9. 8.
 *
 */
@Getter
@Setter
@ToString
public class SampleVo {
    private String name;
    @NoXssChar
    private String description;
}

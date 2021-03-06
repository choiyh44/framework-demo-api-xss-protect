/**
 * 
 */
package com.x2bee.demo.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.x2bee.demo.base.annotation.NoXssFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2021. 9. 8.
 *
 */
@RestController
@RequestMapping("/sample")
@Slf4j
public class SampleController {
    @PostMapping("")
    public SampleVo getSamples(SampleVo sample) {
        log.info("Sample: {}", sample);
        
        return sample;
    }

    @PostMapping("/noXssFilter")
    @NoXssFilter
    public SampleVo getSamplesWithNoXssFilter(SampleVo sample) {
        log.info("Sample: {}", sample);
        
        return sample;
    }
    
}

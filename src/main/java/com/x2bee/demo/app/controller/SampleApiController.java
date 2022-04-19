/**
 * 
 */
package com.x2bee.demo.app.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/samples")
@Slf4j
public class SampleApiController {
    @PostMapping("")
    public SampleVo getSamples(@RequestBody SampleVo sample) {
        log.info("Sample: {}", sample);
        
        return sample;
    }

    @PostMapping("/noXssFilter")
    @NoXssFilter
    public SampleVo getSamplesWithNoXssFilter(@RequestBody SampleVo sample) {
        log.info("Sample: {}", sample);
        
        return sample;
    }

    @PostMapping("/noXssValidator")
    public SampleVo getSamplesWithNoXssValidator(@RequestBody @Valid SampleVo sample) {
        log.info("Sample: {}", sample);
        
        return sample;
    }

    
    
}

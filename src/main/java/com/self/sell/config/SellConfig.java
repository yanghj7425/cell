package com.self.sell.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.Collections;

@Configuration
public class SellConfig {

//    public HttpMessageConverters fastJsonHttpMessageConvert() {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        fastJsonHttpMessageConverter.getFastJsonConfig().setDateFormat("yyyy-MM-dd");
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(
//                Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
//
//
//        return new HttpMessageConverters(fastJsonHttpMessageConverter);
//
//    }


}

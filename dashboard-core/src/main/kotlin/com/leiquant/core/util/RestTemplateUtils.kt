package com.leiquant.core.util

import com.alibaba.fastjson.JSONObject
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate


object RestTemplateUtils {
  fun getHttp(url: String, params: JSONObject, connecTimeout: Int, readTimeout: Int, retryCnt: Int) {
    val requestFactory = SimpleClientHttpRequestFactory()
    requestFactory.setConnectTimeout(connecTimeout);
    requestFactory.setReadTimeout(readTimeout)

    val restTemplate: RestTemplate  = RestTemplate(requestFactory)
    restTemplate.messageConverters.set(1, StringHttpMessageConverter(Charsets.UTF_8))
//    restTemplate.errorHandler =
  }
}
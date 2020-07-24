package com.leiquant.core.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RestTemplateUtil {
  @Autowired
  lateinit var restTemplate: RestTemplate

  fun get(url: String): String {
    return restTemplate.getForObject(url, String::class.java) as String
  }
}
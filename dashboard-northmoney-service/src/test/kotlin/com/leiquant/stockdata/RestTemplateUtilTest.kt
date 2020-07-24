package com.leiquant.stockdata

import com.leiquant.core.config.RestTemplateUtil
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RestTemplateUtilTest {

  @Autowired
  lateinit var restTemplateUtil: RestTemplateUtil

  @Test
  fun testGet() {
    var url = "https://wanandroid.com/wxarticle/chapters/json"
    restTemplateUtil.get(url)
  }
}
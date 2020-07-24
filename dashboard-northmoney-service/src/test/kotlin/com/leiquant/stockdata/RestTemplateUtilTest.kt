package com.leiquant.stockdata

import com.leiquant.core.config.RestTemplateUtil
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RestTemplateUtilTest {

  @Test
  fun testGet() {
    var url = "https://wanandroid.com/wxarticle/chapters/json"
    RestTemplateUtil.get(url)
  }
}
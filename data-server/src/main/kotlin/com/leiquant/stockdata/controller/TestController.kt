package com.leiquant.stockdata.controller

import com.leiquant.stockdata.models.ApiResult
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TestController {
    @GetMapping("/hello")
    @ResponseBody
    fun hellMethod(): ApiResult {
        return ApiResult("Hello World")
    }
}
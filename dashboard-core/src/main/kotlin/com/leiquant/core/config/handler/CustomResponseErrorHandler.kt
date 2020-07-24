package com.leiquant.core.config.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.DefaultResponseErrorHandler

@Component
class CustomResponseErrorHandler : DefaultResponseErrorHandler() {

  private val log: Logger = LoggerFactory.getLogger(this.javaClass)

  override fun hasError(response: ClientHttpResponse): Boolean {
    val statusCode: HttpStatus = response.statusCode
    if (statusCode.is3xxRedirection) {
      return true;
    }
    return super.hasError(response);
  }

  override fun handleError(response: ClientHttpResponse) {
    val statusCode = response.statusCode
    if (statusCode.is3xxRedirection) {
      log.info("########30X错误，需要重定向！##########");
      return;
    }
    super.handleError(response);
  }
}
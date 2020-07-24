package com.leiquant.core.config.interceptors

import org.slf4j.LoggerFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component

@Component
class TrackLogClientHttpRequestInterceptor : ClientHttpRequestInterceptor {
  val log = LoggerFactory.getLogger(this.javaClass)
  override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
    trackRequest(request, body);
    var httpResponse = execution.execute(request, body);
    trackResponse(httpResponse);
    return httpResponse;
  }

  //  @Exception(IOException::class)
  private fun trackResponse(httpResponse: ClientHttpResponse) {
    log.info("============================response begin==========================================");
    log.info("Status code  : {}", httpResponse.statusCode);
    log.info("Status text  : {}", httpResponse.statusText);
    log.info("Headers      : {}", httpResponse.headers);
    log.info("=======================response end=================================================");
  }

  private fun trackRequest(request: HttpRequest, body: ByteArray) {
    log.info("======= request begin ========");
    log.info("uri : {}", request.getURI());
    log.info("method : {}", request.getMethod());
    log.info("headers : {}", request.getHeaders());
    log.info("request body : {}", String(body, Charsets.UTF_8));
    log.info("======= request end ========");
  }
}
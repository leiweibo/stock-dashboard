package com.leiquant.core.config.interceptors

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component


@Component
class HeadClientHttpRequestInterceptor : ClientHttpRequestInterceptor {
  val log: Logger = LoggerFactory.getLogger(this.javaClass)

  override fun intercept(httpRequest: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
    log.info("#####head handle########");
    val headers: HttpHeaders = httpRequest.headers
    headers.add("Accept", "application/json")
    headers.add("Accept-Encoding", "gzip")
    headers.add("Content-Encoding", "UTF-8")
    headers.add("Content-Type", "application/json; charset=UTF-8")

    val response: ClientHttpResponse = execution.execute(httpRequest, body)
    val headersResponse = response.headers
    headersResponse.add("Accept", "application/json")
    return response
  }

}
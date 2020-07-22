package com.leiquant.core.config

import com.leiquant.core.config.convert.CustomMappingJackson2HttpMessageConverter
import com.leiquant.core.config.handler.CustomResponseErrorHandler
import com.leiquant.core.config.interceptors.TrackLogClientHttpRequestInterceptor
import org.apache.http.client.HttpClient
import org.apache.http.client.config.RequestConfig
import org.apache.http.config.Registry
import org.apache.http.config.RegistryBuilder
import org.apache.http.conn.socket.ConnectionSocketFactory
import org.apache.http.conn.socket.PlainConnectionSocketFactory
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate


@Configuration
class RestTemplateConfig {

  @Value("\${http.maxTotal}")
  private var maxTotal: Int? = null

  @Value("\${http.defaultMaxPerRoute}")
  private var defaultMaxPerRoute: Int? = null

  @Value("\${http.connectTimeout}")
  private val connectTimeout: Int? = null

  @Value("\${http.connectionRequestTimeout}")
  private val connectionRequestTimeout: Int? = null

  @Value("\${http.socketTimeout}")
  private val socketTimeout: Int? = null

  @Value("\${http.staleConnectionCheckEnabled}")
  private val staleConnectionCheckEnabled = false

  @Value("\${http.validateAfterInactivity}")
  private val validateAfterInactivity: Int? = null

  @Autowired
  private lateinit var trackLogInterceptor: TrackLogClientHttpRequestInterceptor

  @Autowired
  private lateinit var requestInterceptor: ClientHttpRequestInterceptor

  @Autowired
  private lateinit var errorHandler: CustomResponseErrorHandler

  @Autowired
  private lateinit var converter: CustomMappingJackson2HttpMessageConverter

  @Bean
  fun restTemplate(simpleClientHttpRequestFactory: ClientHttpRequestFactory): RestTemplate {
    var template = RestTemplate(httpRequestFactory())
    var interceptors: MutableList<ClientHttpRequestInterceptor> = mutableListOf()
    interceptors.add(requestInterceptor)
    interceptors.add(trackLogInterceptor)
    template.interceptors = interceptors
    template.requestFactory = simpleClientHttpRequestFactory
    template.errorHandler = errorHandler

    val messageConverters = template.messageConverters
    messageConverters.add(CustomMappingJackson2HttpMessageConverter())
    template.messageConverters = messageConverters

    return template
  }

  @Bean
  fun httpRequestFactory(): ClientHttpRequestFactory {
    return HttpComponentsClientHttpRequestFactory(httpClient())
  }

  @Bean
  fun httpClient(): HttpClient {
    val registry: Registry<ConnectionSocketFactory> = RegistryBuilder.create<ConnectionSocketFactory>()
        .register("http", PlainConnectionSocketFactory.getSocketFactory())
        .register("https", SSLConnectionSocketFactory.getSocketFactory())
        .build()
    val connectionManager = PoolingHttpClientConnectionManager(registry)
    connectionManager.maxTotal = maxTotal!! // 最大连接数

    connectionManager.defaultMaxPerRoute = defaultMaxPerRoute!! //单个路由最大连接数

    connectionManager.validateAfterInactivity = validateAfterInactivity!! // 最大空间时间

    val requestConfig = RequestConfig.custom()
        .setSocketTimeout(socketTimeout!!) //服务器返回数据(response)的时间，超过抛出read timeout
        .setConnectTimeout(connectTimeout!!) //连接上服务器(握手成功)的时间，超出抛出connect timeout
        .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled) // 提交前检测是否可用
        .setConnectionRequestTimeout(connectionRequestTimeout!!) //从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
        .build()
    return HttpClientBuilder.create()
        .setDefaultRequestConfig(requestConfig)
        .setConnectionManager(connectionManager)
        .build()
  }
}
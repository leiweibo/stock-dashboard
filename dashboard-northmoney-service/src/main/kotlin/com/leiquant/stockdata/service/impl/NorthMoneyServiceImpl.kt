package com.leiquant.stockdata.service.impl

import com.leiquant.stockdata.service.NorthMoneyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NorthMoneyServiceImpl: NorthMoneyService {
  @Autowired lateinit var restTemplate: RestTemplate
  @Value("\${eastmoney.northmoney.url}") lateinit var serverUrl:String

  override fun getNorthMoney(startDate: String, endDate: String) {

  }
}
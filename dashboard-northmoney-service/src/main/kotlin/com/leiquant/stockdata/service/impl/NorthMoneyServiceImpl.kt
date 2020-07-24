package com.leiquant.stockdata.service.impl

import com.leiquant.stockdata.service.NorthMoneyService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class NorthMoneyServiceImpl : NorthMoneyService {
  @Value("\${eastmoney.northmoney.url}")
  lateinit var serverUrl: String

  override fun getNorthMoney(startDate: String, endDate: String) {
//    RestTemplateUtil.get("")
  }
}
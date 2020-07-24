package com.leiquant.stockdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.leiquant", "com.leiquant.core.config"])
class StockdataApplication

fun main(args: Array<String>) {
  runApplication<StockdataApplication>(*args)
}

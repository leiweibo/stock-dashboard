package com.leiquant.stockdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockdataApplication

fun main(args: Array<String>) {
	runApplication<StockdataApplication>(*args)
}

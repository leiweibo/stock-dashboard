package com.leiquant.stockdata.models

open class ApiResult {
  lateinit var msg: String

  constructor(msg: String) {
    this.msg = msg
  }
}
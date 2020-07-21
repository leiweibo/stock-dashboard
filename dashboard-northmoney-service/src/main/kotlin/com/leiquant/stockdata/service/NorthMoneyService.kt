package com.leiquant.stockdata.service

interface NorthMoneyService {

  /**
   * Get the north money via start date and end date.
   * @param startDate: start date.
   * @param endDate: end date
   */
  fun getNorthMoney(startDate: String, endDate: String)
}
package com.particeep.test

import com.particeep.api.core.ApiCredential

object ConfigTest {

  val credential: ApiCredential = ApiCredential(
    apiKey    = "d6a53e1a-fc8e-4251-9dda-fabbce5f2a2c",
    apiSecret = "0a8fdc8d-e109-48b6-b6f0-c9bf13d09cfa",
    baseUrl   = "https://test-api.particeep.com"
  )
  val version                   = "1"
}

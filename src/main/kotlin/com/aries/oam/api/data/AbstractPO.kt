package com.aries.oam.api.data

import com.fasterxml.jackson.annotation.JsonProperty

abstract class AbstractPO(@get:JsonProperty("ZA_COMM_HEADER") open var eACommHeaderVO: List<Map<String, String>> = arrayListOf())
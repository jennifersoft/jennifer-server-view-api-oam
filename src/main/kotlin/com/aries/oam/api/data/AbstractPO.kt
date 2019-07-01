package com.aries.oam.api.data

import com.fasterxml.jackson.annotation.JsonProperty

abstract class AbstractPO(@get:JsonProperty("eACommHeaderVO") open var eACommHeaderVO: List<Map<String, String>> = arrayListOf())
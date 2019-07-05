package com.aries.oam.api.data

data class ResponseErrorPO(
    override var eACommHeaderVO: List<Map<String, String>> = arrayListOf(),
    var message: String = ""
) : AbstractPO()
package com.aries.oam.api.data

data class ReadOrDeletePO(
    override var eACommHeaderVO: List<Map<String, String>> = arrayListOf(),
    var id: String = ""
) : AbstractPO()
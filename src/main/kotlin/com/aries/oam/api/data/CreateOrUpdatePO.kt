package com.aries.oam.api.data

data class CreateOrUpdatePO(
    override var eACommHeaderVO: List<Map<String, String>> = arrayListOf(),
    var id: String = "",
    var password: String = "",
    var name: String = "",
    var group: String = "",
    var department: String = ""
) : AbstractPO()
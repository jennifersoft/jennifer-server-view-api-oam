package com.aries.oam.api.data

data class ResponseGetPO(
        override var eACommHeaderVO: List<Map<String, String>> = arrayListOf(),
        var message: String = "",
        var users: List<UserVO> = arrayListOf()
) : AbstractPO()
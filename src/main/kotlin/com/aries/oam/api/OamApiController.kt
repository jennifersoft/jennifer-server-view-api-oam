package com.aries.oam.api

import com.aries.extension.starter.PluginController
import com.aries.oam.api.data.*
import com.aries.view.service.GroupService
import com.aries.view.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
class OamApiController @Autowired constructor(private var userService: UserService, private var groupService: GroupService) : PluginController() {
    companion object {
        private val RESPONSE_HEADER_KEYS = arrayOf("END_USER", "MD_CD", "UUID", "SRVC_ID")
        private val HTTP_COMMON_HEADERS = createDefaultHeaders()

        fun filteringHeaderVO(headers: List<Map<String, String>>): List<Map<String, String>> {
            return headers.filter { RESPONSE_HEADER_KEYS.contains(it["ZA_COMM_BUSN_HEDR_KEY"]) }
        }

        private fun createDefaultHeaders(): HttpHeaders {
            val headers = HttpHeaders()
            headers.add("Content-Type", "application/json;charset=UTF-8")
            return headers
        }

        private fun errorResponseEntity(headers: List<Map<String, String>>, message: String): ResponseEntity<AbstractPO> =
            ResponseEntity(ResponseErrorPO(filteringHeaderVO(headers), message), HTTP_COMMON_HEADERS, HttpStatus.OK)

        private fun postResponseEntity(headers: List<Map<String, String>>, message: String): ResponseEntity<AbstractPO> =
            ResponseEntity(ResponsePostPO(filteringHeaderVO(headers), message), HTTP_COMMON_HEADERS, HttpStatus.OK)

        private fun getResponseEntity(headers: List<Map<String, String>>, message: String, users: List<UserVO>): ResponseEntity<AbstractPO> =
            ResponseEntity(ResponseGetPO(filteringHeaderVO(headers), message, users), HTTP_COMMON_HEADERS, HttpStatus.OK)
    }

    @GetMapping(value = [ "/oamapi"])
    fun getMainPage(): ResponseEntity<ResponseGetPO> =
            ResponseEntity(ResponseGetPO(filteringHeaderVO(mutableListOf()), OamApiStatus.SUCCESS.name), HttpStatus.OK)

    @PostMapping(value = [ "/oamapi/user/create" ], produces = [ "application/json; charset=utf-8" ])
    fun createUser(@RequestBody po: CreateOrUpdatePO): ResponseEntity<AbstractPO> {
        val dbUser = com.aries.view.domain.User()
        var status = OamApiStatus.SUCCESS

        // 그룹 아이디는 공백일 때, guest로 설정해준다.
        if (po.group.equals(""))
            po.group = "guest"

        try {
            if (po.id == "" || po.password == "" || po.name == "")
                status = OamApiStatus.PARAMETER_REQUIRED
            else if (userService.exist(po.id))
                status = OamApiStatus.USER_EXIST
            else if (groupService.find(po.group) == null)
                status = OamApiStatus.GROUP_NOT_EXIST

            if (status == OamApiStatus.SUCCESS) {
                dbUser.id = po.id
                dbUser.password = po.password
                dbUser.name = po.name
                dbUser.groupId = po.group
                dbUser.dept = po.department

                userService.save(dbUser)
            }
        } catch (e: Exception) {
            return errorResponseEntity(po.eACommHeaderVO, e.toString())
        }

        return postResponseEntity(po.eACommHeaderVO, status.name)
    }

    @PostMapping(value = [ "/oamapi/user/read" ])
    fun getUser(@RequestBody po: ReadOrDeletePO): ResponseEntity<AbstractPO> {
        val users = arrayListOf<UserVO>()
        var status = OamApiStatus.SUCCESS

        try {
            if (po.id.equals("")) {
                var dbUsers = userService.list()
                dbUsers!!.forEach {
                    users.add(UserVO(it.id, "", it.name, it.groupId, it.dept))
                }
            } else {
                val dbUser = userService.find(po.id)
                if (dbUser == null)
                    status = OamApiStatus.USER_NOT_EXIST
                else
                    users.add(UserVO(dbUser.id, "", dbUser.name, dbUser.groupId, dbUser.dept))
            }
        } catch (e: Exception) {
            return errorResponseEntity(po.eACommHeaderVO, e.toString())
        }

        return getResponseEntity(po.eACommHeaderVO, status.name, users)
    }

    @PostMapping(value = [ "/oamapi/user/update" ])
    fun updateUser(@RequestBody po: CreateOrUpdatePO): ResponseEntity<AbstractPO> {
        var status = OamApiStatus.SUCCESS

        try {
            val dbUser = userService.find(po.id)

            if (dbUser == null) {
                status = OamApiStatus.USER_NOT_EXIST
            } else {
                if (po.password != "") dbUser.password = po.password
                if (po.name != "") dbUser.name = po.name
                if (po.department != "") dbUser.dept = po.department
                if (po.group != "") {
                    if (groupService.find(po.group) == null)
                        status = OamApiStatus.GROUP_NOT_EXIST
                    else
                        dbUser.groupId = po.group
                }
            }

            if (status == OamApiStatus.SUCCESS)
                userService.modify(dbUser)
        } catch (e: Exception) {
            return errorResponseEntity(po.eACommHeaderVO, e.toString())
        }

        return postResponseEntity(po.eACommHeaderVO, status.name)
    }

    @PostMapping(value = [ "/oamapi/user/delete" ])
    fun deleteUser(@RequestBody po: ReadOrDeletePO): ResponseEntity<AbstractPO> {
        var status = OamApiStatus.SUCCESS

        try {
            if (!userService.exist(po.id))
                status = OamApiStatus.USER_NOT_EXIST

            if (status == OamApiStatus.SUCCESS)
                userService.remove(po.id)
        } catch (e: Exception) {
            return errorResponseEntity(po.eACommHeaderVO, e.toString())
        }

        return postResponseEntity(po.eACommHeaderVO, status.name)
    }
}
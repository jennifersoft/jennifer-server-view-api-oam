package com.aries.oam.api

import com.aries.extension.starter.PluginController
import com.aries.oam.api.data.*
import com.aries.view.service.GroupService
import com.aries.view.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
class OamApiController @Autowired constructor(private var userService: UserService, private var groupService: GroupService) : PluginController() {
    companion object {
        private val RESPONSE_HEADER_KEYS = arrayOf("END_USER", "MD_CD", "UUID", "SRVC_ID")

        fun filteringHeaderVO(headers: List<Map<String, String>>): List<Map<String, String>> {
            return headers.filter { RESPONSE_HEADER_KEYS.contains(it["ZA_COMM_BUSN_HEDR_KEY"]) }
        }
    }

    @GetMapping(value = [ "/oamapi"])
    fun getMainPage(): ResponseEntity<ResponseGetPO> =
            ResponseEntity(ResponseGetPO(filteringHeaderVO(mutableListOf()), OamApiStatus.SUCCESS.name), HttpStatus.OK)

    @PostMapping(value = [ "/oamapi/user/create" ])
    fun createUser(@RequestBody po: CreateOrUpdatePO): ResponseEntity<ResponsePostPO> {
        val dbUser = com.aries.view.domain.User()

        // 그룹 아이디는 공백일 때, guest로 설정해준다.
        if (po.group == "")
            po.group = "guest"

        var status = OamApiStatus.SUCCESS
        if (po.id == "" || po.password == "" || po.name == "")
            status = OamApiStatus.REQUIRED_PARAMETERS
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

        return ResponseEntity(ResponsePostPO(filteringHeaderVO(po.eACommHeaderVO), status.name), HttpStatus.OK)
    }

    @GetMapping(value = [ "/oamapi/user/read" ])
    fun getUser(@RequestBody po: ReadOrDeletePO): ResponseEntity<ResponseGetPO> {
        val users = arrayListOf<UserVO>()
        var status = OamApiStatus.SUCCESS

        if (po.id == "") {
            val dbUsers = userService.list()
            dbUsers!!.forEach{
                users.add(UserVO(it.id, "", it.name, it.groupId, it.dept))
            }
        } else {
            val dbUser = userService.find(po.id)
            if (dbUser == null)
                status = OamApiStatus.USER_NOT_EXIST
            else
                users.add(UserVO(dbUser.id, "", dbUser.name, dbUser.groupId, dbUser.dept))
        }

        return ResponseEntity(ResponseGetPO(filteringHeaderVO(po.eACommHeaderVO), status.name, users), HttpStatus.OK)
    }

    @PostMapping(value = [ "/oamapi/user/update" ])
    fun updateUser(@RequestBody po: CreateOrUpdatePO): ResponseEntity<ResponsePostPO> {
        val dbUser = userService.find(po.id)

        var status = OamApiStatus.SUCCESS
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

        return ResponseEntity(ResponsePostPO(filteringHeaderVO(po.eACommHeaderVO), status.name), HttpStatus.OK)
    }

    @PostMapping(value = [ "/oamapi/user/delete" ])
    fun deleteUser(@RequestBody po: ReadOrDeletePO): ResponseEntity<ResponsePostPO> {
        var status = OamApiStatus.SUCCESS
        if (po.id == "") {
            status = OamApiStatus.REQUIRED_PARAMETERS
        } else {
            if (!userService.exist(po.id))
                status = OamApiStatus.USER_NOT_EXIST

            if (status == OamApiStatus.SUCCESS)
                userService.remove(po.id)
        }


        return ResponseEntity(ResponsePostPO(filteringHeaderVO(po.eACommHeaderVO), status.name), HttpStatus.OK)
    }

    @ExceptionHandler
    fun handleError(e: Exception): ResponseEntity<ResponseErrorPO> {
        return ResponseEntity(ResponseErrorPO(e.toString()), HttpStatus.OK)
    }
}
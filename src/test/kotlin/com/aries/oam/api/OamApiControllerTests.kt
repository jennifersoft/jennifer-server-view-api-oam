package com.aries.oam.api

import com.aries.oam.api.data.*
import com.aries.view.service.GroupService
import com.aries.view.service.GroupServiceImpl
import com.aries.view.service.UserService
import com.aries.view.service.UserServiceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class OamApiControllerTests {
    private var mockMvc: MockMvc? = null
    private val mapper = ObjectMapper()
    private val header = arrayListOf<Map<String, String>>()
    private val userService: UserService = UserServiceImpl()
    private val groupService: GroupService = GroupServiceImpl()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(OamApiController(userService, groupService)).build()
    }

    @Test
    fun `기본 그룹으로 사용자 생성하기`() {
        val req = post("/plugin/oamapi/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "tester", "tester", "Tester", "", "R&D")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.SUCCESS.name))
        r.andDo(print())
    }

    @Test
    fun `이미 존재하는 사용자 생성하기`() {
        val req = post("/plugin/oamapi/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "guest", "1234", "Guest", "guest", "R&D")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.USER_EXIST.name))
        r.andDo(print())
    }

    @Test
    fun `그룹이 없는 사용자 생성하기`() {
        val req = post("/plugin/oamapi/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "guest2", "1234", "Guest", "guest2", "R&D")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.GROUP_NOT_EXIST.name))
        r.andDo(print())
    }

    @Test
    fun `ID 누락 사용자 생성하기`() {
        val req = post("/plugin/oamapi/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "", "1234", "Guest", "guest", "R&D")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.REQUIRED_PARAMETERS.name))
        r.andDo(print())
    }

    @Test
    fun `비밀번호 누락 사용자 생성하기`() {
        val req = post("/plugin/oamapi/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "guest", "", "Guest2", "guest", "R&D")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.REQUIRED_PARAMETERS.name))
        r.andDo(print())
    }

    @Test
    fun `이름 누락 사용자 생성하기`() {
        val req = post("/plugin/oamapi/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "guest", "1234", "", "guest", "R&D")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.REQUIRED_PARAMETERS.name))
        r.andDo(print())
    }

    @Test
    fun `사용자 가져오기`() {
        val req = get("/plugin/oamapi/user/read")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        ReadOrDeletePO(header, "guest")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.SUCCESS.name))
        r.andExpect(jsonPath("\$.users[0].id").value("guest"))
        r.andExpect(jsonPath("\$.users[0].name").value("Guest"))
        r.andExpect(jsonPath("\$.users[0].password").value(""))
        r.andExpect(jsonPath("\$.users[0].group").value("guest"))
        r.andExpect(jsonPath("\$.users[0].department").value("R&D"))
        r.andDo(print())
    }

    @Test
    fun `모든 사용자 가져오기`() {
        val req = get("/plugin/oamapi/user/read")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        ReadOrDeletePO(header)
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.SUCCESS.name))
        r.andExpect(jsonPath("\$.users[1].id").value("monitor"))
        r.andExpect(jsonPath("\$.users[1].name").value("Monitor"))
        r.andExpect(jsonPath("\$.users[1].password").value(""))
        r.andExpect(jsonPath("\$.users[1].group").value("guest"))
        r.andExpect(jsonPath("\$.users[1].department").value("Design"))
        r.andDo(print())
    }

    @Test
    fun `존재하지 않는 사용자 가져오기`() {
        val req = get("/plugin/oamapi/user/read")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        ReadOrDeletePO(header, "guest2")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.USER_NOT_EXIST.name))
        r.andDo(print())
    }

    @Test
    fun `사용자 삭제하기`() {
        val req = post("/plugin/oamapi/user/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        ReadOrDeletePO(header, "guest")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.SUCCESS.name))
    }

    @Test
    fun `존재하지 않는 사용자 삭제하기`() {
        val req = post("/plugin/oamapi/user/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        ReadOrDeletePO(header, "guest2")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.USER_NOT_EXIST.name))
    }

    @Test
    fun `사용자 수정하기`() {
        val req = post("/plugin/oamapi/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "guest", "4321", "Guest", "guest", "QA")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.SUCCESS.name))

        val req2 = get("/plugin/oamapi/user/read")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        ReadOrDeletePO(header, "guest")
                ))

        val r2 = mockMvc!!.perform(req2)
        r2.andExpect(jsonPath("\$.message").value(OamApiStatus.SUCCESS.name))
        r2.andExpect(jsonPath("\$.users[0].department").value("QA"))
        r2.andDo(print())
    }

    @Test
    fun `존재하지 않는 사용자 수정하기`() {
        val req = post("/plugin/oamapi/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "guest2", "", "Guest", "guest", "QA")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.USER_NOT_EXIST.name))
    }

    @Test
    fun `존재하지 않는 그룹의 사용자 수정하기`() {
        val req = post("/plugin/oamapi/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        CreateOrUpdatePO(header, "guest", "", "Guest", "admin", "QA")
                ))

        val r = mockMvc!!.perform(req)
        r.andExpect(jsonPath("\$.message").value(OamApiStatus.GROUP_NOT_EXIST.name))
    }
}
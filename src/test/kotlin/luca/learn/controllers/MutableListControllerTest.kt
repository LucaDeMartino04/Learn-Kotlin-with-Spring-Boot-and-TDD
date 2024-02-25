package luca.learn.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import luca.learn.dataClasses.IndexedStringSearch
import luca.learn.dataClasses.StringSearchInList
import luca.learn.response.MutableListResponse
import luca.learn.response.MutableListSearchByValueResponse
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@SpringBootTest
@AutoConfigureMockMvc
class MutableListControllerTest {
    private val baseUrl = "/list"
    private val listOfString = mutableListOf("Hello", "World")

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun sizeMutableList() {
        val jsonRequest = jacksonObjectMapper().writeValueAsString(listOfString)

        mockMvc.get("$baseUrl/size") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("2") }
            }
    }

    @Test
    fun getElementAtIndex() {
        val mutableListWithIndex = IndexedStringSearch(listOfString, 0)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(mutableListWithIndex)
        val result = mutableListOf("Hello")
        mockMvc.get("$baseUrl/getElementAtIndex") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { result.toString() }
            }
    }

    @Test
    fun getElementAtIndexError() {
        val mutableListWithIndex = IndexedStringSearch(listOfString, 3)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(mutableListWithIndex)
        val result = MutableListResponse.Error("value not in list")

        mockMvc.get("$baseUrl/getElementAtIndex") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isBadRequest() }
                content { result.toString() }
            }
    }

    @Test
    fun getElementPositionByValue() {
        val listOfString = mutableListOf("Hello", "World", "Hello")
        val mutableListWithIndex = StringSearchInList(listOfString, "Hello")
        val jsonRequest = jacksonObjectMapper().writeValueAsString(mutableListWithIndex)
        val result = listOf(Pair(0, "Hello"), Pair(2, "Hello"))

        mockMvc.get("$baseUrl/getElementByString") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { result.toString() }
            }
    }

    @Test
    fun getElementPositionByValueError() {
        val mutableListWithIndex = StringSearchInList(listOfString, "Ciao")
        val jsonRequest = jacksonObjectMapper().writeValueAsString(mutableListWithIndex)
        val result = MutableListSearchByValueResponse.Error("value not in list")

        mockMvc.get("$baseUrl/getElementByString") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isBadRequest() }
                content { result.toString() }
            }
    }


    @Test
    fun removeElementAtIndex() {
        val mutableListWithIndex = IndexedStringSearch(listOfString, 1)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(mutableListWithIndex)
        val result = mutableListOf("Hello")
        mockMvc.get("$baseUrl/removeElementAtIndex") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { result.toString() }
            }
    }

    @Test
    fun removeElementAtIndexError() {
        val mutableListWithIndex = IndexedStringSearch(listOfString, 3)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(mutableListWithIndex)
        val result = MutableListResponse.Error("index not in range")

        mockMvc.get("$baseUrl/removeElementAtIndex") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isBadRequest() }
                content { result.toString() }
            }
    }

    @Test
    fun removeElementByValue() {
        val mutableListWithIndex = StringSearchInList(listOfString, "World")
        val jsonRequest = jacksonObjectMapper().writeValueAsString(mutableListWithIndex)
        val result = mutableListOf("Hello")
        mockMvc.get("$baseUrl/removeElementByValue") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { result.toString() }
            }
    }

    @Test
    fun removeElementByValueError() {
        val mutableListWithIndex = StringSearchInList(listOfString, "Ciao")
        val jsonRequest = jacksonObjectMapper().writeValueAsString(mutableListWithIndex)
        val result = MutableListResponse.Error("value not in list")

        mockMvc.get("$baseUrl/removeElementByValue") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isBadRequest() }
                content { result.toString() }
            }
    }

    @Test
    fun isEmptyListTrue() {
        val listOfString = mutableListOf<String>()
        val jsonRequest = jacksonObjectMapper().writeValueAsString(listOfString)

        mockMvc.get("$baseUrl/isEmpty") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("true") }
            }
    }

    @Test
    fun isEmptyListFalse() {
        val jsonRequest = jacksonObjectMapper().writeValueAsString(listOfString)

        mockMvc.get("$baseUrl/isEmpty") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("false") }
            }
    }


}
package luca.learn.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class MathControllerTest {
    private final val baseUrl = "/math"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun add() {
        val requestBody = arrayOf(1, 2, 3, 4, 5)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(requestBody)

        mockMvc.get("$baseUrl/add") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("15") }
            }
    }

    @Test
    fun sub() {
        val num1 = 3
        val num2 = 4
        mockMvc.get("$baseUrl/sub/$num1&$num2")
            .andExpect {
                status { isOk() }
                content { string("-1") }
            }
    }


    @Test
    fun div() {
        val num1 = 3
        val num2 = 4
        mockMvc.get("$baseUrl/div/$num1&$num2")
            .andExpect {
                status { isOk() }
                content { string("0") }
            }
    }

    @Test
    fun mul() {
        val array = arrayOf(1, 2, 3, 4)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(array)

        mockMvc.get("$baseUrl/mul") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("24") }
            }
    }

    @Test
    fun mod() {
        val num1 = 3
        val num2 = 4
        mockMvc.get("$baseUrl/mod/$num1&$num2")
            .andExpect {
                status { isOk() }
                content { string("3") }
            }
    }

    @Test
    fun abs() {
        val num = -3.21
        mockMvc.get("$baseUrl/abs/$num")
            .andExpect {
                status { isOk() }
                content { string("3.21") }
            }
    }

    @Test
    fun pow() {
        val num1 = 2.0
        val num2 = 2.0
        mockMvc.get("$baseUrl/pow/$num1^$num2")
            .andExpect {
                status { isOk() }
                content { string("4.0") }
            }
    }

    @Test
    fun floor() {
        val num = 3.9879
        mockMvc.get("$baseUrl/floor/$num")
            .andExpect {
                status { isOk() }
                content { string("3") }
            }
    }

    @Test
    fun max() {
        val arrayDouble: Array<Double> = arrayOf(2.3, 3.22, 8.0, 9.0, 10.1, 11111.0)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayDouble)
        mockMvc.get("$baseUrl/max") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("11111.0") }
            }
    }

    @Test
    fun min() {
        val arrayDouble: Array<Double> = arrayOf(2.3, 3.22, 8.0, 9.0, 10.1, 11111.0)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayDouble)
        mockMvc.get("$baseUrl/min") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("2.3") }
            }
    }

    @Test
    fun preIncrement() {
        val num = -0.1
        mockMvc.get("$baseUrl/preIncrement/$num")
            .andExpect {
                status { isOk() }
                content { string("0.9") }
            }
    }

    @Test
    fun preDecrement() {
        val num = 0.1
        mockMvc.get("$baseUrl/preDecrement/$num")
            .andExpect {
                status { isOk() }
                content { string("-0.9") }
            }
    }

    @Test
    fun switchNum(){
        val num = -3444
        mockMvc.get("$baseUrl/switchNum/$num")
            .andExpect {
                status { isOk() }
                content { string("Ok") }
            }
    }

    @Test
    fun switchNumError(){
        val num = 3
        mockMvc.get("$baseUrl/switchNum/$num")
            .andExpect {
                status { isBadRequest() }
                content { string("Error num not in range") }
            }
    }
}
package luca.learn

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
class BooleanControllerTest {
    private val baseUrl = "/bool"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun flip() {
        val bool = true
        mockMvc.get("$baseUrl/flip/$bool")
            .andExpect {
                status { isOk() }
                content { false.toString() }
            }
    }

    @Test
    fun andTrue(){
        val arrayBool = arrayOf(true, true, true)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayBool)
        mockMvc.get("$baseUrl/and"){
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { true.toString() }
            }
    }

    @Test
    fun andFalse(){
        val arrayBool = arrayOf(true, false, true)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayBool)
        mockMvc.get("$baseUrl/and"){
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { false.toString() }
            }
    }

    @Test
    fun orTrue(){
        val arrayBool = arrayOf(false, false, true)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayBool)
        mockMvc.get("$baseUrl/or"){
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { true.toString() }
            }
    }

    @Test
    fun orFalse(){
        val arrayBool = arrayOf(false, false, false)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayBool)
        mockMvc.get("$baseUrl/or"){
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { false.toString() }
            }
    }

    @Test
    fun equalTrue() {
        val num1 = 3.1
        mockMvc.get("$baseUrl/equal/$num1==$num1")
            .andExpect {
                status { isOk() }
                content { true.toString() }
            }
    }

    @Test
    fun equalFalse() {
        val num1 = 3.1
        val num2 = 3.3
        mockMvc.get("$baseUrl/major/$num1>$num2")
            .andExpect {
                status { isOk() }
                content { false.toString() }
            }
    }

    fun majorTrue() {
        val num1 = 3.1
        val num2 = 3.0
        mockMvc.get("$baseUrl/major/$num1>$num2")
            .andExpect {
                status { isOk() }
                content { true.toString() }
            }
    }

    @Test
    fun majorFalse() {
        val num1 = 3.1
        val num2 = 3.3
        mockMvc.get("$baseUrl/major/$num1>$num2")
            .andExpect {
                status { isOk() }
                content { false.toString() }
            }
    }

    @Test
    fun minorTrue() {
        val num1 = 3.3
        val num2 = 3.1
        mockMvc.get("$baseUrl/minor/$num1<$num2")
            .andExpect {
                status { isOk() }
                content { true.toString() }
            }
    }

    @Test
    fun minorFalse() {
        val num1 = 3.1
        val num2 = 3.3
        mockMvc.get("$baseUrl/minor/$num1<$num2")
            .andExpect {
                status { isOk() }
                content { false.toString() }
            }
    }
}
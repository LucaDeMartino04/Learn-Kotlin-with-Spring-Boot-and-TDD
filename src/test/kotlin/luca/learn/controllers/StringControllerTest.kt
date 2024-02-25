package luca.learn.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import luca.learn.dataClasses.CharSearchInString
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class StringControllerTest {
    private final val testText = "Test"
    private final val baseUrl = "/string"
    private final val arrayText = arrayOf("Hello ", "World")

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun textToUppercase() {
        mockMvc.get("$baseUrl/uppercase/$testText")
            .andExpect {
                status { isOk() }
                content { string("TEST") }
            }
    }

    @Test
    fun textToLowercase() {
        mockMvc.get("$baseUrl/lowercase/$testText")
            .andExpect {
                status { isOk() }
                content { string("test") }
            }
    }

    @Test
    fun textLength() {
        mockMvc.get("$baseUrl/length/$testText")
            .andExpect {
                status { isOk() }
                content { string("4") }
            }
    }

    @Test
    fun textFirstChar() {
        mockMvc.get("$baseUrl/firstChar/$testText")
            .andExpect {
                status { isOk() }
                content { string("T") }
            }
    }

    @Test
    fun textLastChar() {
        mockMvc.get("$baseUrl/lastChar/$testText")
            .andExpect {
                status { isOk() }
                content { string("t") }
            }
    }

    @Test
    fun textIndexChar() {
        val request = CharSearchInString(testText, 2)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.post("$baseUrl/char") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("s") }
            }

    }

    @Test
    fun textIndexCharWithError() {
        val request = CharSearchInString(testText, -4)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.post("$baseUrl/char") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isBadRequest() }
                content { string("invalid index") }
            }

    }

    @Test
    fun textIndexCharWithNegativeIndex() {
        val request = CharSearchInString(testText, -3)
        val jsonRequest = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.post("$baseUrl/char") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("e") }
            }

    }

    @Test
    fun textIsNotEmpty() {
        mockMvc.get("$baseUrl/empty?text=$testText")
            .andExpect {
                status { isOk() }
                content { false.toString() }
            }
    }

    @Test
    fun textIsEmpty() {
        mockMvc.get("$baseUrl/empty?text=")
            .andExpect {
                status { isOk() }
                content { true.toString() }
            }
    }

    @Test
    fun textConcat() {
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayText)
        mockMvc.get("$baseUrl/concat") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("Hello World") }
            }
    }

    @Test
    fun textConcatOnlyOneElement() {
        val arrayText = arrayOf("")
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayText)
        mockMvc.get("$baseUrl/concat") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string("") }
            }
    }

    @Test
    fun textTrimIndent() {
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayText)
        val textTrimIndented = """
            Hello 
            World
        """.trimIndent()

        mockMvc.get("$baseUrl/trimIndent") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string(textTrimIndented) }
            }
    }

    @Test
    fun textTrimIndentOnlyOneElement() {
        val arrayText = arrayOf("")
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayText)
        val textTrimIndented = """
                               """.trimIndent()

        mockMvc.get("$baseUrl/trimIndent") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { string(textTrimIndented) }
            }
    }

    @Test
    fun textCompare2Elements() {
        val arrayText = arrayOf("Ciao", "Ciao")// String Pool Memory (point at same value)Mat
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayText)
        val result = arrayOf(true, true, true)

        mockMvc.get("$baseUrl/compare") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { result.toString() }
            }
    }

    @Test
    fun textCompare2ElementsWithDifferentValue() {
        val arrayText = arrayOf("Ciao", String("Ciao".toCharArray()))// String Pool Memory, Heap
        val jsonRequest = jacksonObjectMapper().writeValueAsString(arrayText)
        val result = arrayOf(true, false, false)

        mockMvc.get("$baseUrl/compare") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                content { result.toString() }
            }
    }

    @Test
    fun textSwitch(){
        val gender = "Male"
        mockMvc.get("$baseUrl/switch/$gender")
            .andExpect {
                status { isOk() }
                content { string("Ok") }
            }
    }

    @Test
    fun textSwitchError(){
        val gender = "Mario"
        mockMvc.get("$baseUrl/switch/$gender")
            .andExpect {
                status { isBadRequest() }
                content { string("gender not accepted") }
            }
    }

}
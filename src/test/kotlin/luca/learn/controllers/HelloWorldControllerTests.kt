package luca.learn.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class HelloWorldControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testHelloWorld() {
        mockMvc.get("/api/hello")
            .andExpect {
                status().isOk
                content().string("Hello World")
            }
    }

    @Test
    fun testHelloWorldWithParam() {
        val name = "Test"
        mockMvc.get("/api/hello?name=$name")
            .andExpect {
                status { isOk() }
                content { string("Hello $name") }
            }
    }

    @Test
    fun testHelloWorldWithPath() {
        val name = "Test"
        mockMvc.get("/api/hello/$name")
            .andExpect {
                status { isOk() }
                content { string("Hello $name") }
            }
    }

}

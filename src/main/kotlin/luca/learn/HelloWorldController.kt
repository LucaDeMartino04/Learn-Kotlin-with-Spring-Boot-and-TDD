package luca.learn

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HelloWorldController {

    @GetMapping("/hello")
    fun helloParam(@RequestParam name: String = "World"): String {
        return "Hello $name"
    }

    @GetMapping("/hello/{name}")
    fun helloPath(@PathVariable name: String = "World"): String {
        return "Hello $name"
    }


}
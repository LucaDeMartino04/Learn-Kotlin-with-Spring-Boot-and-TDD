package luca.learn.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bool")
class BooleanController {

    @GetMapping("/flip/{bool}")
    fun flip(@PathVariable bool: Boolean): Boolean {
        return !bool
    }

    @GetMapping("/and")
    fun and(@RequestBody arrayBool: Array<Boolean>): Boolean {
        var finalBool = true
        for (bool in arrayBool){
            finalBool = finalBool && bool
        }
        return finalBool
    }

    @GetMapping("/or")
    fun or(@RequestBody arrayBool: Array<Boolean>): Boolean {
        var finalBool = false
        for (bool in arrayBool){
            finalBool = finalBool || bool
        }
        return finalBool
    }

    @GetMapping("/equal/{num1}=={num2}")
    fun equal(@PathVariable num1: Double,@PathVariable num2: Double ): Boolean {
        return num1 == num2
    }

    @GetMapping("/major/{num1}>{num2}")
    fun major(@PathVariable num1: Double,@PathVariable num2: Double ): Boolean {
        return num1 > num2
    }

    @GetMapping("/minor/{num1}<{num2}")
    fun minor(@PathVariable num1: Double,@PathVariable num2: Double ): Boolean {
        return num1 > num2
    }
}
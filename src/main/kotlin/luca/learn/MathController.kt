package luca.learn

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.pow

@RestController
@RequestMapping("/math")
class MathController {

    @GetMapping("/add")
    fun add(@RequestBody values: Array<Int>): Int {
        var sum: Int = 0
        for (value in values) {
            sum += value
        }
        return sum
    }

    @GetMapping("/sub/{num1}&{num2}")
    fun sub(@PathVariable num1: Int, @PathVariable num2: Int): Int {
        return num1 - num2
    }

    @GetMapping("/div/{num1}&{num2}")
    fun div(@PathVariable num1: Int, @PathVariable num2: Int): Int {
        return num1 / num2
    }

    @GetMapping("/mul")
    fun mul(@RequestBody values: Array<Int>): Int {
        var mul: Int = 1
        for (value in values) {
            mul *= value
        }
        return mul
    }

    @GetMapping("/mod/{num1}&{num2}")
    fun mod(@PathVariable num1: Int, @PathVariable num2: Int): Int {
        return num1 % num2
    }

    @GetMapping("/abs/{num}")
    fun abs(@PathVariable num: Double): Double {
        return kotlin.math.abs(num)
    }

    @GetMapping("/pow/{num1}^{num2}")
    fun pow(@PathVariable num1: Double, @PathVariable num2: Double): Double {
        return num1.pow(num2)
    }

    @GetMapping("/floor/{num}")
    fun floor(@PathVariable num: Double): Int {
        return kotlin.math.floor(num).toInt()
    }

    @GetMapping("/max")
    fun max(@RequestBody arrayDouble: Array<Double>): Double {
        return arrayDouble.max()
    }

    @GetMapping("/min")
    fun min(@RequestBody arrayDouble: Array<Double>): Double {
        return arrayDouble.min()
    }

    @GetMapping("/preIncrement/{num}")
    fun preIncrement(@PathVariable num: Double): Double {
        var numFinal = num
        return ++numFinal
    }

    @GetMapping("/preDecrement/{num}")
    fun preDecrement(@PathVariable num: Double): Double {
        var numFinal = num
        return --numFinal
    }
}
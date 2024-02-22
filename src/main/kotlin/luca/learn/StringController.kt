package luca.learn

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/string")
class StringController {


    @GetMapping("/uppercase/{text}")
    fun textToUppercase(@PathVariable text: String): String {
        return text.uppercase()
    }

    @GetMapping("/lowercase/{text}")
    fun textToLowercase(@PathVariable text: String): String {
        return text.lowercase()
    }

    @GetMapping("/length/{text}")
    fun textLength(@PathVariable text: String): Int {
        return text.length
    }

    @GetMapping("/firstChar/{text}")
    fun textFirstChar(@PathVariable text: String): String {
        return text[0].toString()
    }

    @GetMapping("/lastChar/{text}")
    fun textLastChar(@PathVariable text: String): String {
        return text.last().toString()
    }

    @PostMapping("/char")
    fun textIndexChar(@RequestBody stringWithIndex: SearchCharFromIndex): ResponseEntity<String> {
        val index = stringWithIndex.index
        val text = stringWithIndex.text
        val lengthText = textLength(text)
        if (index in 0..<lengthText) {
            return ResponseEntity.ok(text[index].toString())
        } else if (index < 0 && index > -lengthText) {
            return ResponseEntity.ok(text[lengthText + index].toString())
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid index")
    }

    @GetMapping("/empty")
    fun textIsEmpty(@RequestParam text: String): Boolean {
        return text.isEmpty()
    }

    @GetMapping("/concat")
    fun textConcat(@RequestBody arrayText: Array<String>): String {
        var resultText = ""
        for (text in arrayText) {
            resultText += text
        }
        return resultText
    }

    @GetMapping("/trimIndent")
    fun textTrimIndent(@RequestBody arrayText: Array<String>): String {
        var resultText = ""
        for (text in arrayText) {
            resultText += text + "\n"
        }
        return resultText.trimIndent()
    }

    @GetMapping("/compare")
    fun textCompare(@RequestBody arrayText: Array<String>): ResponseEntity<String> {
        if (arrayText.size == 2) {
            val compareText2EqualSign: Boolean = arrayText[0] == arrayText[1]
            val compareText3EqualSign: Boolean = arrayText[0] === arrayText[1]
            val compareTextEqual: Boolean = arrayText[0].equals(arrayText[1])
            val compareText = arrayOf(compareText2EqualSign, compareText3EqualSign, compareTextEqual)
            return ResponseEntity.ok().body(compareText.toString())
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("array size != 2")
        }
    }
}
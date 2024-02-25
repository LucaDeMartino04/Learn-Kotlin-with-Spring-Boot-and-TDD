package luca.learn.controllers

import luca.learn.dataClasses.IndexedStringSearch
import luca.learn.dataClasses.StringSearchInList
import luca.learn.response.MutableListResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/list")
class MutableListController {

    @GetMapping("/size")
    fun sizeMutableList(@RequestBody listOfString: MutableList<String>): Int {
        return listOfString.size
    }

    @GetMapping("/removeElementWithIndex")
    fun removeMutableListElementWithIndex(@RequestBody listOfStringAndIndex: IndexedStringSearch): ResponseEntity<MutableListResponse> {
        val listOfString = listOfStringAndIndex.mutableList
        val index = listOfStringAndIndex.index
        if (index in 0..<sizeMutableList(listOfString)) {
            listOfString.removeAt(index)
            return ResponseEntity.ok().body(MutableListResponse.Success(listOfString))
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MutableListResponse.Error("index not in range"))
        }
    }

    @GetMapping("/removeElementWithString")
    fun removeMutableListElementWithString(@RequestBody listOfStringAndString: StringSearchInList): ResponseEntity<MutableListResponse> {
        val listOfString = listOfStringAndString.mutableList
        val value = listOfStringAndString.searchString
        if (value in listOfString) {
            listOfString.remove(value)
            return ResponseEntity.ok().body(MutableListResponse.Success(listOfString))
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MutableListResponse.Error("value not in list"))
        }
    }

    @GetMapping("/isEmpty")
    fun isEmptyList(@RequestBody listOfString: MutableList<String>): Boolean {
        return listOfString.isEmpty()
    }

}
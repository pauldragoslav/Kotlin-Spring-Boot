package com.example.paul.controller

import com.example.paul.model.Person
import com.example.paul.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/persons")
class PersonController {

    @Autowired
    lateinit var personRepository: PersonRepository

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Person? = personRepository.findById(id)

    @GetMapping
    fun findAll(): List<Person> = personRepository.findAll()

    @PostMapping
    fun add(@RequestBody person: Person): Person = personRepository.save(person)

    @PutMapping
    fun update(@RequestBody person: Person): Person = personRepository.update(person)

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Int): Boolean = personRepository.removeById(id)

}

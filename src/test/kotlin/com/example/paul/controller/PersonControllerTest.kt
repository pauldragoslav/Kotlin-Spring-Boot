package com.example.paul.controller

import com.example.paul.model.Gender
import com.example.paul.model.Person
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer.Alphanumeric
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric::class)
class PersonControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun test1AddPerson() {
        var person = Person(null, "John Smith", 20, Gender.MALE)
        person = restTemplate.postForObject("/persons", person, Person::class.java)
        assertNotNull(person)
        assertNotNull(person.id)
        assertEquals(1, person.id)
    }

    @Test
    fun test2UpdatePerson() {
        var person = Person(1, "John Smith", 21, Gender.MALE)
        restTemplate.put("/persons", person)
        person = restTemplate.getForObject("/persons/{id}", Person::class.java, 1)
        assertNotNull(person)
        assertEquals(21, person.age)
    }

    @Test
    fun test3DeletePerson() {
        restTemplate.delete("/persons/{id}", 1)
        val person = restTemplate.getForObject("/persons/{id}", Person::class.java, 1)
        assertNull(person)
    }
}

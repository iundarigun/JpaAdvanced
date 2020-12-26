package br.com.devcave.jpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JpaSampleApplication

fun main(args: Array<String>) {
	runApplication<JpaSampleApplication>(*args)
}

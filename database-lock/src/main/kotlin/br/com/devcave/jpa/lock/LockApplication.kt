package br.com.devcave.jpa.lock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class LockApplication

fun main(args: Array<String>) {
	runApplication<LockApplication>(*args)
}

package io.github.casl0.stocknews

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockNewsServerApplication

fun main(args: Array<String>) {
	runApplication<StockNewsServerApplication>(*args)
}

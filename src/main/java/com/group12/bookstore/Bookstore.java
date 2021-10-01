package com.group12.bookstore;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Bookstore {

	public static void main(String[] args) {
		SpringApplication.run(Bookstore.class, args);
	}
}
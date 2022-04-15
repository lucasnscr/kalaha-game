package com.lucasnscr.kalahaGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.lucasnscr.kalahaGame.*"})
@SpringBootApplication
public class KalahaGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(KalahaGameApplication.class, args);
	}

}

package com.restapi.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@SpringBootApplication
public class BookApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookApiApplication.class, args);

        System.out.println("Run hoise, Yea!!!");

	}


    ///api/v1/author

}
/*
	{

        "bookName": "updated Harry Potter 2",
        "author": "updated J.K Rowling",
        "publishedDate": "2007",
        "genre": "fantasy",
        "type": "story"
    }

     {
        "bookName": "Algorithm and concepts",
        "author": "CLRS",
        "publishedDate": "2002",
        "topic":"algorithm",
        "type": "thesis"
    }


     {
        "bookName": "hijibiji",
        "author": "hijibiji",
        "publishedDate": "2007",
        "publisher":"IEEE",
        "type": "journal"
    }


*/
package com.ecobank.srms;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cloudinary.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.IOException;
import java.util.Map;



/*@ComponentScan({"srms.controllers", "srms.repository"})
@EnableJpaRepositories("srms.repository")*/


@SpringBootApplication
//@EntityScan(basePackages = {"com.ecobank.srms.entity"})
//@EnableJpaRepositories(basePackages = {"com.ecobank.srms.repository.StudentRepository"})
public class SrmsApplication {
	public SrmsApplication() throws IOException {
	}

	public static void main(String[] args) throws IOException {

		SpringApplication.run(SrmsApplication.class, args);

	}


}

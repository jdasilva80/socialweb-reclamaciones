package com.jdasilva.socialweb.reclamaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.jdasilva.socialweb.reclamaciones.models.service.IUploadService;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class SocialwebReclamacionesApplication implements CommandLineRunner{

	@Autowired
	IUploadService uploadService;
	
	public static void main(String[] args) {
		SpringApplication.run(SocialwebReclamacionesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		uploadService.deleteAll();
		uploadService.init();
		
	}

}

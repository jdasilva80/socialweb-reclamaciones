package com.jdasilva.socialweb.reclamaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
	
	@Bean
	// @LoadBalanced para usar ribbon con restamplate
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		// Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		// Note: here we are making this converter to process any kind of response,
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

}

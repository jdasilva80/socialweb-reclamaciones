package com.jdasilva.socialweb.reclamaciones;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("tiempoTranscurridoInterceptor")
	HandlerInterceptor TiempoTranscurridoInterceptor;

	@Autowired
	@Qualifier("horarioInterceptor")
	HandlerInterceptor horarioInterceptor;

	@Autowired
	@Qualifier("flashAttributesInterceptor")
	HandlerInterceptor flashAttributesInterceptor;
	
	private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);


	@Override
	/*
	 * se registra el interceptor
	 */
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(horarioInterceptor).excludePathPatterns("/**/reclamaciones/horario**");

		registry.addInterceptor(TiempoTranscurridoInterceptor).addPathPatterns("/**/reclamaciones/**");

		registry.addInterceptor(flashAttributesInterceptor).addPathPatterns("/**/reclamaciones/**");

	}
	

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//		// configuración del directorio estático.
//		Path path = Paths.get("uploads").toAbsolutePath();
//		
//		// registry.addResourceHandler("/api/socialweb-reclamaciones/uploads/**").addResourceLocations("file:/C:/tmp/files/");
//		registry.addResourceHandler("/api/socialweb-reclamaciones/uploads/**")
//				.addResourceLocations(path.toUri().toString(), "file:/C:/tmp/files/");
//		
//	}

}

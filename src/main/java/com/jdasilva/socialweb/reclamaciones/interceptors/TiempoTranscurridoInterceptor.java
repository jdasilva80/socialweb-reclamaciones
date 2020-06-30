package com.jdasilva.socialweb.reclamaciones.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 *se utilizan para funcionalidades transversales en los handlers de la aplicación, no operaciones concretas de los datos
 *como por ejemplo el login, logs, rollbacks...muy útiles para hacer estadísticas.
 * 
 */

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getMethod().equalsIgnoreCase("post"))
			return true;

		logger.info("TiempoTranscurridoInterceptor preHandle: entrando......");
		logger.info("Interceptando : " + handler);

		if (handler instanceof HandlerMethod) {
			logger.info("Es un método del controlador, handler: " + ((HandlerMethod) handler).getMethod().getName());
		}

		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);

		Random random = new Random();
		Thread.sleep(random.nextInt(100));
		
		

		// si devuelve false no se aplicará el interceptor al handler y se redirige a
		// una página de error
		// response.sendRedirect(request.getContextPath().concat("/api/socialweb-reclamaciones/reclamaciones/error"));
		// return false;
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (request.getMethod().equalsIgnoreCase("post"))
			return;

		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
		request.setAttribute("tiempoTranscurrido", tiempoTranscurrido);

		logger.info("TiempoTranscurridoInterceptor preHandle: tiempoTranscurrido : " + tiempoTranscurrido);
		logger.info("TiempoTranscurridoInterceptor preHandle: saliendo......");

		if (handler instanceof HandlerMethod && modelAndView != null) {
			// es importante añadir modelAndView != null porque los recursos estaticos
			// como css serán interceptados tb y no tienen modelo
			modelAndView.getModelMap().addAttribute("tiempoTranscurrido", tiempoTranscurrido);
		}
	}

}

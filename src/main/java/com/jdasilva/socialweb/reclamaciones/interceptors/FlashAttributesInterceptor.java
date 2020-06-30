package com.jdasilva.socialweb.reclamaciones.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

/*
 *se utilizan para funcionalidades transversales en los handlers de la aplicación, no operaciones concretas de los datos
 *como por ejemplo el login, logs, rollbacks...muy útiles para hacer estadísticas.
 * 
 */

@Component("flashAttributesInterceptor")
public class FlashAttributesInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(FlashAttributesInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

//		Map<String, ?> previousFlashAttributes = RequestContextUtils.getInputFlashMap(request);
		FlashMap flashAttributesForNextRequest = RequestContextUtils.getOutputFlashMap(request);

		if(flashAttributesForNextRequest!=null && modelAndView!=null && modelAndView.getModelMap()!=null) {
			
			modelAndView.getModelMap().addAttribute("mensajeFlash",
					modelAndView.getModelMap().getAttribute("mensajeFlash") == null
							? flashAttributesForNextRequest.get("mensajeFlash")
							: modelAndView.getModelMap().getAttribute("mensajeFlash"));

			FlashMap flashMap = new FlashMap();
			flashMap.put("mensajeFlash", modelAndView.getModelMap().getAttribute("mensajeFlash"));

			logger.info("Interceptando mensajeFlash : " + flashMap);

			RequestContextUtils.getFlashMapManager(request).saveOutputFlashMap(flashMap, request, response);
		}

	}

}

package com.jdasilva.socialweb.reclamaciones.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {

	@Value("${config.horario.apertura}")
	private Integer apertura;

	@Value("${config.horario.cierre}")
	private Integer cierre;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Calendar calendar = Calendar.getInstance();
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY);

		StringBuilder sb = new StringBuilder();
		sb.append("El horario de atención al cliente es de ");
		sb.append(apertura);
		sb.append(" h a ");
		sb.append(cierre);
		sb.append(" h");
		sb.append(", Gracias por su visita");

		request.setAttribute("horarioMensaje", sb.toString());

		if (hora >= apertura && hora < cierre) {

			return true;
		}

		response.sendRedirect(request.getContextPath().concat("/api/socialweb-reclamaciones/reclamaciones/horario"));

		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (handler instanceof HandlerMethod && modelAndView != null) {
			// es importante añadir modelAndView != null porque los recursos estaticos
			// como css serán interceptados tb y no tienen modelo

			if (request.getAttribute("horarioMensaje") != null) {

				modelAndView.getModelMap().addAttribute("horarioMensaje",
						"Bienvenido, ".concat((String) request.getAttribute("horarioMensaje")));
			}
		}
	}

}

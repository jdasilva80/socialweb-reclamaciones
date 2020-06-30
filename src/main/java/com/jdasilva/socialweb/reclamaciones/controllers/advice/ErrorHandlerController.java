package com.jdasilva.socialweb.reclamaciones.controllers.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jdasilva.socialweb.commons.errors.UsuarioNoEncontrado;

@ControllerAdvice
public class ErrorHandlerController {
	
	@ExceptionHandler
	public String aritmeticaException(ArithmeticException exception, Model model) {
		
		model.addAttribute("error", "Error aritmético" );
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/global";
		
	}
	
	@ExceptionHandler
	public String numberFormatException(NumberFormatException exception, Model model) {
		
		model.addAttribute("error", "Formato numérico incorrecto" );
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/global";
		
	}
	
	@ExceptionHandler
	public String usuarioNoEncontrado(UsuarioNoEncontrado exception, Model model) {
		
		model.addAttribute("error", "Usuario no encontrado" );
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/global";
		
	}
}

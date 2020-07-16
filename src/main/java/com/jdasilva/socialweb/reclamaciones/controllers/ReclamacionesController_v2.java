package com.jdasilva.socialweb.reclamaciones.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdasilva.socialweb.commons.models.entity.Usuario;
import com.jdasilva.socialweb.reclamaciones.models.domain.Reclamacion;
import com.jdasilva.socialweb.reclamaciones.models.service.IUsuarioService;

@Controller
@RequestMapping("/reclamaciones-v2")
public class ReclamacionesController_v2 {

	@Autowired
	@Qualifier("usuarioRestService")
	IUsuarioService usuarioService;

	@GetMapping("/form")
	public String form(Reclamacion reclamacion, Model model) {

		model.addAttribute("titulo", "Crear reclamación");

		return "reclamacion_v2";
	}

	@PostMapping("/form")
	public String guardar(@Valid Reclamacion reclamacion, BindingResult result, Model model) {

		model.addAttribute("titulo", "Resumen reclamación");

		if (result.hasErrors()) {

			Map<String, String> errors = new HashMap<>();

			result.getFieldErrors().forEach((error) -> errors.put(error.getField(),
					"campo ".concat(error.getField()).concat(" : ").concat(error.getDefaultMessage())));

			model.addAttribute("errors", errors);

			return "reclamacion_v2";
		}
		Usuario usuario = usuarioService.findByUsername(reclamacion.getUsername());
		model.addAttribute("usuario", usuario);

		return "resumen_v2";
	}

}

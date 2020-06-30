package com.jdasilva.socialweb.reclamaciones.clientsrest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdasilva.socialweb.commons.models.entity.Usuario;

@FeignClient(name = "socialweb-usuarios")
public interface UsuariosClienteRestFeign {

	@GetMapping("/usuarios/search/buscar-nombre")
	public Usuario findByUserName(@RequestParam String username);
}

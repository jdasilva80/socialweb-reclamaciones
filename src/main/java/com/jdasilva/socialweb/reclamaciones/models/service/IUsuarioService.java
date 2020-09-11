package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.Optional;

import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public Optional<Usuario> findByUsernameOptional(String username);
}

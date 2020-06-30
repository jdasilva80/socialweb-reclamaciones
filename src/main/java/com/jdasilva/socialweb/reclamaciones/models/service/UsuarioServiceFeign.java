package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdasilva.socialweb.commons.models.entity.Usuario;
import com.jdasilva.socialweb.reclamaciones.clientsrest.UsuariosClienteRestFeign;

@Service("usuariofeingService")
public class UsuarioServiceFeign implements IUsuarioService {

	@Autowired(required = false)
	private UsuariosClienteRestFeign clienteRestFeign;

	@Override
	public Usuario findByUsername(String username) {

		return clienteRestFeign.findByUserName(username);
	}

	@Override
	public Optional<Usuario> findByUsernameOptional(String username) {

		return Optional.ofNullable(clienteRestFeign.findByUserName(username));
	}

}

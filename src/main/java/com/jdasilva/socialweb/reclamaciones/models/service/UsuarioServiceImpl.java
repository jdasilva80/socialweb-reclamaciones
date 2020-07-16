package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jdasilva.socialweb.commons.models.entity.Usuario;

@Service("usuarioRestService")
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	RestTemplate clienteRest;

	@Override
	public Usuario findByUsername(String username) {

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("username", username);

		Usuario usuario = clienteRest.getForObject(
				"https://soyjose-usuarios.herokuapp.com/usuarios/username/{username}", Usuario.class,
				pathVariables);

		return usuario;
	}

	@Override
	public Optional<Usuario> findByUsernameOptional(String username) {

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("username", username);

		Usuario usuario = clienteRest.getForObject(
				"https://soyjose-usuarios.herokuapp.com/usuarios/username/{username}", Usuario.class,
				pathVariables);

		return Optional.ofNullable(usuario);
	}

}

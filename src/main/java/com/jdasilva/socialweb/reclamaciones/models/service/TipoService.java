package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Tipo;

public interface TipoService {

	public List<Tipo> findAll();

	public Tipo getById(Long id);
}

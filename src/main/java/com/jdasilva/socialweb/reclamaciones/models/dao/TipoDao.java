package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Tipo;

public interface TipoDao {

	public List<Tipo> findAll();

	public Tipo getById(Long id);
}

package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Opcion;

public interface OpcionDao {

	public List<Opcion> findAll();

	public Opcion getById(Long id);
}

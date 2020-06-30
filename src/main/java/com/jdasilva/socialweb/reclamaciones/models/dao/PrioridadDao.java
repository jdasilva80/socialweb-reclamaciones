package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Prioridad;

public interface PrioridadDao {

	public List<Prioridad> findAll();

	public Prioridad getById(Long id);
}

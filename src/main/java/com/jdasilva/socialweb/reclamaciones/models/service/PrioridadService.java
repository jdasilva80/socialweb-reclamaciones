package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Prioridad;

public interface PrioridadService {

	public List<Prioridad> findAll();

	public Prioridad getById(Long id);
}

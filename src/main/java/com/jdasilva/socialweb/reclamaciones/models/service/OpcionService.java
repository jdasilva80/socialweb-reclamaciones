package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Opcion;

public interface OpcionService {

	public List<Opcion> findAll();

	public Opcion getById(Long id);
}

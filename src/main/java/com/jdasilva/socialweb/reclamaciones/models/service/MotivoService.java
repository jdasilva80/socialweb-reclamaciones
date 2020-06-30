package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Motivo;

public interface MotivoService {

	public List<Motivo> findAll();

	public Motivo getById(Long id);
}

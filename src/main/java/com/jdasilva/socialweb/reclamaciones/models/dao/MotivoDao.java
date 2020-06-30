package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Motivo;

public interface MotivoDao {

	public List<Motivo> findAll();

	public Motivo getById(Long id);
}

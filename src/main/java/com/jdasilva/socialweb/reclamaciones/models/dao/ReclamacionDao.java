package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import com.jdasilva.socialweb.reclamaciones.models.domain.Reclamacion;

public interface ReclamacionDao {

	public List<Reclamacion> findAll();
	
	public void save(Reclamacion reclamacion);
	
	public Reclamacion getById(Long id);
	
	public Reclamacion findOne(Long id);
	
	public void delete(Long id);
}

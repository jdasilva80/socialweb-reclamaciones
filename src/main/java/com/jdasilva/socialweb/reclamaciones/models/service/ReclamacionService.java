package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jdasilva.socialweb.reclamaciones.models.domain.Reclamacion;

public interface ReclamacionService {

	public List<Reclamacion> findAll();
	
	public Page<Reclamacion> findAll(Pageable pageable);

	public Reclamacion getById(Long id);

	public Reclamacion save(Reclamacion reclamacion);
	
	public Reclamacion findOne(Long id);
	
	public void delete(Long id);
}

package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.reclamaciones.models.dao.PrioridadDao;
import com.jdasilva.socialweb.reclamaciones.models.domain.Prioridad;

@Service
public class PrioridadServiceImpl implements PrioridadService {

	//List<Prioridad> prioridades;	
	@Autowired
	PrioridadDao prioridadDao;

	@Transactional(readOnly = true)
	@Override
	public List<Prioridad> findAll() {

		return prioridadDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Prioridad getById(Long id) {

		//return PrioridadDao.findAll().stream().filter((opcion) -> opcion.getId() == id).findFirst().get();
		return prioridadDao.getById(id);
	}

}

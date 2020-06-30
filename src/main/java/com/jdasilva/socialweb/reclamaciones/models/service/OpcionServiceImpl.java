package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.reclamaciones.models.dao.OpcionDao;
import com.jdasilva.socialweb.reclamaciones.models.domain.Opcion;

@Service
public class OpcionServiceImpl implements OpcionService {

	// List<Opcion> opciones;
	@Autowired
	OpcionDao opcionDao;

	@Transactional(readOnly = true)
	@Override
	public List<Opcion> findAll() {

		return opcionDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Opcion getById(Long id) {

		// return opcionDao.findAll().stream().filter((opcion) -> opcion.getId() ==
		// id).findFirst().get();
		return opcionDao.getById(id);
	}

}

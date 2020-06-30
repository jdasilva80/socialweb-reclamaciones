package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.reclamaciones.models.dao.MotivoDao;
import com.jdasilva.socialweb.reclamaciones.models.domain.Motivo;

@Service
public class MotivoServiceImpl implements MotivoService {

	// List<Motivo> motivos;
	@Autowired
	MotivoDao motivoDao;

	@Transactional(readOnly = true) // podríamos tener distintos DAO dentro de una misma transacción
	@Override
	public List<Motivo> findAll() {

		return motivoDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Motivo getById(Long id) {

		// return motivoDao.findAll().stream().filter((motivo) -> motivo.getId() ==
		// id).findFirst().get();
		return motivoDao.getById(id);
	}

}

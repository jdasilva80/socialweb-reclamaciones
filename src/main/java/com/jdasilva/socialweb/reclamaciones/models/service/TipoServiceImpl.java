package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.reclamaciones.models.dao.TipoDao;
import com.jdasilva.socialweb.reclamaciones.models.domain.Tipo;

@Service
public class TipoServiceImpl implements TipoService {

	// List<Motivo> motivos;
	@Autowired
	TipoDao tipoDao;

	@Transactional(readOnly = true)
	@Override
	public List<Tipo> findAll() {

		return tipoDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Tipo getById(Long id) {

		return tipoDao.getById(id);
	}

}

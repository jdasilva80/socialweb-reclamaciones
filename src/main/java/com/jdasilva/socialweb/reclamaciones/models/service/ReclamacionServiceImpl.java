package com.jdasilva.socialweb.reclamaciones.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdasilva.socialweb.reclamaciones.models.dao.IReclamacionDaoPageable;
import com.jdasilva.socialweb.reclamaciones.models.dao.ReclamacionDao;
import com.jdasilva.socialweb.reclamaciones.models.domain.Reclamacion;

@Service
public class ReclamacionServiceImpl implements ReclamacionService {

	@Autowired
	ReclamacionDao reclamacionDao;

	@Autowired
	IReclamacionDaoPageable reclamacionDaoPageable;

	// List<Reclamacion> reclamaciones = new ArrayList<>();

	@Transactional(readOnly = true)
	@Override
	public List<Reclamacion> findAll() {

		return reclamacionDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Reclamacion getById(Long id) {

//		return reclamacionDao.findAll().stream().filter((reclamacion) -> reclamacion.getId().equals(id)).findFirst()
//				.orElse(null);

		return reclamacionDao.getById(id);
	}

	@Transactional
	@Override
	public Reclamacion save(Reclamacion reclamacion) {

		reclamacionDao.save(reclamacion);

		return reclamacion;
	}

	@Transactional(readOnly = true)
	@Override
	public Reclamacion findOne(Long id) {

		return reclamacionDao.findOne(id);
	}

	@Transactional
	@Override
	public void delete(Long id) {

		reclamacionDao.delete(id);

	}

	@Transactional(readOnly = true)
	@Override
	public Page<Reclamacion> findAll(Pageable pageable) {

		return reclamacionDaoPageable.findAll(pageable);
	}

}

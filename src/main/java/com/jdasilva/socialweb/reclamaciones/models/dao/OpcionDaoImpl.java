package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jdasilva.socialweb.reclamaciones.models.domain.Opcion;

@Repository
public class OpcionDaoImpl implements OpcionDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Opcion> findAll() {
		return em.createQuery("from Opcion").getResultList();
	}

	@Override
	public Opcion getById(Long id) {

		TypedQuery<Opcion> q = em.createQuery("SELECT o FROM Opcion o where o.id = ?1", Opcion.class).setParameter(1, id);

		return q.getSingleResult();
	}

}

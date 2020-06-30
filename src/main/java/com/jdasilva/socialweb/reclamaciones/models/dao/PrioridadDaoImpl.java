package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jdasilva.socialweb.reclamaciones.models.domain.Prioridad;

@Repository
public class PrioridadDaoImpl implements PrioridadDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Prioridad> findAll() {
		return em.createQuery("from Prioridad").getResultList();
	}

	@Override
	public Prioridad getById(Long id) {

		TypedQuery<Prioridad> q = em.createQuery("SELECT p FROM Prioridad p where p.id = ?1", Prioridad.class)
				.setParameter(1, id);

		return q.getSingleResult();
	}

}

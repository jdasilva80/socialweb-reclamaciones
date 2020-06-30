package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jdasilva.socialweb.reclamaciones.models.domain.Motivo;

@Repository
public class MotivoDaoImpl implements MotivoDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Motivo> findAll() {

		return em.createQuery("from Motivo").getResultList();
	}

	@Override
	public Motivo getById(Long id) {

		TypedQuery<Motivo> q = em.createQuery("SELECT m FROM Motivo m where m.id = ?1", Motivo.class).setParameter(1, id);

		return q.getSingleResult();
	}

}

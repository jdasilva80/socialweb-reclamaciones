package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jdasilva.socialweb.reclamaciones.models.domain.Tipo;

@Repository
public class TipoDaoImpl implements TipoDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tipo> findAll() {

		return em.createQuery("from Tipo").getResultList();
	}

	@Override
	public Tipo getById(Long id) {

		TypedQuery<Tipo> q = em.createQuery("SELECT t FROM Tipo t where t.id = ?1", Tipo.class).setParameter(1, id);

		return q.getSingleResult();
	}

}

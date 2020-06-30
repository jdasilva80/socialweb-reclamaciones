package com.jdasilva.socialweb.reclamaciones.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jdasilva.socialweb.reclamaciones.models.domain.Reclamacion;

@Repository
public class ReclamacionDaoImpl implements ReclamacionDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Reclamacion> findAll() {
		return em.createQuery("from Reclamacion").getResultList();
	}

	@Override
	public Reclamacion getById(Long id) {

		TypedQuery<Reclamacion> q = em.createQuery("SELECT r FROM Reclamacion r where r.id = ?1", Reclamacion.class)
				.setParameter(1, id);

		return q.getSingleResult();
	}

	@Override
	public void save(Reclamacion reclamacion) {

		if (reclamacion.getId() == null) {

			em.persist(reclamacion);

		} else {

			em.merge(reclamacion);
		}
	}

	@Override
	public Reclamacion findOne(Long id) {

		return em.find(Reclamacion.class, id);
	}

	@Override
	public void delete(Long id) {

		em.remove(this.findOne(id));
	}

}

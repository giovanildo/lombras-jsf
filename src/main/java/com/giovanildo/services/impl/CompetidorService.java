package com.giovanildo.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.Competidor;
import com.giovanildo.utils.JpaUtils;

public class CompetidorService implements CrudService<Competidor, Integer> {

	@Override
	public List<Competidor> all() {
		return JpaUtils.getEntityManager().createQuery("from Competidor", Competidor.class).getResultList();
	}

	@Override
	public Competidor byId(Integer id) {
		return JpaUtils.getEntityManager().find(Competidor.class, id);
	}

	@Override
	public Competidor insert(Competidor entity) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
//			em.persist(entity.geteAtleta());
//			em.persist(entity.getClube());
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public Competidor update(Competidor entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.merge(entity.geteAtleta());
			em.merge(entity.getClube());
			em.merge(entity);
			em.getTransaction().commit();
			return entity;
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void delete(Competidor entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = null;
		if (byId(id) == null) {
			return;
		}
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.remove(byId(id));
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public Competidor byString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}

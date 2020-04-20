package com.giovanildo.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.Competidor;
import com.giovanildo.utils.JpaUtils;

public class CompetidorService implements CrudService<Competidor, Integer> {

	@Override
	public List<Competidor> all() {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.createQuery("from Competidor", Competidor.class).getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Competidor byId(Integer id) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.find(Competidor.class, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Competidor insert(Competidor entity) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
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
		for (Competidor daVez : all()) {
			if(daVez.geteAtleta().getNome().equals(string)) return daVez;
		}
		return null;
	}

}

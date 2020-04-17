package com.giovanildo.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.Clube;
import com.giovanildo.utils.JpaUtils;

public class ClubeService implements CrudService<Clube, Integer> {

	@Override
	public List<Clube> all() {
		return JpaUtils.getEntityManager().createQuery("from Clube", Clube.class).getResultList();
	}

	@Override
	public Clube byId(Integer id) {
		return JpaUtils.getEntityManager().find(Clube.class, id);
	}

	@Override
	public Clube insert(Clube entity) {
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
	public Clube update(Clube entity) {
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
	public void delete(Clube entity) {
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

}

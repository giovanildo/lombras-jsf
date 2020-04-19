package com.giovanildo.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.EAtleta;
import com.giovanildo.utils.JpaUtils;

public class EatletaService implements CrudService<EAtleta, Integer> {

	@Override
	public List<EAtleta> all() {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.createQuery("from EAtleta", EAtleta.class).getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public EAtleta byId(Integer id) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.find(EAtleta.class, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public EAtleta insert(EAtleta entity) {
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
	public EAtleta update(EAtleta entity) {
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
	public void delete(EAtleta entity) {
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
	public EAtleta byString(String string) {
		for (EAtleta daVez : all()) {
			if (daVez.getNome().equals(string)) {
				return daVez;
			}
		}
		return null;
	}

}

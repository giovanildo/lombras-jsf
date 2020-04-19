package com.giovanildo.services.impl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.Competidor;
import com.giovanildo.models.Torneio;
import com.giovanildo.utils.JpaUtils;

public class TorneioService implements CrudService<Torneio, Integer> {

	@Override
	public List<Torneio> all() {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.createQuery("from Torneio", Torneio.class).getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Torneio byId(Integer id) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.find(Torneio.class, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Torneio insert(Torneio entity) {
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			for (Competidor daVez : entity.getCompetidores()) {
				try {
					em.persist(daVez);
				} catch (EntityExistsException e) {
					System.out.println(e.getMessage());
				}
				
			}
			em.getTransaction().commit();
			return entity;
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public Torneio update(Torneio entity) {
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
	public void delete(Torneio entity) {
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
	public Torneio byString(String string) {
		return null;
	}

}

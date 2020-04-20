package com.giovanildo.services.impl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import com.giovanildo.jpa.service.interfaces.CrudService;
import com.giovanildo.models.CompetidorEmCampo;
import com.giovanildo.models.Partida;
import com.giovanildo.utils.JpaUtils;

public class PartidaService implements CrudService<Partida, Integer> {

	@Override
	public List<Partida> all() {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.createQuery("from Partida", Partida.class).getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Partida byId(Integer id) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.find(Partida.class, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Partida insert(Partida entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			for (CompetidorEmCampo daVez : entity.getCompetidoresEmCampo()) {
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
	public Partida update(Partida entity) {
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
	public void delete(Partida entity) {
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
	public Partida byString(String string) {
		return null;
	}

}

package com.giovanildo.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.giovanildo.dao.DAO;
import com.giovanildo.models.EAtleta;

@Named
@RequestScoped
public class EatletaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EAtleta eatleta = new EAtleta();

	public void salvar() {
		System.out.println("salvando " + eatleta.getNome());
		new DAO<EAtleta>(EAtleta.class).adiciona(this.eatleta);
		eatleta = new EAtleta();
	}

	public EAtleta getEatleta() {
		return eatleta;
	}

	public void setEatleta(EAtleta eatleta) {
		this.eatleta = eatleta;
	}

	
}

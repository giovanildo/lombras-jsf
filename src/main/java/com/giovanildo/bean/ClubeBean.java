package com.giovanildo.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.giovanildo.dao.DAO;
import com.giovanildo.models.Clube;

@Named
@RequestScoped
public class ClubeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Clube clube = new Clube();

	public void salvar() {
		System.out.println("salvou " + clube.getNome() + "  " + clube.getNacionalidade());
		new DAO<Clube>(Clube.class).adiciona(this.clube);
		clube = new Clube();
	}

	public Clube getClube() {
		return clube;
	}

	public void setClube(Clube clube) {
		this.clube = clube;
	}
}

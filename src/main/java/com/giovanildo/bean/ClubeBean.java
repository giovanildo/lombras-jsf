package com.giovanildo.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.giovanildo.dao.DAO;
import com.giovanildo.models.Clube;

@Named
@ViewScoped
public class ClubeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Clube clube = new Clube();

	public List<Clube> getClubes() {
		return new DAO<Clube>(Clube.class).listaTodos();
	}

	public void salvar() {
		System.out.println("salvou " + clube.getNome() + "  " + clube.getNacionalidade());
		if (this.clube.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("clube",
					new FacesMessage("O nome do Clube não pode ser em branco"));
			return;
		}
		new DAO<Clube>(Clube.class).adiciona(this.clube);
		clube = new Clube();
	}

	public void validador(FacesContext fc, UIComponent component, Object value) {
		String nome = value.toString();
		if (nome.isEmpty()) {
			throw new ValidatorException(new FacesMessage("Deixa em branco não! Arrombado"));
		}
		for (Clube daVez : getClubes()) {
			if (daVez.getNome().equals(nome)) {
				throw new ValidatorException(new FacesMessage("Esse nome já existe na lista"));
			}
		}
	}

	public Clube getClube() {
		return clube;
	}

	public void setClube(Clube clube) {
		this.clube = clube;
	}
}

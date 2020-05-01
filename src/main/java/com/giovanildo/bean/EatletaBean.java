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
import com.giovanildo.models.EAtleta;

@Named
@ViewScoped
public class EatletaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private EAtleta eatleta = new EAtleta();

	public void salvar() {
		System.out.println("salvando " + eatleta.getNome());
		if (this.eatleta.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("eatleta",
					new FacesMessage("O nome do EAtleta não pode ser em branco"));
			return;
		}
		
		new DAO<EAtleta>(EAtleta.class).adiciona(this.eatleta);
		eatleta = new EAtleta();
	}

	public void validador(FacesContext fc, UIComponent component, Object value) {
		String nome = value.toString();
		if (nome.isEmpty()) {
			throw new ValidatorException(new FacesMessage("O nome não pode ser vazio"));
		}

		for (EAtleta daVez : this.getEatletas()) {
			if (daVez.getNome().equals(nome)) {
				throw new ValidatorException(new FacesMessage("Esse nome já existe"));
			}
		}
	}

	public List<EAtleta> getEatletas() {
		return new DAO<EAtleta>(EAtleta.class).listaTodos();
	}

	public EAtleta getEatleta() {
		return eatleta;
	}

	public void setEatleta(EAtleta eatleta) {
		this.eatleta = eatleta;
	}

}

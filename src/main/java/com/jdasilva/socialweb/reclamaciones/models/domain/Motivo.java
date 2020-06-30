package com.jdasilva.socialweb.reclamaciones.models.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "motivos")
public class Motivo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descripcion;

	public Motivo() {
	}

	public Motivo(@NotNull Long id, String descripcion) {

		this.id = id;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripción(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		// es necesario hacer el toString() para que en la vista Reclamacion en la select
		// de motivos se pueda seleccionar el valor por defecto ya los valores están mapeados a través del id del motivo
		// th:value="{motivo.id}"
		return id.toString();
	}

}

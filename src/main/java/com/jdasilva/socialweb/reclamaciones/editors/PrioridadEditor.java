package com.jdasilva.socialweb.reclamaciones.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jdasilva.socialweb.reclamaciones.models.domain.Prioridad;
import com.jdasilva.socialweb.reclamaciones.models.service.PrioridadService;

@Component
public class PrioridadEditor extends PropertyEditorSupport {

	@Autowired
	private PrioridadService prioridadService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		try {

			Long id = Long.parseLong(text);
			Prioridad prioridad = prioridadService.getById(id);
			setValue(prioridad);

		} catch (Exception e) {
			setValue(null);
		}
	}
}

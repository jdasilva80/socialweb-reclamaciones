package com.jdasilva.socialweb.reclamaciones.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jdasilva.socialweb.reclamaciones.models.domain.Opcion;
import com.jdasilva.socialweb.reclamaciones.models.service.OpcionService;

@Component
public class OpcionEditor extends PropertyEditorSupport {

	@Autowired
	private OpcionService opcionService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		try {

			Long id = Long.parseLong(text);
			Opcion opcion = opcionService.getById(id);
			setValue(opcion);

		} catch (Exception e) {
			setValue(null);
		}
	}
}

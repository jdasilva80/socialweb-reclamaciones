package com.jdasilva.socialweb.reclamaciones.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jdasilva.socialweb.reclamaciones.models.domain.Motivo;
import com.jdasilva.socialweb.reclamaciones.models.service.MotivoService;

@Component
public class MotivosEditor extends PropertyEditorSupport {

	@Autowired
	private MotivoService motivoService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		try {
			Long id = Long.parseLong(text);
			Motivo motivo = motivoService.getById(id);
			setValue(motivo);
		} catch (Exception e) {
			setValue(null);
		}

	}

}

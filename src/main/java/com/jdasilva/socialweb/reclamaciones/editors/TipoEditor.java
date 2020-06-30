package com.jdasilva.socialweb.reclamaciones.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jdasilva.socialweb.reclamaciones.models.domain.Tipo;
import com.jdasilva.socialweb.reclamaciones.models.service.TipoService;

@Component
public class TipoEditor extends PropertyEditorSupport {

	@Autowired
	private TipoService tipoService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		try {

			Long id = Long.parseLong(text);
			Tipo tipo = tipoService.getById(id);
			setValue(tipo);

		} catch (Exception e) {
			setValue(null);
		}
	}
}

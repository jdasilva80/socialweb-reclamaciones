package com.jdasilva.socialweb.reclamaciones.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;

@Component
public class MayusculasEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		setValue(text.toUpperCase());
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import java.io.IOException;
import java.util.List;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.SelectItem;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectonemenu.SelectOneMenuRenderer;
import org.primefaces.util.ComponentUtils;

/**
 *
 * @author Oscar Quiroz
 */
@FacesComponent(createTag=true)
public class PfSelectOneMenuRendered extends SelectOneMenuRenderer  {
    
    @Override
    protected void encodeLabel(final FacesContext context, final SelectOneMenu menu, final List<SelectItem> selectItems)
    throws IOException {
    
        @SuppressWarnings("resource")
	final ResponseWriter writer = context.getResponseWriter();
	final String valueToRender = ComponentUtils.getValueToRender(context, menu);
	if (menu.isEditable()) {
            writer.startElement("input", null);
	
			if (menu.getOnkeydown() != null) {
				writer.writeAttribute("onkeydown", menu.getOnkeydown(), null);
			}
			if (menu.getOnkeyup() != null) {
				writer.writeAttribute("onkeyup", menu.getOnkeyup(), null);
			}
		    this.renderDomEvent(context, menu, "onkeyup", "keyup", "keyup", null);
			writer.endElement("input");
		} else {
			writer.startElement("label", null);
			writer.writeAttribute("id", menu.getClientId(context) + "_label", null);
			writer.writeAttribute("class", SelectOneMenu.LABEL_CLASS, null);
			if (menu.getPlaceholder() != null) {
				writer.writeAttribute("data-placeholder", menu.getPlaceholder(), null);
			}
			writer.write("&nbsp;");
			writer.endElement("label");
		}
    }
}

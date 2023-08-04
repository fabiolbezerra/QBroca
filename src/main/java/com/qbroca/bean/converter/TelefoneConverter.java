package com.qbroca.bean.converter;

import com.qbroca.entity.Produto;
import com.qbroca.util.UtilTexto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "telefoneConverter")
public class TelefoneConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            return null;
        } else {
            return UtilTexto.removeCharacterPhone(string);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        if (obj == null) {
            return null;
        } else {
            return UtilTexto.formatarTelefone(obj.toString().trim());
        }
    }
}

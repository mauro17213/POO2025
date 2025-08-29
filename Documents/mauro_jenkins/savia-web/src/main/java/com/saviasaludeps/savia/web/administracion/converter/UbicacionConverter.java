/**
 * Clase que convierte la selección de un objeto tipo Ubicaciones
 * a ubicacionpara el caso del componente SelectOneMenu de PrimeFaces. 
 * 
 * @author ripalacios
 * @since 17-16-2016
 */
package com.saviasaludeps.savia.web.administracion.converter;

//import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
//import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
//import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
//@FacesConverter("ubicacionConverter")
//public class UbicacionConverter implements Converter {
//
//    private UbicacionRemoto getUbicacionRemoto() throws Exception {
//        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
//    }
//
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        Ubicacion ubi = null;
//        if (value != null) {
//            try {
//                ubi = this.getUbicacionRemoto().consultar(Integer.valueOf(value));
//            } catch (Exception ex) {
//            }
//        }
//        return ubi;
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value == null) {
//            return "";
//        }
//        return String.valueOf(((Ubicacion) value).getId());
//    }
//}

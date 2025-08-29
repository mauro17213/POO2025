package com.saviasaludeps.savia.web.utilidades;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

/*
* Realiza las transformaciones para compatibilidad PF8
 */
public class CompatibilidadPF {

    //Date a LocalDateTime
    public static LocalDateTime convertirDateToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    //Hash String FilterMeta to Filter String Obj
    public static HashMap<String, Object> ConvertirFiltroMetaToHash(Map<String, FilterMeta> filtros) {
        HashMap<String, Object> filtroObj = new HashMap<>();
        filtros.entrySet().forEach(filtroMeta -> {
            if (filtroMeta.getValue().getFilterValue() != null) {
                //2024-03-08 (rpalacic) Quitar espacios al inicio y al final del String
                try {
                    String val = (String) filtroMeta.getValue().getFilterValue();
                    filtroObj.put((filtroMeta.getValue().getField() != null)? filtroMeta.getValue().getField():filtroMeta.getKey(), val.trim());
                } catch (Exception e) {
                    filtroObj.put((filtroMeta.getValue().getField() != null)? filtroMeta.getValue().getField():filtroMeta.getKey(), filtroMeta.getValue().getFilterValue());
                }
            }
        });
        return filtroObj;
    }
    
    //Convierte el HashMap propio de primefaces en uno propio
    public static HashMap<String, String> convertirFiltrosOrdenToHash(Map<String, SortMeta> filtroOrden) {
        HashMap<String, String> filtroFinal = new HashMap<>();
        for (SortMeta orden : filtroOrden.values()) {
            String ordenStr = "DESC";
            if (orden.getOrder().isAscending()) {
                ordenStr = "ASC";
            }
            filtroFinal.put(orden.getField(), ordenStr);
        }
        return filtroFinal;
    }

}

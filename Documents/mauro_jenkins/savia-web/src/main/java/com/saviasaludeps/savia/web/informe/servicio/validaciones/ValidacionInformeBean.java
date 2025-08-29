/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.informe.servicio.validaciones;

import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;
import com.saviasaludeps.savia.web.informe.bean.InformeBean;
import com.saviasaludeps.savia.web.utilidades.DateUtil;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author oquiroz
 */
public class ValidacionInformeBean {

    public static void validarGuardarInforme(InformeBean bean) {
        String regexAlfanumerico = "^[a-zA-Z0-9-_ ]+$";
        Pattern patron = Pattern.compile(regexAlfanumerico);
        if (bean.getObjeto().isProgramado()) {
            if (bean.getObjeto().getPeriodicidad() == null || bean.getObjeto().getPeriodicidad() <= 0) {
                bean.addError("Si el informe es programado debe indicar una periodicad de 1 o más");
            }
            if (bean.getObjeto().getFechaProgramadaInicio() == null) {
                bean.addError("Cuando el informe es programado se requiere ingresar la fecha inicio");
            }
            if (bean.getObjeto().getFechaProgramadoFin() == null) {
                bean.addError("Cuando el informe es programado se requiere ingresar la fecha fin");
            }
            /*if (bean.getObjeto().getFechaProgramadaInicio() != null && bean.getObjeto().getFechaProgramadaInicio().before(new Date())) {
                bean.addError("La fecha programada de inicio debe ser mayor a hoy");
            }*/
            if (!bean.isError() && bean.getObjeto().getFechaProgramadoFin().before(bean.getObjeto().getFechaProgramadaInicio())) {
                bean.addError("La fecha fin no puede ser superior a la fecha inicio");
            }
            if (bean.getObjeto().getTipoPeriodicidad() == -1) {
                bean.addError("Debe ingresar el tipo de periodicidad");
            }
            if (bean.getObjeto().getPeriodicidad() <= -1) {
                bean.addError("Debe ingresar una periodicidad mayor a 0");
            }
        }
        if (bean.getObjeto().getNombreSp() == null || bean.getObjeto().getNombreSp().equals("")) {
            bean.addError("Ingres el nombre del SP");
        }
        if (bean.getObjeto().getDescripcion() == null || bean.getObjeto().getDescripcion().equals("")) {
            bean.addError("Ingrese la descripción");
        }
        if (bean.getObjeto().getGrupo() == null || bean.getObjeto().getGrupo().getId() == null) {
            bean.addError("Elija el grupo al que pertenece el informe");
        }
        if (bean.getObjeto().getNombre() == null || bean.getObjeto().getNombre().equals("")) {
            bean.addError("Debe indicar un nombre al informe");
        }
        if (bean.getObjeto().getNombreArchivo() == null || bean.getObjeto().getNombreArchivo().equals("")) {
            bean.addError("Ingrese el nombre del archivo con que se generara este informe");
        }
        if (!bean.isError() && !patron.matcher(bean.getObjeto().getNombreArchivo()).matches()) {
            bean.addError("El nombre de archivo no puede contener caracteres especiales");
        }

        //Validar parametros
        if (bean.getObjeto().getListaVariables() != null) {
            if (!existeValorEnLista(bean.getObjeto().getListaVariables())) {
                bean.addError("Error no se puede repetir el orden en la lista de parametros");
            }
            //Validar tipos versus parametro ingresado
            for (InfInformeVariable variable : bean.getObjeto().getListaVariables()) {
                if (variable.getValor() != null && !variable.getValor().equals("")) {
                    switch (variable.getTipo()) {
                        case InfInformeVariable.TIPO_TEXTO:
                            break;
                        case InfInformeVariable.TIPO_NUMERO:
                            try {
                                Integer.parseInt(variable.getValor());
                            } catch (NumberFormatException ex) {
                                bean.addError("El parametro ingresado no es de tipo número para la variable: " + variable.getNombre());
                            }
                        break;
                        case InfInformeVariable.TIPO_FECHA:
                            if (!DateUtil.esFechaValida(variable.getValor())) {
                                bean.addError("El parametro ingresado no está en formato fecha yyyy-MM-dd para la variable: " + variable.getNombre());
                            }
                            break;
                        default:
                            break;
                    }
                }
                //Validar campos obligatorios en variables
                if (variable.getTipo() == -1) {
                    bean.addError("Ingrese el tipo para la variable: " + variable.getNombre());
                }
                if (variable.getNombre() == null || variable.getNombre().equals("")) {
                    bean.addError("Variable sin nombre: ");
                }
                //Requiere valor en una variable programada de tipo texto,numero y fecha
                if (bean.getObjeto().isProgramado()) {
                    switch (variable.getTipo()) {
                        case InfInformeVariable.TIPO_TEXTO:
                            if (variable.getValor() == null || variable.getValor().equals("") && !variable.isDinamico()) {
                                bean.addError("Si el informe es programado y el valor de la variable no la ingresa el usuario debe ingresar un valor a las variables no automaticas");
                            }
                            break;
                        case InfInformeVariable.TIPO_NUMERO:
                            if (variable.getValor() == null || variable.getValor().equals("") && !variable.isDinamico()) {
                                bean.addError("Si el informe es programado debe ingresar un valor a las variables no automaticas");
                            }
                            break;
                        case InfInformeVariable.TIPO_FECHA:
                            if (variable.getValor() == null || variable.getValor().equals("") && !variable.isDinamico()) {
                                bean.addError("Si el informe es programado y el valor de la variable no la ingresa el usuario debe ingresar un valor a las variables no automaticas");
                            }
                            break;
                    }
                }
            }
        }
        if (bean.getObjeto().getCarpeta() == null || bean.getObjeto().getCarpeta().equals("") || (validarCarpeta(bean.getObjeto().getCarpeta()) == false)) {
            bean.addError("El informe requiere un nombre de carpeta valido");
        }

        //Validar parametros editar
        if (bean.getObjeto().getListaVariables() != null) {
            if (!existeValorEnLista(bean.getObjeto().getListaVariables())) {
                bean.addError("Error no se puede repetir el orden en la lista de parametros");
            }
            //Validar tipos versus parametro ingresado
            for (InfInformeVariable variable : bean.getObjeto().getListaVariables()) {
                //Si se ingresa un valor validar el tipo
                if (variable.getValor() != null && !variable.getValor().equals("") && !variable.getValor().equals("manual")) {
                    switch (variable.getTipo()) {
                        case InfInformeVariable.TIPO_TEXTO:
                            break;
                        case InfInformeVariable.TIPO_NUMERO:
                    try {
                            Integer.parseInt(variable.getValor());
                        } catch (NumberFormatException ex) {
                            bean.addError("El parametro ingresado no es de tipo número para la variable: " + variable.getNombre());
                        }
                        break;
                        case InfInformeVariable.TIPO_FECHA:
                            if (!DateUtil.esFechaValida(variable.getValor())) {
                                bean.addError("El parametro ingresado no está en formato fecha yyyy-MM-dd para la variable: " + variable.getNombre());
                            }
                            break;
                        default:
                            break;
                    }
                }
                //Validar campos obligatorios en variables
                if (variable.getTipo() == -1) {
                    bean.addError("Ingrese el tipo para la variable: " + variable.getNombre());
                }
                if (variable.getNombre() == null || variable.getNombre().equals("")) {
                    bean.addError("Variable sin nombre: ");
                }
                //Requiere valor en una variable programada de tipo texto,numero y fecha
                if (bean.getObjeto().isProgramado()) {
                    switch (variable.getTipo()) {
                        case InfInformeVariable.TIPO_TEXTO:
                            if (variable.getValor() == null || variable.getValor().equals("")) {
                                bean.addError("Si el informe es programado debe ingresar un valor a las variables no automaticas");
                            }
                            break;
                        case InfInformeVariable.TIPO_NUMERO:
                            if (variable.getValor() == null || variable.getValor().equals("")) {
                                bean.addError("Si el informe es programado debe ingresar un valor a las variables no automaticas");
                            }
                            break;
                        case InfInformeVariable.TIPO_FECHA:
                            if (variable.getValor() == null || variable.getValor().equals("")) {
                                bean.addError("Si el informe es programado debe ingresar un valor a las variables no automaticas");
                            }
                            break;
                    }
                }
            }
        }
    }

    static boolean existeValorEnLista(List<InfInformeVariable> lista) {
        return lista.stream().map(InfInformeVariable::getOrden).distinct().count() == lista.size();
    }

    private static boolean validarCarpeta(String valor) {
        return valor.matches("^[a-zA-Z0-9_/]*$");
    }

}

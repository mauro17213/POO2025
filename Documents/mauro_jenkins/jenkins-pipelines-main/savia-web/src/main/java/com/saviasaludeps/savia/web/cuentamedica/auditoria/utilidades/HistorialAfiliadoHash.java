/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import java.util.Date;
import java.util.HashMap;



/**
 *
 * @author Jorge Eliecer Perez
 */
public class HistorialAfiliadoHash {
    
    private Date fechaConsulta;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private HashMap<Integer, Maestro> hashOrigenAfiliado;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashGrupoPoblacional;

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public HashMap<Integer, Maestro> getHashOrigenAfiliado() {
        return hashOrigenAfiliado;
    }

    public void setHashOrigenAfiliado(HashMap<Integer, Maestro> hashOrigenAfiliado) {
        this.hashOrigenAfiliado = hashOrigenAfiliado;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public HashMap<Integer, Maestro> getHashGrupoPoblacional() {
        return hashGrupoPoblacional;
    }

    public void setHashGrupoPoblacional(HashMap<Integer, Maestro> hashGrupoPoblacional) {
        this.hashGrupoPoblacional = hashGrupoPoblacional;
    }

    public Date getFechaConsulta() {
        if (fechaConsulta == null) {
            fechaConsulta = new Date();
        }
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
    
     public String getEstadoCivil(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Soltero";
                break;
            case "2":
                equivalente = "Casado";
                break;
            case "3":
                equivalente = "Viudo";
                break;
            case "4":
                equivalente = "Divorciado";
                break;
            case "5":
                equivalente = "Unión Libre";
                break;
            case "6":
                equivalente = "Divorciado";
                break;
            case "7":
                equivalente = "No reportado";
                break;
            case "8":
                equivalente = "Otro";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
     
    public String getTipoAfiliado(String valor) {
        String equivalente;
        switch (valor) {
            case "101":
                equivalente = "Cotizante";
                break;
            case "102":
                equivalente = "Beneficiario";
                break;
            case "103":
                equivalente = "Cabeza de Hogar";
                break;
            case "104":
                equivalente = "Adicional";
            break;
            default:
                equivalente = "";
            break;
        }
        return equivalente;
    }
    
    public String getParentescoCotizante(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Cónyuge o compañero(a) permanente";
                break;
            case "2":
                equivalente = "Hijos del cotizante o del compañero(a) permanente, menores de veinticinco años que dependen económicamente del cotizante";
                break;
            case "3":
                equivalente = "Padre o madre del cotizante, cabeza de familia";
                break;
            case "4":
                equivalente = "Hijos de beneficiarios menores de 25 años o con incapacidad permanente";
                break;
            case "5":
                equivalente = "Hasta tercer grado de consanguinidad - Hijos de menores de 25 años o cualquier edad con incapacidad permanente";
                break;
            case "7":
                equivalente = "Padres que dependan económicamente de alguno de los cónyuges o compañero(a) permanente del cotizante, cuando ambos cotizan, cabeza de familia";
                break;
            case "8":
                equivalente = "Afiliado adicional";
                break;
            case "9":
                equivalente = "Hijos del cotizante o del compañero(a) permanente, de cualquier edad si tienen incapacidad permanente y depende económicamente del cotizante, cabeza de familia";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public String getRegimen(String id) {
        String resultado;
        switch (id) {
            case "1":
                resultado = "Subsidiado";
                break;
            case "2":
                resultado = "Contributivo";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }
    
    public String getCategoriaIbc(String valor) {
        String equivalente;
        switch (valor) {
            case "101":
                equivalente = "Categoría A";
                break;
            case "102":
                equivalente = "Categoría B";
                break;
            case "103":
                equivalente = "Categoría C";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public String getZona(String valor) {
        String equivalente;
        switch (valor) {
            case "R":
                equivalente = "Rural";
                break;
            case "U":
                equivalente = "Urbana";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public String getModeloLiquidacion(String valor) {
        String equivalente;
        switch (valor) {
            case "0":
                equivalente = "CAPITA";
                break;
            case "1":
                equivalente = "EVENTO";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public String getGrupoEtnico(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Indígena";
                break;
            case "2":
                equivalente = "ROM (Gitanos)";
                break;
            case "3":
                equivalente = "Raizal";
                break;
            case "4":
                equivalente = "Palenquero san jacinto";
                break;
            case "5":
                equivalente = "Afrocolombiano";
                break;
            case "6":
                equivalente = "Otras";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public String getNivelSisben(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Nivel 1";
                break;
            case "2":
                equivalente = "Nivel 2";
                break;
            case "3":
                equivalente = "Nivel 3";
                break;
            case "N":
                equivalente = "No Aplica";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public String getTipoDiscapacidad(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Física";
                break;
            case "2":
                equivalente = "Neuro-Sensorial";
                break;
            case "3":
                equivalente = "Mental";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public String getEstadoPortabilidad(int valor) {
        String equivalente;
        switch (valor) {
            case 1:
                equivalente = "En Trámite";
                break;
            case 2:
                equivalente = "Aprobada";
                break;
            case 3:
                equivalente = "Rechazada";
                break;
            case 4:
                equivalente = "Cancelada x Usuario";
                break;
            case 5:
                equivalente = "Finalizada";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public String getTipoPortabilidad(int valor) {
        String equivalente;
        switch (valor) {
            case 1:
                equivalente = "Ocasional";
                break;
            case 2:
                equivalente = "Temporal";
                break;
            case 3:
                equivalente = "Permanente";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
     public String getOrigenAfiliado(Integer id) {
        try {
            return hashOrigenAfiliado.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
     
     
     
    public String getEstadoAfiliacion(Integer id) {
        try {
            return hashEstadosAfiliacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getGrupoPoblacional(Integer id) {
        try {
            return hashGrupoPoblacional.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getTipoGenero(Integer id) {
        try {
            return hashTiposGenero.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getTipoDocumento(Integer id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
    
}

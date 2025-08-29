/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.utilidades;

import com.saviasaludeps.savia.dominio.cargas.CarCargaRegistro;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author aguevara
 */
public class RtConstantesCargas {
    
    

    public static String getEstado(Integer idEstado) {
        switch (idEstado) {
            case 0: return "En Cola";
            case 1: return "En Proceso";
            case 2: return "Procesado";
            case 3: return "Fallido";
            default: return "Desconocido"; // Para manejar valores inesperados
        }

    }
    
    public String getTipoCarga(Integer idEstado) {
        switch (idEstado) {
            case 1: return "Tipo 1";
            case 2: return "Tipo 2";
            case 3: return "Tipo 3";
            case 4: return "Tipo 4";
            case 5: return "Tipo 5";
            default: return "Desconocido"; // Para manejar valores inesperados
        }

    }
    
    
   
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.actualizacion;

import com.saviasaludeps.savia.web.singleton.MaestroSingle;

/**
 *
 * @author stive
 */
public class HiloMaestro implements Runnable {

    @Override
    public void run() {
        try {
            //Carga Inicial
            do {                
                try {
                    MaestroSingle.getInstance().actualizar();
                    Thread.sleep(5 * 1000); //Esperar 5 segundos
                } catch (InterruptedException e) {
                }
            } while (MaestroSingle.getInstance().isEmpty());
            //Carga recurrente
            while (true) {                
                try {
                    Thread.sleep(1440 * 60 * 1000); //Esperar 24 horas o 1440 minutos
                    MaestroSingle.getInstance().actualizar();
                } catch (InterruptedException e) {
                }
            }
        } catch (Exception e) {
        }
    }
    
}

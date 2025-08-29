
package com.saviasaludeps.savia.web.actualizacion;

import com.saviasaludeps.savia.web.singleton.UbicacionSingle;

/**
 *
 * @author rpalacios
 */
public class HiloUbicacion implements Runnable {

    @Override
    public void run() {
        try {
            //Carga inicial
            do {
                try {
//                    System.out.println(" - " + num + " INICIO " + format.format(new Date()));
                    UbicacionSingle.getInstance().actualizar();
                    //Esperar 5 segundos
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException ex) {
                }
            } while (UbicacionSingle.getInstance().isEmpty());
            //Carga recurrente
            while (true) {
                try {
                    //Esperar 60 minutos
                    Thread.sleep(60 * 60 * 1000);
//                    System.out.println(" - " + num + " ACTUALIZA " + format.format(new Date()));
                    UbicacionSingle.getInstance().actualizar();
                } catch (InterruptedException ex) {
                }
            }
        } catch (Exception ex) {
        }
    }

}

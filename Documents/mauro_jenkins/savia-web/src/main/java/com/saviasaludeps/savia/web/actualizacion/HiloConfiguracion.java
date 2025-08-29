package com.saviasaludeps.savia.web.actualizacion;

import com.saviasaludeps.savia.web.utilidades.PropApl;

/**
 *
 * @author rpalacios
 */
public class HiloConfiguracion implements Runnable {

    @Override
    public void run() {
        try {
            //Carga inicial
            do {
                try {
                    PropApl.getInstance().actualizar();
                    //Esperar 5 segundos
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException ex) {
                }
            } while (PropApl.getInstance().isEmpty());
            //Carga recurrente
            while (true) {
                try {
                    //Esperar 30 minutos
                    Thread.sleep(30 * 60 * 1000);
                    PropApl.getInstance().actualizar();
                } catch (InterruptedException ex) {
                }
            }
        } catch (Exception ex) {
        }

    }

}

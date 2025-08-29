package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PePrograma;

public interface AuAnexo3SolicitudItemRemoto {

    /**
     * Crear un objeto Anexo3Item
     *
     * @param auAnexo3
     * @return
     * @throws Exception
     */
    AuAnexo3 insertar(AuAnexo3 auAnexo3) throws Exception;

    /**
     * Cambia estado y registra bitacora de item direccionado
     *
     * @param item
     * @param prestadorSede
     * @param programa
     * @return item con estado actualizado
     * @throws java.lang.Exception
     */
    AuAnexo3Item cambiarEstadoItemDireccionado(AuAnexo3Item item, CntPrestadorSede prestadorSede, PePrograma programa) throws Exception;

    /**
     * Actualiza el estado cuando aplica para seguimiento
     *
     * @param item
     * @return
     * @throws Exception
     */
    AuAnexo3Item cambiarEstadoItemSeguimiento(AuAnexo3Item item) throws Exception;

}

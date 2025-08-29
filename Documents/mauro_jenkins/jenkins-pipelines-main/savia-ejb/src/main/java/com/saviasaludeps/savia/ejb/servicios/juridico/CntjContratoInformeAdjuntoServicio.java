package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjContratoInforme;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoInformeAdjunto;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoInformeAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoInformes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoInformeAdjuntoRemoto;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author StivenGV
 */
@Stateless
@Remote(CntjContratoInformeAdjuntoRemoto.class)
public class CntjContratoInformeAdjuntoServicio extends GenericoServicio implements CntjContratoInformeAdjuntoRemoto {

    @Override
    public int insertar(CntjContratoInformeAdjunto objeto) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CntjContratoInformeAdjunto objeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CntjContratoInformeAdjunto> consultarTodosPorIdContrato(int idInforme) throws Exception {
        List<CntjContratoInformeAdjunto> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.cntjContratoInformesId.id =  "+idInforme);
            List<CntjContratoInformeAdjuntos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            if (list != null) {
                for (CntjContratoInformeAdjuntos adjunto : list) {
                    listResult.add(castEntidadNegocio(adjunto));
                }
            }
            
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    
    private CntjContratoInformeAdjuntos castNegocioEntidad(CntjContratoInformeAdjunto negocio){
        CntjContratoInformeAdjuntos entidad = new CntjContratoInformeAdjuntos();
        entidad.setCntjContratoInformesId(new CntjContratoInformes(negocio.getCntjContratoInformeId().getId()));
        entidad.setMaeTipoArchivoId(negocio.getMaeTipoArchivoId());
        entidad.setMaeTipoArchivoCodigo(negocio.getMaeTipoArchivoCodigo());
        entidad.setMaeTipoArchivoValor(negocio.getMaeTipoArchivoValor());
        entidad.setRuta(negocio.getRuta());
        entidad.setNombre(negocio.getNombre());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setExiste(negocio.isExiste());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }
    
    private CntjContratoInformeAdjunto castEntidadNegocio(CntjContratoInformeAdjuntos entidad){
        CntjContratoInformeAdjunto negocio = new CntjContratoInformeAdjunto();
        negocio.setId(entidad.getId());
        negocio.setCntjContratoInformeId(new CntjContratoInforme(entidad.getCntjContratoInformesId().getId()));
        negocio.setMaeTipoArchivoId(entidad.getMaeTipoArchivoId());
        negocio.setMaeTipoArchivoCodigo(entidad.getMaeTipoArchivoCodigo());
        negocio.setMaeTipoArchivoValor(entidad.getMaeTipoArchivoValor());
        negocio.setRuta(entidad.getRuta());
        negocio.setNombre(entidad.getNombre());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setExiste(entidad.getExiste());
        if (!Files.exists(Paths.get(entidad.getRuta() + entidad.getArchivo()))) {
            negocio.setExiste(false);
        }
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }
    
}

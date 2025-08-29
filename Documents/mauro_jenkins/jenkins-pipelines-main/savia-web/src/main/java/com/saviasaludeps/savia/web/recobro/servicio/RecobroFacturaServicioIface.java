package com.saviasaludeps.savia.web.recobro.servicio;
import com.saviasaludeps.savia.web.recobro.bean.RecobroFacturaBean;

/**
 *
 * @author sgiraldov
 */
public interface RecobroFacturaServicioIface {

    /**
     * Método de acciones central
     *
     * @author Stiven Giraldo
     * @param bean
     */
    void Accion(RecobroFacturaBean bean);
    
    void listarGrupos(RecobroFacturaBean bean); 
    
    void listarIPSMasivo(RecobroFacturaBean bean);
    
    void listarGrupoMasivo(RecobroFacturaBean bean);
    
    void listarIPS(RecobroFacturaBean bean); 
    
    void elegirIPS(RecobroFacturaBean bean); 
    
    void elegirGrupo(RecobroFacturaBean bean);
    
    void seleccionarPePrograma(RecobroFacturaBean bean);
    
    void seleccionarPeProgramaMasivo(RecobroFacturaBean bean);
    
    void actualizarEstadoFactura(RecobroFacturaBean bean);
    /*1
    void 
    
     */
    /**
     * Método para realizar carga inicial de información
     *
     * @author Stiven Giraldo
     * @param bean
     */
    void cargaInicial(RecobroFacturaBean bean);

}

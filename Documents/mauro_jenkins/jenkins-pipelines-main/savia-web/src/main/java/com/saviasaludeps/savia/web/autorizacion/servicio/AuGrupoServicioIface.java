package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoUsuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.autorizacion.bean.GrupoBean;

public interface AuGrupoServicioIface {

    void Accion(GrupoBean bean);
    
    void cargaInicial(GrupoBean bean);
    
    void listarAuditores(GrupoBean bean);
    
    void borrarAuditor(int id);
    
    void listarSedes(GrupoBean bean);
    
    void borrarSede(int id);
    
    void borrarDiagnostico(int id);
    
    void borrarTecnologia(int id);
    
    void borrarRegion(int id);
    
    void listarCasos(GrupoBean bean);
    
    void listarGrupoUsuario(GrupoBean bean);
    
    void listarGrupoSede(GrupoBean bean);
    
    boolean validarAuditor(Usuario usuario, GrupoBean bean);
    
    boolean validarAuditorTipo(AuGrupoUsuario grupo);
    
    void listarGrupoDiagnostico(GrupoBean bean);
    
    void listarGrupoTecnologia(GrupoBean bean);
    
    boolean validarSede(CntPrestadorSede sede, GrupoBean bean);
    
    boolean validarTecnologia(int IdTecnologia, int tipoTecnologia, GrupoBean bean);
    
    boolean validarDiagnostico(MaDiagnostico diagnostico, GrupoBean bean);
    
    void listarGrupoAuditores(GrupoBean bean);
    
    boolean validarGenerico(GrupoBean bean);
    
    boolean validarTutela(GrupoBean bean);
    
    boolean validarPbs(GrupoBean bean);
    
    boolean validarPrograma(int idPrograma, GrupoBean bean);
    
    void listarProgramas(GrupoBean bean);
    
    boolean validarTecnologiaTieneSeguimiento(GrupoBean bean);
    
    void listarGrupoProgramas(GrupoBean bean);
    
    void borrarPrograma(int id);
    
    void reordenar(int posicionMover, int posicionMovida, AuGrupo grupoMovido, AuGrupo grupoMover, GrupoBean bean);

}

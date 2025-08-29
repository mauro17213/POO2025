/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuGrupo extends Auditoria {

    //Static 
    private static final String NO_TUTELA = "No Tutela";
    private static final String TUTELA = "Tutela";
    private static final String NO_PBS = "No PBS";
    private static final String PBS = "PBS";

    private Integer id;
    private String nombre;
    private String descripcion;
    private int orden;
    private boolean activo;
    private boolean soloGrupo;
    private Integer ultimoUsuarioMedicoId;
    private Integer ultimoUsuarioEnfermeroId;
    private Integer ultimoUsuarioAuxiliarId;
    private Integer maeAmbitoId;
    private String maeAmbitoCodigo;
    private String maeAmbitoValor;
    private boolean tutela;
    private boolean pbs;
    private boolean generico;
    private boolean esTecnologia;
    private boolean esInsumo;
    private boolean esMedicamento;
    private boolean esPaquete;
    private Boolean aplicaSeguimiento;
    private List<AuAnexo3Item> auAnexo3ItemList;
    private List<AuGrupoRegion> auGrupoRegionList;
    private List<AuGrupoUsuario> auGrupoUsuarioList;
    private List<Usuario> gnUsuarioList;
    private List<AuGrupoPrograma> auGrupoProgramasList;
    private List<AuGrupoDiagnostico> auGrupoDiagnosticoList;
    private List<AuGrupoHistorico> auGrupoHistoricoList;
    private List<AuGrupoSede> auGrupoSedeList;
    private List<AuGrupoTecnologia> auGrupoTecnologiaList;

    private HashMap<Integer, AuGrupoDiagnostico> hashGrupoDiagnosticos;
    private HashMap<Integer, AuGrupoSede> hashGrupoSedes;
    private HashMap<Integer, AuGrupoRegion> hashGrupoRegiones;
    private HashMap<Integer, AuGrupoTecnologia> hashGrupoTecnologias;

    //Objetos auxiliares
    private int idRegion;
    private int idAuditor;
    private AuGrupoUsuario auditor;

    public AuGrupo() {
        this.gnUsuarioList = new ArrayList();
        this.auGrupoDiagnosticoList = new ArrayList();
        this.auGrupoSedeList = new ArrayList();
    }

    public AuGrupo(Integer id) {
        this.id = id;
    }

    public AuGrupo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isSoloGrupo() {
        return soloGrupo;
    }

    public void setSoloGrupo(boolean soloGrupo) {
        this.soloGrupo = soloGrupo;
    }

    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public boolean isGenerico() {
        return generico;
    }

    public void setGenerico(boolean generico) {
        this.generico = generico;
    }

    public boolean isTutela() {
        return tutela;
    }

    public void setTutela(boolean tutela) {
        this.tutela = tutela;
    }

    public boolean isPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public boolean isEsTecnologia() {
        return esTecnologia;
    }

    public void setEsTecnologia(boolean esTecnologia) {
        this.esTecnologia = esTecnologia;
    }

    public boolean isEsInsumo() {
        return esInsumo;
    }

    public void setEsInsumo(boolean esInsumo) {
        this.esInsumo = esInsumo;
    }

    public boolean isEsMedicamento() {
        return esMedicamento;
    }

    public void setEsMedicamento(boolean esMedicamento) {
        this.esMedicamento = esMedicamento;
    }

    public boolean isEsPaquete() {
        return esPaquete;
    }

    public void setEsPaquete(boolean esPaquete) {
        this.esPaquete = esPaquete;
    }

    public List<AuAnexo3Item> getAuAnexo3ItemList() {
        return auAnexo3ItemList;
    }

    public void setAuAnexo3ItemList(List<AuAnexo3Item> auAnexo3ItemList) {
        this.auAnexo3ItemList = auAnexo3ItemList;
    }

    public List<AuGrupoRegion> getAuGrupoRegionList() {
        return auGrupoRegionList;
    }

    public void setAuGrupoRegionList(List<AuGrupoRegion> auGrupoRegionList) {
        this.auGrupoRegionList = auGrupoRegionList;
    }

    public List<AuGrupoUsuario> getAuGrupoUsuarioList() {
        return auGrupoUsuarioList;
    }

    public void setAuGrupoUsuarioList(List<AuGrupoUsuario> auGrupoUsuarioList) {
        this.auGrupoUsuarioList = auGrupoUsuarioList;
    }

    public List<Usuario> getGnUsuarioList() {
        return gnUsuarioList;
    }

    public void setGnUsuarioList(List<Usuario> gnUsuarioList) {
        this.gnUsuarioList = gnUsuarioList;
    }

    public List<AuGrupoDiagnostico> getAuGrupoDiagnosticoList() {
        return auGrupoDiagnosticoList;
    }

    public void setAuGrupoDiagnosticoList(List<AuGrupoDiagnostico> auGrupoDiagnosticoList) {
        this.auGrupoDiagnosticoList = auGrupoDiagnosticoList;
    }

    public List<AuGrupoHistorico> getAuGrupoHistoricoList() {
        return auGrupoHistoricoList;
    }

    public void setAuGrupoHistoricoList(List<AuGrupoHistorico> auGrupoHistoricoList) {
        this.auGrupoHistoricoList = auGrupoHistoricoList;
    }

    public List<AuGrupoSede> getAuGrupoSedeList() {
        return auGrupoSedeList;
    }

    public void setAuGrupoSedeList(List<AuGrupoSede> auGrupoSedeList) {
        this.auGrupoSedeList = auGrupoSedeList;
    }

    public List<AuGrupoTecnologia> getAuGrupoTecnologiaList() {
        return auGrupoTecnologiaList;
    }

    public void setAuGrupoTecnologiaList(List<AuGrupoTecnologia> auGrupoTecnologiaList) {
        this.auGrupoTecnologiaList = auGrupoTecnologiaList;
    }

    public List<AuGrupoPrograma> getAuGrupoProgramasList() {
        return auGrupoProgramasList;
    }

    public void setAuGrupoProgramasList(List<AuGrupoPrograma> auGrupoProgramasList) {
        this.auGrupoProgramasList = auGrupoProgramasList;
    }

    public HashMap<Integer, AuGrupoDiagnostico> getHashGrupoDiagnosticos() {
        return hashGrupoDiagnosticos;
    }

    public AuGrupoDiagnostico getGrupoDiagnostico(Integer id) {
        try {
            return hashGrupoDiagnosticos.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void setHashGrupoDiagnosticos(HashMap<Integer, AuGrupoDiagnostico> hashGrupoDiagnosticos) {
        this.hashGrupoDiagnosticos = hashGrupoDiagnosticos;
    }

    public HashMap<Integer, AuGrupoSede> getHashGrupoSedes() {
        return hashGrupoSedes;
    }

    public AuGrupoSede getGrupoSede(Integer id) {
        try {
            return hashGrupoSedes.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void setHashGrupoSedes(HashMap<Integer, AuGrupoSede> hashGrupoSedes) {
        this.hashGrupoSedes = hashGrupoSedes;
    }

    public HashMap<Integer, AuGrupoRegion> getHashGrupoRegiones() {
        return hashGrupoRegiones;
    }

    public AuGrupoRegion getGrupoRegion(Integer id) {
        try {
            return hashGrupoRegiones.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void setHashGrupoRegiones(HashMap<Integer, AuGrupoRegion> hashGrupoRegiones) {
        this.hashGrupoRegiones = hashGrupoRegiones;
    }

    public HashMap<Integer, AuGrupoTecnologia> getHashGrupoTecnologias() {
        return hashGrupoTecnologias;
    }

    public AuGrupoTecnologia getGrupoTecnologia(Integer id) {
        try {
            return hashGrupoTecnologias.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void setHashGrupoTecnologias(HashMap<Integer, AuGrupoTecnologia> hashGrupoTecnologias) {
        this.hashGrupoTecnologias = hashGrupoTecnologias;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getIdAuditor() {
        return idAuditor;
    }

    public void setIdAuditor(int idAuditor) {
        this.idAuditor = idAuditor;
    }

    public AuGrupoUsuario getAuditor() {
        return auditor;
    }

    public void setAuditor(AuGrupoUsuario auditor) {
        this.auditor = auditor;
    }

    public String obtenerNombreTutela() {
        if (isTutela()) {
            return TUTELA;
        } else {
            return NO_TUTELA;
        }
    }

    public String obtenerNombrePbs() {
        if (isPbs()) {
            return PBS;
        } else {
            return NO_PBS;
        }
    }

    public String obtenerGenerico() {
        return obtenerBoolean(isGenerico());
    }

    public String obtenerActivo() {
        return obtenerBoolean(isActivo());
    }

    public String obtenerEsTecnologia() {
        return obtenerBoolean(isEsTecnologia());
    }

    public String obtenerEsMedicamento() {
        return obtenerBoolean(isEsMedicamento());
    }

    public String obtenerEsPaquete() {
        return obtenerBoolean(isEsPaquete());
    }

    public String obtenerEsInsumo() {
        return obtenerBoolean(isEsInsumo());
    }

    public String obtenerAplicaSeguimiento() {
        return obtenerBoolean(getAplicaSeguimiento());
    }

    public String obtenerBoolean(boolean valor) {
        if (valor) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public String obtenerSoloGrupo() {
        return obtenerBoolean(isSoloGrupo());
    }

    public Integer getUltimoUsuarioMedicoId() {
        return ultimoUsuarioMedicoId;
    }

    public void setUltimoUsuarioMedicoId(Integer ultimoUsuarioMedicoId) {
        this.ultimoUsuarioMedicoId = ultimoUsuarioMedicoId;
    }

    public Integer getUltimoUsuarioEnfermeroId() {
        return ultimoUsuarioEnfermeroId;
    }

    public void setUltimoUsuarioEnfermeroId(Integer ultimoUsuarioEnfermeroId) {
        this.ultimoUsuarioEnfermeroId = ultimoUsuarioEnfermeroId;
    }

    public Integer getUltimoUsuarioAuxiliarId() {
        return ultimoUsuarioAuxiliarId;
    }

    public void setUltimoUsuarioAuxiliarId(Integer ultimoUsuarioAuxiliarId) {
        this.ultimoUsuarioAuxiliarId = ultimoUsuarioAuxiliarId;
    }

    public Boolean getAplicaSeguimiento() {
        return aplicaSeguimiento;
    }

    public void setAplicaSeguimiento(Boolean aplicaSeguimiento) {
        this.aplicaSeguimiento = aplicaSeguimiento;
    }

    @Override
    public String toString() {
        return "AuGrupo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", orden=" + orden + ", activo=" + activo + ", soloGrupo=" + soloGrupo + ", ultimoUsuarioMedicoId=" + ultimoUsuarioMedicoId + ", ultimoUsuarioEnfermeroId=" + ultimoUsuarioEnfermeroId + ", ultimoUsuarioAuxiliarId=" + ultimoUsuarioAuxiliarId + ", maeAmbitoId=" + maeAmbitoId + ", maeAmbitoCodigo=" + maeAmbitoCodigo + ", maeAmbitoValor=" + maeAmbitoValor + ", tutela=" + tutela + ", pbs=" + pbs + ", generico=" + generico + ", esTecnologia=" + esTecnologia + ", esInsumo=" + esInsumo + ", esMedicamento=" + esMedicamento + ", esPaquete=" + esPaquete + ", idRegion=" + idRegion + ", idAuditor=" + idAuditor + ", auditor=" + auditor + '}';
    }

}

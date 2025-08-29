/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author acuartas
 */
public class GatTiquete extends Auditoria {
    
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_LLAMADO = 1;
    public static final int ESTADO_FINALIZADO = 2;
    public static final int ESTADO_ABANDONADO = 3;

    private Integer id;
    private int maeTipoServicio;
    private String maeTipoServicioCodigo;
    private String maeTipoServicioValor;
    private String numero;
    private Integer estado;
    private Date fechaHoraLlamado;
    private Date fechaHoraAtendido;
    private Date fechaHoraFinaliza;
    private Date fechaHoraAbandona;
    private Boolean prioritario;
    private List<GatAtencion> gatAtencionesList;
    private List<GatTiketeLlamado> gatTiketeLlamadosList;
    private GatUsuario gatUsuario;
    private GnSede gnSede;
    
    //Variables auxiliares
    private boolean afiliado;

    public GatTiquete() {
        this.estado = ESTADO_PENDIENTE;
    }

    public GatTiquete(Integer id) {
        this.id = id;
    }
    
    public GatTiquete(Integer id, String numero) {
        this.id = id;
        this.numero = numero;
    }
    
    public GatTiquete(Integer id, String numero, int maeTipoServicio) {
        this.id = id;
        this.numero = numero;
        this.maeTipoServicio = maeTipoServicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoServicio() {
        return maeTipoServicio;
    }

    public void setMaeTipoServicio(int maeTipoServicio) {
        this.maeTipoServicio = maeTipoServicio;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaHoraLlamado() {
        return fechaHoraLlamado;
    }

    public void setFechaHoraLlamado(Date fechaHoraLlamado) {
        this.fechaHoraLlamado = fechaHoraLlamado;
    }

    public Date getFechaHoraAtendido() {
        return fechaHoraAtendido;
    }

    public void setFechaHoraAtendido(Date fechaHoraAtendido) {
        this.fechaHoraAtendido = fechaHoraAtendido;
    }

    public Date getFechaHoraFinaliza() {
        return fechaHoraFinaliza;
    }

    public void setFechaHoraFinaliza(Date fechaHoraFinaliza) {
        this.fechaHoraFinaliza = fechaHoraFinaliza;
    }

    public Date getFechaHoraAbandona() {
        return fechaHoraAbandona;
    }

    public void setFechaHoraAbandona(Date fechaHoraAbandona) {
        this.fechaHoraAbandona = fechaHoraAbandona;
    }

    public Boolean getPrioritario() {
        if (prioritario == null) {
            this.prioritario = false;
        }
        return prioritario;
    }

    public void setPrioritario(Boolean prioritario) {
        this.prioritario = prioritario;
    }

    public List<GatAtencion> getGatAtencionesList() {
        return gatAtencionesList;
    }

    public void setGatAtencionesList(List<GatAtencion> gatAtencionesList) {
        this.gatAtencionesList = gatAtencionesList;
    }

    public List<GatTiketeLlamado> getGatTiketeLlamadosList() {
        return gatTiketeLlamadosList;
    }

    public void setGatTiketeLlamadosList(List<GatTiketeLlamado> gatTiketeLlamadosList) {
        this.gatTiketeLlamadosList = gatTiketeLlamadosList;
    }

    public GatUsuario getGatUsuario() {
        return gatUsuario;
    }

    public void setGatUsuario(GatUsuario gatUsuario) {
        this.gatUsuario = gatUsuario;
    }

    public GnSede getGnSede() {
        return gnSede;
    }

    public void setGnSede(GnSede gnSede) {
        this.gnSede = gnSede;
    }

    public boolean isAfiliado() {
        return afiliado;
    }

    public void setAfiliado(boolean afiliado) {
        this.afiliado = afiliado;
    }
    
    //Metodos auxiliares
    public String getPrioritarioStr() {
        if (getPrioritario() == null) {
            setPrioritario(false);
        }
        if (getPrioritario()) {
            return "Si";
        } else {
            return "No";
        }
    }

    @Override
    public String toString() {
        return "GatTiquete{" + "id=" + id + ", maeTipoServicio=" + maeTipoServicio + ", maeTipoServicioCodigo=" + maeTipoServicioCodigo + ", maeTipoServicioValor=" + maeTipoServicioValor + ", numero=" + numero + ", estado=" + estado + ", fechaHoraLlamado=" + fechaHoraLlamado + ", fechaHoraAtendido=" + fechaHoraAtendido + ", fechaHoraFinaliza=" + fechaHoraFinaliza + ", fechaHoraAbandona=" + fechaHoraAbandona + ", gatUsuario=" + gatUsuario + ", gnSede=" + gnSede + ", afiliado=" + afiliado + '}';
    }
    
}

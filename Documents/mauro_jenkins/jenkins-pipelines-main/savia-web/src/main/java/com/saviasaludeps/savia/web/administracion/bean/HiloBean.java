package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.generico.Hilo;
import com.saviasaludeps.savia.web.administracion.servicio.HiloServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class HiloBean extends Url {

    private HiloServicioIface hiloServicio;
    private List<Hilo> registros;
    private Hilo objeto;

    public HiloBean() {
        this.objeto = new Hilo();
        this.registros = new ArrayList<>();
        Modulo mod = super.validarModulo(Modulo.ID_HILOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
        }
    }

    @PostConstruct
    public void postConstruct() {
        listar();
    }
    
    public void refrescar(){
        super.setAccion(ACCION_LISTAR);
        getHiloServicio().Accion(this);
    }

    private void listar() {
        super.setAccion(ACCION_LISTAR);
        getHiloServicio().Accion(this);
        procesoFinal();
    }

    public void gestionarHilos() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getHiloServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmHilos");
        PrimeFaces.current().executeScript("PF('dialogoHilos').show()");
        procesoFinal();
    }

    public void terminarHilo(int id) {
        setObjeto(new Hilo(id));
        super.setAccion(ACCION_BORRAR);
        getHiloServicio().Accion(this);
        refrescarHilos();
        procesoFinal();
    }

    public void refrescarHilos() {
        super.setAccion(ACCION_LISTAR);
        getHiloServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmHilos");
    }

    public HiloServicioIface getHiloServicio() {
        return hiloServicio;
    }

    public void setHiloServicio(HiloServicioIface hiloServicio) {
        this.hiloServicio = hiloServicio;
    }

    public List<Hilo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Hilo> registros) {
        this.registros = registros;
    }

    public Hilo getObjeto() {
        return objeto;
    }

    public void setObjeto(Hilo objeto) {
        this.objeto = objeto;
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_BORRAR:
                    crearLog("Hilos - Detener", getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    crearLog("Hillos - listar", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmHilos");
                    break;
            }
        }
        generarMensajes();
    }

}

package com.saviasaludeps.savia.dominio.recobro;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class RcoGrupo extends Auditoria {

    public static final int TIPO_CAPITA = 0;
    public static final int TIPO_PGP = 1;

    private Integer id;
    private String nombre;
    private int tipo;
    private PePrograma peProgramaId;
    private String descripcion;
    private List<RcoGrupoUsuario> rcoGrupoUsuariosList;
    private Usuario usuario;
    private List<RcoGrupoUsuario> listaRcoGrupoUsuario;

    public RcoGrupo() {
    }

    public RcoGrupo(Integer id) {
        this.id = id;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PePrograma getPeProgramaId() {
        return peProgramaId;
    }

    public void setPeProgramaId(PePrograma peProgramaId) {
        this.peProgramaId = peProgramaId;
    }

    public List<RcoGrupoUsuario> getRcoGrupoUsuariosList() {
        return rcoGrupoUsuariosList;
    }

    public List<RcoGrupoUsuario> getListaRcoGrupoUsuario() {
        if (listaRcoGrupoUsuario == null) {
            listaRcoGrupoUsuario = new ArrayList<>();
        }
        return listaRcoGrupoUsuario;
    }

    public void setlistaRcoGrupoUsuario(List<RcoGrupoUsuario> listaRcoGrupoUsuario) {
        this.listaRcoGrupoUsuario = listaRcoGrupoUsuario;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "RcoGrupo{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", descripcion=" + descripcion + ", peProgramaId=" + peProgramaId + '}';
    }

    //Metodo Auxiliares
    public String getTipoStr() {
        String texto = "CAPITA";
        if (getTipo() == 1) {
            texto = "PGP";
        }
        return texto;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import java.util.List;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.administracion.Login;
import com.saviasaludeps.savia.dominio.administracion.UsuarioRol;
import com.saviasaludeps.savia.negocio.administracion.ConexionRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnRolesUsuario;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.Encrypt;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;

/**
 *
 * @author ripalacios
 */
@Stateless
@Remote(ConexionRemoto.class)
public class ConexionServicio extends GenericoServicio implements ConexionRemoto {

    @Override
    public boolean conectar(Login bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desconectar(Login bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public Usuario validarConexion(Login obj) throws Exception {
        Usuario usu = null;
        try {
            String sql = "FROM GnUsuarios AS us "
                    + "WHERE us.usuario = :usuario "
                    + "AND us.contrasena = :contrasena "
                    + "AND us.activo = :usuario_activo "
                    + "AND (us.bloqueado = NULL OR us.bloqueado = :usuario_bloqueado) "
                    + "AND us.gnEmpresasId.activa = :empresa_activa ";
            Query query = getEntityManager().createQuery(sql)
                    .setParameter("usuario", obj.getUsuario().getUsuario())
                    .setParameter("contrasena", Encrypt.sha512(obj.getUsuario().getContrasena()))
                    .setParameter("usuario_activo", true)
                    .setParameter("usuario_bloqueado", false)
                    .setParameter("empresa_activa", true);
            List<GnUsuarios> list = query.getResultList();
            if (list != null && !list.isEmpty()) {
                GnUsuarios perUsuario = list.get(0);
                usu = UsuarioServicio.castEntidadNegocio(perUsuario);
                List<UsuarioRol> listaRoles = new ArrayList();
                for(GnRolesUsuario rolPer : perUsuario.getGnRolesUsuarioList()){
                    UsuarioRol rolObj = new UsuarioRol();
                    rolObj.setId(rolPer.getId());
                    rolObj.setRol(RolServicio.castEntidadNegocio(rolPer.getGnRolesId()));
                    listaRoles.add(rolObj);
                }
                usu.setListaRoles(listaRoles);
                usu.setEmpresa(EmpresaServicio.castEntidadNegocio(perUsuario.getGnEmpresasId()));
            } else {
                usu = null;
            }
        } catch (NoResultException e) {
            usu = null;
        } catch (Exception e) {
            Exception("Fallo al intentar el login del usuario", e);
        } finally {
            cerrarEntityManager();
        }
        return usu;
    }

}

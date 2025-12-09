package com.example.demo.servicio;
import com.example.demo.entidad.Usuario;
import com.example.demo.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public boolean registrar(Usuario  usuario){
        boolean existe = usuarioRepositorio.existsByGmail(usuario.getGmail());
        if(existe){
           return false;
        }
        usuarioRepositorio.save(usuario);
        return true;
    }
    public List<Usuario> buscar (){
        return usuarioRepositorio.findAll();
    }


}

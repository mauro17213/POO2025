package com.example.demo.controlador;

import com.example.demo.entidad.Usuario;
import com.example.demo.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/login")     // 👈 prefijo para todos los métodos
@CrossOrigin(origins = "http://127.0.0.1:5500") // lo explico abajo
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registrar")
    public boolean registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.registrar(usuario);
    }
    @PostMapping ("/home")
    public List<Usuario> buscaUsuarios (){
        return usuarioServicio.buscar();
    }

}

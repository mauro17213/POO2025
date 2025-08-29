/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.bean.DTO;

import java.io.Serializable;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefAnexo9TriageDTO implements Serializable {
    private Integer id;
    private String descripcion;
    private String url;

    public RefAnexo9TriageDTO() {
    }

    public RefAnexo9TriageDTO(Integer id, String descripcion, String url) {
        this.id = id;
        this.descripcion = descripcion;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

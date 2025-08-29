/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.utilidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author jdlopez
 */
public class GsonUtil {
    private static final Gson GSON;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.serializeNulls();
        GSON = builder.create();
    }
    
    
    public static <T> T deserializar(String json, TypeToken<T> type) {
        try {
            return GSON.fromJson(json, type.getType());
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
    
    public static String serializar(Object object) {
            return GSON.toJson(object);
    }
    
}

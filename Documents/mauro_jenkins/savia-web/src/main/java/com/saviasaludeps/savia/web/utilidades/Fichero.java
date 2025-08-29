/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.utilidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.Set;

/**
 *
 * @author rpalacios
 */
public class Fichero {

    public static void permisos(Path path) throws IOException {
        //Asignación de permisos FULL
        if (isUnix()) {
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
            Files.setPosixFilePermissions(path, permissions);
        }     
    }
    
    public static void propietario(Path path) throws IOException {
        //Asignar propietario y grupo
        if (isUnix()) {
            UserPrincipalLookupService lookupService = path.getFileSystem().getUserPrincipalLookupService();
            UserPrincipal owner = lookupService.lookupPrincipalByName("arquitectura");
            Files.setOwner(path, owner);
        }     
    }
    
    private static boolean isUnix() {
        String osName = System.getProperty("os.name").toLowerCase();
        return (osName.contains("nix") || osName.contains("nux") || osName.contains("mac"));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservices;

import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservices.ContratacionRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.contratacion.IpsDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.contratacion.IpsSedesDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(ContratacionRemoto.class)
public class ContratacionServicio extends GenericoServicio implements ContratacionRemoto {

    @Override
    public IpsDTO consultarIps(String codigoHabilitacionPrestador, String documentoPrestador, String codigoSedePrestador) throws java.lang.Exception {
        IpsDTO ips = null;

        try {
            String strQuery = "FROM CntPrestadores p "
                    + "WHERE 1 = 1 ";

            if ((documentoPrestador == null || documentoPrestador.trim().equals("")) && (codigoHabilitacionPrestador == null || codigoHabilitacionPrestador.trim().equals(""))) {
                throw new Exception("Parámetros incompletos");
            } else {
                if (codigoHabilitacionPrestador != null && !codigoHabilitacionPrestador.trim().equals("")) {
                    strQuery += "AND p.codigoMinSalud = '" + codigoHabilitacionPrestador + "' ";
                }
                if (documentoPrestador != null && !documentoPrestador.trim().equals("")) {
                    strQuery += "AND p.numeroDocumento = '" + documentoPrestador + "' ";
                }
            }

            List<CntPrestadores> list = getEntityManager().createQuery(strQuery).getResultList();
            if (!list.isEmpty()) {

                CntPrestadores per = list.get(0);
                //Carga Ips
                ips = new IpsDTO();
                ips.setCodigoHabilitacion(per.getCodigoMinSalud());
                if (per.getMaeTipoDocumentoId() != 0) {
                    GnMaestros tipoDocumento = (GnMaestros) getEntityManager().find(GnMaestros.class, per.getMaeTipoDocumentoId());
                    ips.setTipoDocumento(tipoDocumento.getValor());
                }

                ips.setNumeroDocumento(per.getNumeroDocumento());
                ips.setDigitoVerificacion(per.getDigitoVerificacion());
                ips.setRazonSocial(per.getRazonSocial());
                ips.setNaturalezaJuridica(per.getNaturalezaJuridica());
                ips.setPrefijo(per.getPrefijo());
                GnMaestros clasePrestador = (GnMaestros) getEntityManager().find(GnMaestros.class, per.getMaeClasePrestadorId());
                if (clasePrestador != null) {
                    ips.setClase(clasePrestador.getDescripcion());
                }

                ips.setCategoria(String.valueOf(per.getCategoriaPrestador()));
                ips.setNivelAtencion(String.valueOf(per.getNivelAtencion()));
                GnMaestros tipoDocumentoRep = (GnMaestros) getEntityManager().find(GnMaestros.class, per.getMaeTipoDocumentoRepId());
                if (tipoDocumentoRep != null) {
                    ips.setTipoDocRepresentanteLegal(tipoDocumentoRep.getValor());
                }

                ips.setDocRepresentanteLegal(per.getNumeroDocumentoRep());
                ips.setNomRepresentanteLegal(per.getNombreRepresentanteLegal());
                if (ips.getCodigoHabilitacion() != null) {
//                    Consulta y Carga de IpsSedes 
                    String strQuerySedes = "FROM CntPrestadorSedes ps "
                            + "WHERE ps.cntPrestadoresId = '" + per.getId() + "' ";

                    if (codigoSedePrestador != null && !codigoSedePrestador.trim().equals("")) {
                        strQuerySedes += "AND ps.codigoPrestador = '" + codigoSedePrestador + "'";
                    } else if (codigoHabilitacionPrestador != null && codigoHabilitacionPrestador != "") {
                        throw new Exception("Parámetros incompletos ");
                    }

                    List<CntPrestadorSedes> listSedes = getEntityManager().createQuery(strQuerySedes).getResultList();
                    List<IpsSedesDTO> listaIpsSedes = new ArrayList();
                    if (!listSedes.isEmpty()) {
                        for (CntPrestadorSedes sede : listSedes) {

                            IpsSedesDTO ipsSedes = new IpsSedesDTO();
                            GnUbicaciones ubicacion = (GnUbicaciones) getEntityManager().find(GnUbicaciones.class, sede.getUbicacionId());
                            if (ubicacion != null) {
                                ipsSedes.setMunicipio(ubicacion.getNombre());
                            }
                            ipsSedes.setCodigoPrestador(sede.getCodigoPrestador());
                            ipsSedes.setDireccion(sede.getDireccion());
                            ipsSedes.setNombre(sede.getNombre());
                            ipsSedes.setCodigo(sede.getCodigo());
                            ipsSedes.setCodigoHabilitacion(sede.getCodigoHabilitacion());
                            ipsSedes.setZonaPrecedencia(sede.getZonaPrecedencia());
                            if (sede.getEstadoSede() != null) {
                                ipsSedes.setEstado(sede.getEstadoSede().toString());
                            }
                            if (sede.getNivelAtencion() != null) {
                                ipsSedes.setNivelAtencion(sede.getNivelAtencion().toString());
                            }
                            if (sede.getMaeClasePrestadorId() != null) {
                                ipsSedes.setClasePrestador(sede.getMaeClasePrestadorValor());
                            }

                            ipsSedes.setFax(sede.getFax());
                            ipsSedes.setTelefonoCitas(sede.getTelefonoCitas());
                            ipsSedes.setCorreoElectronico(sede.getCorreoElectronico());
                            ipsSedes.setTelefonoAdministrativo(sede.getTelefonoAdministrativo());
                            listaIpsSedes.add(ipsSedes);
                        }
                    }
                    ips.setSedes(listaIpsSedes);
                }

//            
            }

        } catch (NoResultException e) {
            ips = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return ips;
    }

}

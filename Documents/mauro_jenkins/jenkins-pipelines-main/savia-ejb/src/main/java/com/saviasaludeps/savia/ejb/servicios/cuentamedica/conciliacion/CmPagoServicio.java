package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPago;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmPagos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmPagoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

@Stateless
@Remote(CmPagoRemoto.class)
public class CmPagoServicio extends GenericoServicio implements CmPagoRemoto {

    @Override
    public CmPago consultar(int id) throws Exception {
        CmPago objRes = null;
        try {
            objRes = castEntidadNegocio((CmPagos) getEntityManager().find(CmPagos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(CmPago obj) throws Exception {
        int id = 0;
        try {
            CmPagos per = castNegocioEntidad(obj);
            id = (int) getEntityManager().merge(per).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "pago ya existe.");
            System.out.println("ERROR:" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public List<CmPago> consultarPorAtributos(ParamConsulta paramConsulta) throws Exception {
        List<CmPago> listaPagos = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null) {
                String strQuery = "FROM CmPagos p JOIN p.cmPagoFacturasList f  WHERE p.id > 0  ";

                if (paramConsulta.getParametroConsulta1() != null) {
                    String nit = (String) paramConsulta.getParametroConsulta1();
                    strQuery += " AND  p.documento = '" + nit + "' ";
                }
                if (paramConsulta.getParametroConsulta2() != null) {
                    String factura = (String) paramConsulta.getParametroConsulta2();
                    strQuery += " AND f.numeroFactura = '" + factura + "' ";
                }
                Query query = getEntityManager().createQuery(strQuery);
                List<Object[]> pagos = query.getResultList();

                for (Object[] neg : pagos) {
                    listaPagos.add(castEntidadNegocio((CmPagos) neg[0]));
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaPagos;
    }

    @Override
    public void actualizar(CmPago obj) throws Exception {
        //
    }

    @Override
    public CmPago eliminar(int id) throws Exception {
        CmPago obj = null;
        try {
            CmPagos ent = getEntityManager().find(CmPagos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    public static CmPago castEntidadNegocio(CmPagos per) {
        CmPago obj = new CmPago();
        obj.setId(per.getId());
        obj.setIdetificador(per.getIdetificador());
        obj.setTipo(per.getTipo());
        obj.setForma(per.getForma());
        if (per.getMaeTipoDocumentoValor() != null) {
            obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        } else {
            obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        }
        obj.setDocumento(per.getDocumento());
        obj.setFechaHora(per.getFechaHora());
        obj.setFacturas(per.getFacturas());
        obj.setValorBruto(per.getValorBruto());
        obj.setValorNeto(per.getValorNeto());
        obj.setValorDeducciones(per.getValorDeducciones());
        obj.setValorCompensacionAnticipos(per.getValorCompensacionAnticipos());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        if (per.getCntPrestadoresId() != null) {
            obj.setCntPrestadoresId(new CntPrestador(per.getCntPrestadoresId().getId()));
        }

        return obj;
    }

    public static CmPagos castNegocioEntidad(CmPago obj) {
        CmPagos per = new CmPagos();
        per.setId(obj.getId());
        per.setIdetificador(obj.getIdetificador());
        per.setTipo((short) obj.getTipo());
        per.setForma((short) obj.getForma());
        if (obj.getMaeTipoDocumentoValor() != null) {
            per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        } else {
            per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        }
        per.setDocumento(obj.getDocumento());
        per.setFechaHora(obj.getFechaHora());
        per.setFacturas(obj.getFacturas());
        per.setValorBruto(obj.getValorBruto());
        per.setValorNeto(obj.getValorNeto());
        per.setValorDeducciones(obj.getValorDeducciones());
        per.setValorCompensacionAnticipos(obj.getValorCompensacionAnticipos());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());

        return per;
    }

}

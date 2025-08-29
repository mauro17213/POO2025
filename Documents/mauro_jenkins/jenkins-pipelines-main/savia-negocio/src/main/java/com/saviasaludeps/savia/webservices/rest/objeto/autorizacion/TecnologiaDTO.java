package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.util.List;

/**
 *
 * @author LFRIVERA
 */
public class TecnologiaDTO {

    private List<ProcedimientoDTO> procedimientos;
    private List<MedicamentoDTO> medicamentos;
    private List<InsumoDTO> insumos;
    private List<PaqueteDTO> paquete;

    public List<ProcedimientoDTO> getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(List<ProcedimientoDTO> procedimientos) {
        this.procedimientos = procedimientos;
    }

    public List<MedicamentoDTO> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<InsumoDTO> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<InsumoDTO> insumos) {
        this.insumos = insumos;
    }

    public List<PaqueteDTO> getPaquete() {
        return paquete;
    }

    public void setPaquete(List<PaqueteDTO> paquete) {
        this.paquete = paquete;
    }
}

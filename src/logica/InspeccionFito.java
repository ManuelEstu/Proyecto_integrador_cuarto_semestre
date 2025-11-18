/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class InspeccionFito {
    private final String idOrden;
    private final String NRO_PLANTAS_EVALUADAS;
    private final String ESTADO_FENOLOGICO;
    private final String CANTIDAD_PLANTAS_INFESTADAS;
    private final String PORCENTAJE_DE_INFESTACION;
    private final String COMENTARIOS;
    
    private InspeccionFito(InspeccionFitoBuilder builder) {
        this.idOrden = builder.idOrden;
        this.NRO_PLANTAS_EVALUADAS = builder.NRO_PLANTAS_EVALUADAS;
        this.ESTADO_FENOLOGICO = builder.ESTADO_FENOLOGICO;
        this.CANTIDAD_PLANTAS_INFESTADAS = builder.CANTIDAD_PLANTAS_INFESTADAS;
        this.PORCENTAJE_DE_INFESTACION = builder.PORCENTAJE_DE_INFESTACION;
        this.COMENTARIOS = builder.COMENTARIOS;
    }
    
    public String getIdOrden() { return idOrden; }
    public String getNRO_PLANTAS_EVALUADAS() { return NRO_PLANTAS_EVALUADAS; }
    public String getESTADO_FENOLOGICO() { return ESTADO_FENOLOGICO; }
    public String getCANTIDAD_PLANTAS_INFESTADAS() { return CANTIDAD_PLANTAS_INFESTADAS; }
    public String getPORCENTAJE_DE_INFESTACION() { return PORCENTAJE_DE_INFESTACION; }
    public String getCOMENTARIOS() { return COMENTARIOS; }
    
    public static class InspeccionFitoBuilder {
        // Los mismos campos, inicializados a un valor por defecto si es necesario
        private String idOrden; // Asumimos que este es obligatorio o el primer campo
        private String NRO_PLANTAS_EVALUADAS = "";
        private String ESTADO_FENOLOGICO = "";
        private String CANTIDAD_PLANTAS_INFESTADAS = "";
        private String PORCENTAJE_DE_INFESTACION = "";
        private String COMENTARIOS = "";

        // Constructor del Builder (para campos obligatorios si los hubiera)
        public InspeccionFitoBuilder(String idOrden) {
            this.idOrden = idOrden;
        }

        // Métodos 'setter' que devuelven el Builder (para encadenamiento)
        public InspeccionFitoBuilder conNRO_PLANTAS_EVALUADAS(String NRO_PLANTAS_EVALUADAS) {
            this.NRO_PLANTAS_EVALUADAS = NRO_PLANTAS_EVALUADAS;
            return this;
        }

        public InspeccionFitoBuilder conESTADO_FENOLOGICO(String ESTADO_FENOLOGICO) {
            this.ESTADO_FENOLOGICO = ESTADO_FENOLOGICO;
            return this;
        }

        public InspeccionFitoBuilder conCANTIDAD_PLANTAS_INFESTADAS(String CANTIDAD_PLANTAS_INFESTADAS) {
            this.CANTIDAD_PLANTAS_INFESTADAS = CANTIDAD_PLANTAS_INFESTADAS;
            return this;
        }
        
        // ... (Añade los métodos para los demás campos) ...
        public InspeccionFitoBuilder conPORCENTAJE_DE_INFESTACION(String PORCENTAJE_DE_INFESTACION) {
            this.PORCENTAJE_DE_INFESTACION = PORCENTAJE_DE_INFESTACION;
            return this;
        }

        public InspeccionFitoBuilder conCOMENTARIOS(String COMENTARIOS) {
            this.COMENTARIOS = COMENTARIOS;
            return this;
        }

        // El método final para construir el objeto
        public InspeccionFito build() {
            return new InspeccionFito(this);
        }
    }
    
}

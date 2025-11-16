/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class InformeVisTec {

    // --- Campos Finales del DTO ---
    private final String ica;
    private final String nombreLugar;
    private final String nombreTecnico;
    private final String apellidoTecnico;
    private final String fechaUltimaInspeccion; 
    private final String areaAcopio;
    private final String areaManResiduosVegetales;
    private final String areaAlmacInsumosAgricolas;
    private final String areaDosifPrepMezclas;
    private final String areaResiduosMezLavado;
    private final String areaSanitariaLavamanos;
    private final String areaHerramientas;
    private final String comentarios;

    // 1. Constructor privado que acepta el Builder
    private InformeVisTec(InformeUltimaInspeccionBuilder builder) {
        this.ica = builder.ica;
        this.nombreLugar = builder.nombreLugar;
        this.nombreTecnico = builder.nombreTecnico;
        this.apellidoTecnico = builder.apellidoTecnico;
        this.fechaUltimaInspeccion = builder.fechaUltimaInspeccion;
        this.areaAcopio = builder.areaAcopio;
        this.areaManResiduosVegetales = builder.areaManResiduosVegetales;
        this.areaAlmacInsumosAgricolas = builder.areaAlmacInsumosAgricolas;
        this.areaDosifPrepMezclas = builder.areaDosifPrepMezclas;
        this.areaResiduosMezLavado = builder.areaResiduosMezLavado;
        this.areaSanitariaLavamanos = builder.areaSanitariaLavamanos;
        this.areaHerramientas = builder.areaHerramientas;
        this.comentarios = builder.comentarios;
    }

    // --- Getters (Necesarios para el acceso a la información) ---
    
    public String getIca() { return ica; }
    public String getNombreLugar() { return nombreLugar; }
    public String getNombreTecnico() { return nombreTecnico; }
    public String getApellidoTecnico() { return apellidoTecnico; }
    public String getFechaUltimaInspeccion() { return fechaUltimaInspeccion; }
    public String getAreaAcopio() { return areaAcopio; }
    public String getAreaManResiduosVegetales() { return areaManResiduosVegetales; }
    public String getAreaAlmacInsumosAgricolas() { return areaAlmacInsumosAgricolas; }
    public String getAreaDosifPrepMezclas() { return areaDosifPrepMezclas; }
    public String getAreaResiduosMezLavado() { return areaResiduosMezLavado; }
    public String getAreaSanitariaLavamanos() { return areaSanitariaLavamanos; }
    public String getAreaHerramientas() { return areaHerramientas; }
    public String getComentarios() { return comentarios; }

    // ==========================================================
    // 2. CLASE BUILDER ANIDADA
    // ==========================================================

    public static class InformeUltimaInspeccionBuilder {
        // Los mismos campos, ya no son 'final'
        private String ica;
        private String nombreLugar;
        private String nombreTecnico;
        private String apellidoTecnico;
        private String fechaUltimaInspeccion;
        private String areaAcopio;
        private String areaManResiduosVegetales;
        private String areaAlmacInsumosAgricolas;
        private String areaDosifPrepMezclas;
        private String areaResiduosMezLavado;
        private String areaSanitariaLavamanos;
        private String areaHerramientas;
        private String comentarios;

        // 3. Constructor del Builder (si hubiese campos obligatorios al iniciar)
        // Dejamos un constructor vacío ya que en este DTO, todos los campos vienen del ResultSet
        public InformeUltimaInspeccionBuilder() {
            // Inicialización opcional de valores por defecto si los campos pueden ser nulos
        }
        
        // 4. Métodos 'setter' que devuelven el propio Builder (cadena de llamadas)
        
        public InformeUltimaInspeccionBuilder conIca(String ica) {
            this.ica = ica;
            return this;
        }
        
        // --- LDP ---
        public InformeUltimaInspeccionBuilder conNombreLugar(String nombreLugar) {
            this.nombreLugar = nombreLugar;
            return this;
        }

        // --- TECNICO ---
        public InformeUltimaInspeccionBuilder conNombreTecnico(String nombreTecnico) {
            this.nombreTecnico = nombreTecnico;
            return this;
        }

        public InformeUltimaInspeccionBuilder conApellidoTecnico(String apellidoTecnico) {
            this.apellidoTecnico = apellidoTecnico;
            return this;
        }
        
        // --- ORDEN ---
        public InformeUltimaInspeccionBuilder conFechaUltimaInspeccion(String fechaUltimaInspeccion) {
            this.fechaUltimaInspeccion = fechaUltimaInspeccion;
            return this;
        }

        // --- INSPECCION_TECNICA ---
        public InformeUltimaInspeccionBuilder conAreaAcopio(String areaAcopio) {
            this.areaAcopio = areaAcopio;
            return this;
        }

        public InformeUltimaInspeccionBuilder conAreaManResiduosVegetales(String areaManResiduosVegetales) {
            this.areaManResiduosVegetales = areaManResiduosVegetales;
            return this;
        }

        public InformeUltimaInspeccionBuilder conAreaAlmacInsumosAgricolas(String areaAlmacInsumosAgricolas) {
            this.areaAlmacInsumosAgricolas = areaAlmacInsumosAgricolas;
            return this;
        }

        public InformeUltimaInspeccionBuilder conAreaDosifPrepMezclas(String areaDosifPrepMezclas) {
            this.areaDosifPrepMezclas = areaDosifPrepMezclas;
            return this;
        }

        public InformeUltimaInspeccionBuilder conAreaResiduosMezLavado(String areaResiduosMezLavado) {
            this.areaResiduosMezLavado = areaResiduosMezLavado;
            return this;
        }

        public InformeUltimaInspeccionBuilder conAreaSanitariaLavamanos(String areaSanitariaLavamanos) {
            this.areaSanitariaLavamanos = areaSanitariaLavamanos;
            return this;
        }

        public InformeUltimaInspeccionBuilder conAreaHerramientas(String areaHerramientas) {
            this.areaHerramientas = areaHerramientas;
            return this;
        }

        public InformeUltimaInspeccionBuilder conComentarios(String comentarios) {
            this.comentarios = comentarios;
            return this;
        }

        // 5. El método final para construir el objeto
        public InformeVisTec build() {
            // Opcional: validar que campos cruciales no sean nulos antes de construir
            return new InformeVisTec(this);
        }
    }
}

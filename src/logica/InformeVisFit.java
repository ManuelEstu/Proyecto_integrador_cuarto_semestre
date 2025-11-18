/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class InformeVisFit {
    private final String ica;
    private final String nombreLugar;
    private final String nombreTecnico;
    private final String apellidoTecnico;
    private final String fechaUltimaInspeccion; 
    private final String plantasEva;
    private final String estado;
    private final String plantasInfes;
    private final String porInfes;
    private final String comentarios;
    
    private InformeVisFit(InformeUltimaInspeccionBuilder builder) {
        this.ica = builder.ica;
        this.nombreLugar = builder.nombreLugar;
        this.nombreTecnico = builder.nombreTecnico;
        this.apellidoTecnico = builder.apellidoTecnico;
        this.fechaUltimaInspeccion = builder.fechaUltimaInspeccion;
        this.plantasEva = builder.plantasEva;
        this.estado = builder.estado;
        this.plantasInfes = builder.plantasInfes;
        this.porInfes = builder.porInfes;
        this.comentarios = builder.comentarios;
    }

    // --- Getters (Necesarios para el acceso a la información) ---
    
    public String getIca() { return ica; }
    public String getNombreLugar() { return nombreLugar; }
    public String getNombreTecnico() { return nombreTecnico; }
    public String getApellidoTecnico() { return apellidoTecnico; }
    public String getFechaUltimaInspeccion() { return fechaUltimaInspeccion; }
    public String getPlantasEva() { return plantasEva; }
    public String getEstado() { return estado; }
    public String getPlantasInfes() { return plantasInfes; }
    public String getPorInfes() { return porInfes; }
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
        private String plantasEva;
        private String estado;
        private String plantasInfes;
        private String porInfes;
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
        public InformeUltimaInspeccionBuilder conPlantasEva(String plantasEva) {
            this.plantasEva = plantasEva;
            return this;
        }

        public InformeUltimaInspeccionBuilder conEstado(String estado) {
            this.estado = estado;
            return this;
        }

        public InformeUltimaInspeccionBuilder conPlantasInfes(String plantasInfes) {
            this.plantasInfes = plantasInfes;
            return this;
        }

        public InformeUltimaInspeccionBuilder conPorInfes(String porInfes) {
            this.porInfes = porInfes;
            return this;
        }

        public InformeUltimaInspeccionBuilder conComentarios(String comentarios) {
            this.comentarios = comentarios;
            return this;
        }

        // 5. El método final para construir el objeto
        public InformeVisFit build() {
            // Opcional: validar que campos cruciales no sean nulos antes de construir
            return new InformeVisFit(this);
        }
    }
    
}

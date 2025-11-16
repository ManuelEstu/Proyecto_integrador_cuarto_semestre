/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class InformeEstadoLug {
    private final String nombreLugar;
    private final String areaTotal;
    private final String predios;
    private final String areaRestante;
    private final String lotes; 
    private final String areaOcupada;
    
    private InformeEstadoLug(InformeProduccionBuilder builder) {
        this.nombreLugar = builder.nombreLugar;
        this.areaTotal = builder.areaTotal;
        this.predios = builder.predios;
        this.areaRestante = builder.areaRestante;
        this.lotes = builder.lotes;
        this.areaOcupada = builder.areaOcupada;
    }
    
    public String getNombreLugar() { return nombreLugar; }
    public String getAreaTotal() { return areaTotal; }
    public String getPredios() { return predios; }
    public String getAreaRestante() { return areaRestante; }
    public String getLotes() { return lotes; }
    public String getAreaOcupada() { return areaOcupada; }
    
    public static class InformeProduccionBuilder {
        // Los mismos campos, ya no son 'final'
        private String nombreLugar;
        private String areaTotal;
        private String predios;
        private String areaRestante;
        private String lotes;
        private String areaOcupada;

        // 3. Constructor del Builder (si hubiese campos obligatorios al iniciar)
        // Dejamos un constructor vacío ya que en este DTO, todos los campos vienen del ResultSet
        public InformeProduccionBuilder() {
            // Inicialización opcional de valores por defecto si los campos pueden ser nulos
        }
        
        // 4. Métodos 'setter' que devuelven el propio Builder (cadena de llamadas)
        
        public InformeProduccionBuilder conNombreLugar(String nombreLugar) {
            this.nombreLugar = nombreLugar;
            return this;
        }
        
        // --- LDP ---
        public InformeProduccionBuilder conAreaTotal(String areaTotal) {
            this.areaTotal = areaTotal;
            return this;
        }

        // --- TECNICO ---
        public InformeProduccionBuilder conPredios(String predios) {
            this.predios = predios;
            return this;
        }

        public InformeProduccionBuilder conAreaRestante(String areaRestante) {
            this.areaRestante = areaRestante;
            return this;
        }
        
        // --- ORDEN ---
        public InformeProduccionBuilder conLotes(String lotes) {
            this.lotes = lotes;
            return this;
        }

        // --- INSPECCION_TECNICA ---
        public InformeProduccionBuilder conAreaOcupada(String areaOcupada) {
            this.areaOcupada = areaOcupada;
            return this;
        }

        // 5. El método final para construir el objeto
        public InformeEstadoLug build() {
            // Opcional: validar que campos cruciales no sean nulos antes de construir
            return new InformeEstadoLug(this);
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class InformePro {
    private final String nombreLugar;
    private final String areaTotal;
    private final String planta;
    private final String recolectado;
    private final String promedio; 
    private final String areaCultivo;
    
    private InformePro(InformeProduccionBuilder builder) {
        this.nombreLugar = builder.nombreLugar;
        this.areaTotal = builder.areaTotal;
        this.planta = builder.planta;
        this.recolectado = builder.recolectado;
        this.promedio = builder.promedio;
        this.areaCultivo = builder.areaCultivo;
    }
    
    public String getNombreLugar() { return nombreLugar; }
    public String getAreaTotal() { return areaTotal; }
    public String getPlanta() { return planta; }
    public String getRecolectado() { return recolectado; }
    public String getPromedio() { return promedio; }
    public String getAreaCultivo() { return areaCultivo; }
    
    public static class InformeProduccionBuilder {
        // Los mismos campos, ya no son 'final'
        private String nombreLugar;
        private String areaTotal;
        private String planta;
        private String recolectado;
        private String promedio;
        private String areaCultivo;

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
        public InformeProduccionBuilder conPlanta(String planta) {
            this.planta = planta;
            return this;
        }

        public InformeProduccionBuilder conRecolectado(String recolectado) {
            this.recolectado = recolectado;
            return this;
        }
        
        // --- ORDEN ---
        public InformeProduccionBuilder conPromedio(String promedio) {
            this.promedio = promedio;
            return this;
        }

        // --- INSPECCION_TECNICA ---
        public InformeProduccionBuilder conAreaCultivo(String areaCultivo) {
            this.areaCultivo = areaCultivo;
            return this;
        }

        // 5. El método final para construir el objeto
        public InformePro build() {
            // Opcional: validar que campos cruciales no sean nulos antes de construir
            return new InformePro(this);
        }
    }
    
}

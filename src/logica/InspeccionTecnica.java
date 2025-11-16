/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class InspeccionTecnica {
    // Campos del DAO (deben coincidir con la base de datos)
    private final String idOrden;
    private final String areaAcopio;
    private final String areaManResiduosVegetales;
    private final String areaAlmacInsumosAgricolas;
    private final String areaDosifPrepMezclas;
    private final String areaResiduosMezLavado;
    private final String areaSanitariaLavamanos;
    private final String areaHerramientas;
    private final String comentarios;

    // Constructor privado que solo acepta el Builder
    private InspeccionTecnica(InspeccionTecnicaBuilder builder) {
        this.idOrden = builder.idOrden;
        this.areaAcopio = builder.areaAcopio;
        this.areaManResiduosVegetales = builder.areaManResiduosVegetales;
        this.areaAlmacInsumosAgricolas = builder.areaAlmacInsumosAgricolas;
        this.areaDosifPrepMezclas = builder.areaDosifPrepMezclas;
        this.areaResiduosMezLavado = builder.areaResiduosMezLavado;
        this.areaSanitariaLavamanos = builder.areaSanitariaLavamanos;
        this.areaHerramientas = builder.areaHerramientas;
        this.comentarios = builder.comentarios;
    }

    // --- Getters para el DAO ---
    public String getIdOrden() { return idOrden; }
    public String getAreaAcopio() { return areaAcopio; }
    public String getAreaManResiduosVegetales() { return areaManResiduosVegetales; }
    public String getAreaAlmacInsumosAgricolas() { return areaAlmacInsumosAgricolas; }
    public String getAreaDosifPrepMezclas() { return areaDosifPrepMezclas; }
    public String getAreaResiduosMezLavado() { return areaResiduosMezLavado; }
    public String getAreaSanitariaLavamanos() { return areaSanitariaLavamanos; }
    public String getAreaHerramientas() { return areaHerramientas; }
    public String getComentarios() { return comentarios; }
    // (Puedes añadir un toString() si quieres)

    // --- CLASE BUILDER ANIDADA ---
    public static class InspeccionTecnicaBuilder {
        // Los mismos campos, inicializados a un valor por defecto si es necesario
        private String idOrden; // Asumimos que este es obligatorio o el primer campo
        private String areaAcopio = "";
        private String areaManResiduosVegetales = "";
        private String areaAlmacInsumosAgricolas = "";
        private String areaDosifPrepMezclas = "";
        private String areaResiduosMezLavado = "";
        private String areaSanitariaLavamanos = "";
        private String areaHerramientas = "";
        private String comentarios = "";

        // Constructor del Builder (para campos obligatorios si los hubiera)
        public InspeccionTecnicaBuilder(String idOrden) {
            this.idOrden = idOrden;
        }

        // Métodos 'setter' que devuelven el Builder (para encadenamiento)
        public InspeccionTecnicaBuilder conAreaAcopio(String areaAcopio) {
            this.areaAcopio = areaAcopio;
            return this;
        }

        public InspeccionTecnicaBuilder conAreaManResiduosVegetales(String areaManResiduosVegetales) {
            this.areaManResiduosVegetales = areaManResiduosVegetales;
            return this;
        }

        public InspeccionTecnicaBuilder conAreaAlmacInsumosAgricolas(String areaAlmacInsumosAgricolas) {
            this.areaAlmacInsumosAgricolas = areaAlmacInsumosAgricolas;
            return this;
        }
        
        // ... (Añade los métodos para los demás campos) ...
        public InspeccionTecnicaBuilder conAreaDosifPrepMezclas(String areaDosifPrepMezclas) {
            this.areaDosifPrepMezclas = areaDosifPrepMezclas;
            return this;
        }

        public InspeccionTecnicaBuilder conAreaResiduosMezLavado(String areaResiduosMezLavado) {
            this.areaResiduosMezLavado = areaResiduosMezLavado;
            return this;
        }

        public InspeccionTecnicaBuilder conAreaSanitariaLavamanos(String areaSanitariaLavamanos) {
            this.areaSanitariaLavamanos = areaSanitariaLavamanos;
            return this;
        }

        public InspeccionTecnicaBuilder conAreaHerramientas(String areaHerramientas) {
            this.areaHerramientas = areaHerramientas;
            return this;
        }

        public InspeccionTecnicaBuilder conComentarios(String comentarios) {
            this.comentarios = comentarios;
            return this;
        }

        // El método final para construir el objeto
        public InspeccionTecnica build() {
            return new InspeccionTecnica(this);
        }
    }
}

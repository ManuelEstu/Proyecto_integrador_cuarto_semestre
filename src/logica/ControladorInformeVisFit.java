/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import java.util.Optional;
import logica.InformeVisFit;
import persistencia.DaoInformeVisFit;

public class ControladorInformeVisFit {
    private final DaoInformeVisFit DaoInformeVisFit;

    /**
     * Constructor que inicializa el controlador, inyectando o creando el DAO.
     * En un entorno real, 'daoInformeVisTec' se inyectaría como dependencia.
     */
    public ControladorInformeVisFit() {
        this.DaoInformeVisFit = new DaoInformeVisFit();
    }

    /**
     * Valida el número de registro ICA y, si es válido, busca el último informe
     * técnico asociado a ese número en la base de datos.
     *
     * Reglas de validación:
     * 1. No nulo o vacío.
     * 2. Debe contener exactamente 6 dígitos.
     * 3. Debe ser numérico.
     *
     * @param icaNumber El número de registro ICA proporcionado.
     * @return Un Optional<InformeVisTec> con el informe encontrado, o Optional.empty()
     * si el ICA es inválido o si no se encuentra el informe en el DAO.
     */
    public Optional<InformeVisFit> buscarUltimoInforme(String icaNumber) {
        
        // --- 1. VALIDACIÓN DE ENTRADA ---
        
        // 1.1. No nulo o vacío
        if (icaNumber == null || icaNumber.trim().isEmpty()) {
            System.err.println("❌ ERROR (Controlador): El número ICA no puede ser nulo o vacío.");
            return Optional.empty();
        }

        String icaLimpio = icaNumber.trim();
        
        // 1.2. Validar longitud (6 dígitos)
        if (icaLimpio.length() != 6) {
            System.err.println("❌ ERROR (Controlador): El número ICA debe contener exactamente 6 dígitos. Longitud actual: " + icaLimpio.length());
            return Optional.empty();
        }

        // 1.3. Validar que solo contiene números
        if (!icaLimpio.matches("\\d+")) {
            System.err.println("❌ ERROR (Controlador): El número ICA debe contener solo caracteres numéricos.");
            return Optional.empty();
        }

        // --- 2. LLAMADA A LA CAPA DE DATOS ---
        
        System.out.println("✅ VALIDACIÓN EXITOSA. Buscando informe para ICA: " + icaLimpio);
        
        try {
            // Delega la operación de búsqueda a la capa DAO
            return DaoInformeVisFit.obtenerUltimoInformePorIca(icaLimpio);
        } catch (Exception e) {
            // Registra cualquier excepción que pueda propagarse desde el DAO
            System.err.println("💥 ERROR INESPERADO en la capa de negocio al buscar el informe: " + e.getMessage());
            // En un entorno de producción, se lanzaría una excepción personalizada de negocio
            return Optional.empty();
        }
    }
}

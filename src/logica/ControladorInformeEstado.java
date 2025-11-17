/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import logica.InformeEstadoLug;
import persistencia.DaoInformeEstadoLug; // Asegúrate de que esta importación sea correcta
import java.sql.SQLException;
import java.util.Optional;

/**
 * Clase controladora para gestionar la lógica de búsqueda del informe de estado
 * de un Lugar de Producción.
 */
public class ControladorInformeEstado {

    // Cambié el tipo de informeDAO para reflejar el nombre del archivo DAO que manejamos,
    // pero mantuve el nombre de la clase que proporcionaste.
    private final DaoInformeEstadoLug informeDAO;

    public ControladorInformeEstado() {
        // Asumiendo que DaoInformeEstadoLug es InformeEstadoLugDAO
        this.informeDAO = new DaoInformeEstadoLug(); 
    }

    /**
     * Valida el formato del Nro ICA y recupera el informe de la base de datos.
     * @param icaNumber El número ICA ingresado por el usuario (esperado: 6 dígitos).
     * @return Optional<InformeEstadoLug> si se encuentra y es válido, o Optional.empty() si no.
     * @throws IllegalArgumentException Si el formato del Nro ICA es incorrecto.
     * @throws RuntimeException Si ocurre un error fatal de base de datos (se usa para simplificar el manejo de SQLException en la vista).
     */
    public Optional<InformeEstadoLug> generarInforme(String icaNumber) throws IllegalArgumentException, RuntimeException {
        // --- 1. Validación de Formato (Lógica de Negocio) ---
        if (icaNumber == null || icaNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número ICA no puede estar vacío.");
        }

        // Validación de 6 dígitos: El método matches usa una expresión regular
        if (!icaNumber.trim().matches("\\d{6}")) {
            throw new IllegalArgumentException("El número ICA debe contener exactamente 6 dígitos.");
        }

        // --- 2. Llamada al DAO (Lógica de Acceso a Datos) ---
        try {
            return informeDAO.obtenerInformePorIca(icaNumber.trim());
        } catch (Exception e) {
            // Ahora este catch es obligatorio y compilará correctamente.
            System.err.println("Error fatal de BD al buscar informe ICA: " + icaNumber);
            e.printStackTrace();
            // Lanzamos una excepción de tiempo de ejecución para que la vista la pueda capturar
            throw new RuntimeException("Ocurrió un error al intentar acceder a la base de datos.", e);
        }
    }
}

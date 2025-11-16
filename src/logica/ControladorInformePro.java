/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import java.util.List;
import logica.InformePro; // Asume que la entidad está en logica
import persistencia.DaoInformePro; // Asume que el DAO está en dao

public class ControladorInformePro {
    
    private DaoInformePro daoInformePro;

    public ControladorInformePro() {
        this.daoInformePro = new DaoInformePro();
    }

    /**
     * Valida el formato del Nro ICA y obtiene el informe de producción.
     * @param registroIca El número ICA ingresado por el usuario.
     * @return Una lista de objetos InformePro si el ICA es válido y se encuentra el informe.
     * @throws IllegalArgumentException Si el formato del Nro ICA no es de 6 dígitos.
     */
    public List<InformePro> generarInformeProduccion(String registroIca) throws IllegalArgumentException {
        
        // 1. Validación del formato del Nro ICA
        if (registroIca == null || !registroIca.matches("\\d{6}")) {
            throw new IllegalArgumentException("El Nro ICA debe contener exactamente 6 dígitos numéricos.");
        }

        // 2. Llamada al DAO para obtener el informe
        System.out.println("Nro ICA válido (" + registroIca + "). Generando informe...");
        
        // El DAO devolverá una lista (vacía si no hay resultados o si hay error,
        // pero la lista vacía es un resultado válido si el ICA existe pero no tiene lotes).
        return daoInformePro.obtenerInforme(registroIca);
    }
}

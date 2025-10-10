/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package igu;

/**
 *
 * @author CASA
 */
import javax.swing.JOptionPane;
import logica.ControladorLugarProduccion; 
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;// Necesario para el modelo
import logica.FiltroDepartamento;
public class Registro_lugar_produccion extends javax.swing.JFrame {

    // Lista de los 32 departamentos de Colombia en orden alfabético
    private static final String[] DEPARTAMENTOS_COLOMBIA = {
        "Amazonas", "Antioquia", "Arauca", "Atlántico", "Bolívar", "Boyacá",
        "Caldas", "Caquetá", "Casanare", "Cauca", "Cesar", "Chocó",
        "Córdoba", "Cundinamarca", "Guainía", "Guaviare", "Huila", "La Guajira",
        "Magdalena", "Meta", "Nariño", "Norte de Santander", "Putumayo", 
        "Quindío", "Risaralda", "San Andrés y Providencia", "Santander", 
        "Sucre", "Tolima", "Valle del Cauca", "Vaupés", "Vichada"
    };
    private static final Map<String, String[]> MUNICIPIOS_POR_DEPARTAMENTO = new HashMap<>();
    static {
        
        // =======================================================
        // BLOQUE DE INICIALIZACIÓN: MUNICIPIOS POR DEPARTAMENTO
        // =======================================================

        // 1. AMAZONAS (11 municipios)
        String[] amazonas = {"Leticia", "El Encanto", "La Chorrera", "La Pedrera", "La Victoria", "Mirití - Paraná", "Puerto Alegría", "Puerto Arica", "Puerto Nariño", "Puerto Santander", "Tarapacá"};
        Arrays.sort(amazonas);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Amazonas", amazonas);
        
        // 2. ANTIOQUIA (125 municipios)
        String[] antioquia = {"Medellín", "Abejorral", "Abriaquí", "Alejandría", "Amagá", "Amalfi", "Andes", "Angelópolis", "Angostura", "Anorí", "Santa Fe de Antioquia", "Anzá", "Apartadó", "Arboletes", "Argelia", "Armenia", "Barbosa", "Bello", "Betania", "Betulia", "Ciudad Bolívar", "Briceño", "Buriticá", "Cáceres", "Caicedo", "Caldas", "Campamento", "Cañasgordas", "Caracolí", "Caramanta", "Carepa", "Carolina del Príncipe", "Caucasia", "Chigorodó", "Cisneros", "Cocorná", "Concepción", "Concordia", "Copacabana", "Dabeiba", "Donmatías", "Ebéjico", "El Bagre", "Entrerríos", "Envigado", "Fredonia", "Frontino", "Giraldo", "Girardota", "Gómez Plata", "Granada", "Guadalupe", "Guarne", "Guatapé", "Heliconia", "Hispania", "Itagüí", "Ituango", "Jardín", "Jericó", "La Ceja", "La Estrella", "La Pintada", "La Unión", "Liborina", "Maceo", "Marinilla", "Montebello", "Murindó", "Mutatá", "Nariño", "Nechí", "Necoclí", "Olaya", "Peñol", "Peque", "Pueblorrico", "Puerto Berrío", "Puerto Nare", "Puerto Triunfo", "Remedios", "Retiro", "Rionegro", "Sabanalarga", "Sabaneta", "Salgar", "San Andrés de Cuerquia", "San Carlos", "San Francisco", "San Jerónimo", "San José de la Montaña", "San Juan de Urabá", "San Luis", "San Pedro de los Milagros", "San Pedro de Urabá", "San Rafael", "San Roque", "San Vicente", "Santa Bárbara", "Santa Rosa de Osos", "Santo Domingo", "Segovia", "Sonsón", "Sopetrán", "Támesis", "Tarazá", "Tarso", "Titiribí", "Toledo", "Turbo", "Uramita", "Urrao", "Valdivia", "Valparaíso", "Vegachí", "Venecia", "Vigía del Fuerte", "Yalí", "Yarumal", "Yolombó", "Yondó", "Zaragoza"};
        Arrays.sort(antioquia);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Antioquia", antioquia);
        
        // 3. ARAUCA (7 municipios)
        String[] arauca = {"Arauca", "Arauquita", "Cravo Norte", "Fortul", "Puerto Rondón", "Saravena", "Tame"};
        Arrays.sort(arauca);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Arauca", arauca);
        
        // 4. ATLÁNTICO (23 municipios)
        String[] atlantico = {"Barranquilla", "Baranoa", "Campo de la Cruz", "Candelaria", "Galapa", "Juan de Acosta", "Luruaco", "Malambo", "Manatí", "Palmar de Varela", "Piojó", "Polo Nuevo", "Ponedera", "Puerto Colombia", "Repelón", "Sabanagrande", "Sabanalarga", "Santa Lucía", "Santo Tomás", "Soledad", "Suan", "Tubará", "Usiacurí"};
        Arrays.sort(atlantico);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Atlántico", atlantico);
        
        // 5. BOLÍVAR (46 municipios)
        String[] bolivar = {"Cartagena de Indias", "Achí", "Altos del Rosario", "Arenal", "Arjona", "Arroyohondo", "Barranco de Loba", "Calamar", "Cantagallo", "Cicuco", "Córdoba", "Clemencia", "El Carmen de Bolívar", "El Guamo", "El Peñón", "Hatillo de Loba", "Magangué", "Mahates", "Margarita", "María La Baja", "Mompós", "Montecristo", "Morales", "Norosí", "Pinillos", "Regidor", "Río Viejo", "San Cristóbal", "San Estanislao", "San Fernando", "San Jacinto", "San Jacinto del Cauca", "San Juan Nepomuceno", "San Martín de Loba", "San Pablo", "Santa Catalina", "Santa Rosa", "Santa Rosa del Sur", "Simití", "Soplaviento", "Talaigua Nuevo", "Tiquisio", "Turbaco", "Turbaná", "Villanueva", "Zambrano"};
        Arrays.sort(bolivar);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Bolívar", bolivar);

        // 6. BOYACÁ (123 municipios)
        String[] boyaca = {"Tunja", "Almeida", "Aquitania", "Arcabuco", "Belén", "Berbeo", "Betéitiva", "Boavita", "Boyacá", "Briceño", "Buenavista", "Busbanzá", "Caldas", "Campohermoso", "Cerinza", "Chinavita", "Chiquinquirá", "Chiscas", "Chita", "Chitaraque", "Chivatá", "Chíquiza", "Ciénaga", "Combita", "Coper", "Corrales", "Covarachía", "Cubará", "Cucaita", "Cuítiva", "Duitama", "El Cocuy", "El Espino", "Firavitoba", "Floresta", "Gachantivá", "Gámeza", "Garagoa", "Guacamayas", "Guateque", "Guayatá", "Güicán de la Sierra", "Iza", "Jenesano", "Jericó", "La Capilla", "La Uvita", "Villa de Leyva", "Labranzagrande", "Macanal", "Maripí", "Miraflores", "Muzo", "Nobsa", "Nuevo Colón", "Oicatá", "Otanche", "Pachavita", "Páez", "Paipa", "Pajarito", "Panqueba", "Pauna", "Paya", "Paz de Río", "Pesca", "Pisba", "Puerto Boyacá", "Quípama", "Ramiriquí", "Ráquira", "Rondón", "Saboyá", "Sáchica", "Samacá", "San Eduardo", "San José de Pare", "San Luis de Gaceno", "San Mateo", "San Miguel de Sema", "San Pablo de Borbur", "Santa María", "Santa Sofía", "Santana", "Sativanorte", "Sativasur", "Siachoque", "Soatá", "Socha", "Socotá", "Sogamoso", "Somondoco", "Sora", "Sotaquirá", "Susacón", "Sutamarchán", "Sutatenza", "Tasco", "Tenza", "Tibaná", "Tinjacá", "Tipacoque", "Toca", "Tópaga", "Tota", "Turmequé", "Tutazá", "Úmbita", "Ventaquemada", "Viracachá", "Zetaquira", "Cómbita", "Mongua", "Monguí", "Motavita", "Páramo", "Sutamarchan", "Tibasosa", "Tuta", "Aquitania", "Puebloviejo", "Togüí", "Tunja"};
        Arrays.sort(boyaca);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Boyacá", boyaca);

        // 7. CALDAS (27 municipios)
        String[] caldas = {"Manizales", "Aguadas", "Anserma", "Aranzazu", "Belalcázar", "Chinchiná", "Filadelfia", "La Dorada", "La Merced", "Manzanares", "Marmato", "Marquetalia", "Marulanda", "Neira", "Norcasia", "Pácora", "Palestina", "Pensilvania", "Riosucio", "Risaralda", "Salamina", "Samaná", "San José", "Supía", "Victoria", "Villamaría", "Viterbo"};
        Arrays.sort(caldas);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Caldas", caldas);

        // 8. CAQUETÁ (16 municipios)
        String[] caqueta = {"Florencia", "Albania", "Belén de los Andaquíes", "Cartagena del Chairá", "Curillo", "El Doncello", "El Paujil", "La Montañita", "Puerto Rico", "San José del Fragua", "San Vicente del Caguán", "Solano", "Solita", "Valparaíso", "Morelia", "Puerto Milán"};
        Arrays.sort(caqueta);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Caquetá", caqueta);

        // 9. CASANARE (19 municipios)
        String[] casanare = {"Yopal", "Aguazul", "Chámeza", "Hato Corozal", "La Salina", "Maní", "Monterrey", "Nunchía", "Orocué", "Paz de Ariporo", "Pore", "Recetor", "Sabanalarga", "Sácama", "San Luis de Palenque", "Tauramena", "Trinidad", "Villanueva", "Támara"};
        Arrays.sort(casanare);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Casanare", casanare);

        // 10. CAUCA (42 municipios)
        String[] cauca = {"Popayán", "Almaguer", "Argelia", "Balboa", "Bolívar", "Buenos Aires", "Cajibío", "Caldono", "Caloto", "Corinto", "El Tambo", "Florencia", "Guachené", "Guapí", "Inzá", "Jambaló", "La Sierra", "La Vega", "López de Micay", "Mercaderes", "Miranda", "Padilla", "Páez", "Patía", " "};
        Arrays.sort(cauca);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Cauca", cauca);
        
        // 11. CESAR (25 municipios)
        String[] cesar = {"Valledupar", "Aguachica", "Astrea", "Becerril", "Bosconia", "Chimichagua", "Chiriguaná", "Curumaní", "El Copey", "El Paso", "Gamarra", "González", "La Gloria", "La Jagua de Ibirico", "Manaure Balcón del Cesar", "Pailitas", "Pelaya", "Pueblo Bello", "Río de Oro", "San Alberto", "San Diego", "San Martín", "Tamalameque", "Agustín Codazzi", "La Paz"};
        Arrays.sort(cesar);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Cesar", cesar);
        
        // 12. CHOCÓ (30 municipios)
        String[] choco = {"Quibdó", "Acandí", "Alto Baudó", "Atrato", "Bagadó", "Bahía Solano", "Bajo Baudó", "Bojayá", "Carmen de Atrato", "Cértegui", "Condoto", "El Cantón de San Pablo", "El Carmen del Darién", "Istmina", "Juradó", "Lloró", "Medio Atrato", "Medio Baudó", "Medio San Juan", "Nóvita", "Nuquí", "Río Iró", "Río Quito", "Riosucio", "Sipí", "Tadó", "Unguía", "Unión Panamericana", "San José del Palmar", "El Litoral del San Juan"};
        Arrays.sort(choco);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Chocó", choco);

        // 13. CÓRDOBA (30 municipios)
        String[] cordoba = {"Montería", "Ayapel", "Buenavista", "Canalete", "Cereté", "Chima", "Chinú", "Ciénaga de Oro", "Cotorra", "La Apartada", "Lorica", "Los Córdobas", "Momil", "Moñitos", "Montelíbano", "Planeta Rica", "Pueblo Nuevo", "Puerto Escondido", "Puerto Libertador", "Purísima de la Candelaria", "Sahagún", "San Andrés de Sotavento", "San Antero", "San Bernardo del Viento", "San Carlos", "San Pelayo", "Tierralta", "Tuchín", "Valencia", "San José de Uré"};
        Arrays.sort(cordoba);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Córdoba", cordoba);

        // 14. CUNDINAMARCA (116 municipios + Bogotá D.C.)
        String[] cundinamarca = {"Bogotá D.C.", "Agua de Dios", "Albán", "Anapoima", "Anolaima", "Apulo", "Arbeláez", "Beltrán", "Bituima", "Bojacá", "Cabrera", "Cachipay", "Cajicá", "Caparrapí", "Cáqueza", "Carmen de Carupa", "Chaguaní", "Chía", "Chipaque", "Choachí", "Chocontá", "Cogua", "Cota", "Cucunubá", "El Colegio", "El Peñón", "El Rosal", "Fúquene", "Facatativá", "Fosca", "Funza", "Fusa gasugá", "Gachalá", "Gachancipá", "Gachetá", "Gama", "Girardot", "Granada", "Guachetá", "Guaduas", "Guasca", "Guataquí", "Guatavita", "Guayabal de Síquima", "Guayabetal", "Lenguazaque", "Machetá", "Madrid", "Manta", "Medina", "Mosquera", "Nariño", "Nemocón", "Nilo", "Nimaima", "Nocaima", "Pacho", "Paime", "Pandi", "Paratebueno", "Pasca", "Puerto Salgar", "Pulí", "Quebradanegra", "Quetame", "Quipile", "Ricaurte", "San Antonio del Tequendama", "San Bernardo", "San Cayetano", "San Francisco", "San Juan de Río Seco", "Sasaima", "Sesquilé", "Sibaté", "Silvania", "Simijaca", "Soacha", "Sopó", "Subachoque", "Suesca", "Supatá", "Susa", "Sutatausa", "Tabio", "Tausa", "Tena", "Tenjo", "Tibacuy", "Tibirita", "Tocaima", "Tocancipá", "Topaipí", "Ubalá", "Ubaque", "Une", "Útica", "Vianí", "Villapinzón", "Villeta", "Viotá", "Yacopí", "Zipacón", "Zipaquirá", "Vergara", "Villagómez", "Gutiérrez", "La Calera", "La Mesa", "La Palma", "La Peña", "La Vega", "Sutatausa"};
        Arrays.sort(cundinamarca);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Cundinamarca", cundinamarca);

        // 15. GUAINÍA (1 municipio - Entidad Territorial)
        String[] guainia = {"Inírida", "Barrancominas", "Mapiripana", "San Felipe", "Puerto Colombia", "La Guadalupe", "Cacahual", "Pana Pana", "Morichal"};
        Arrays.sort(guainia);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Guainía", guainia);

        // 16. GUAVIARE (4 municipios)
        String[] guaviare = {"San José del Guaviare", "Calamar", "El Retorno", "Miraflores"};
        Arrays.sort(guaviare);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Guaviare", guaviare);

        // 17. HUILA (37 municipios)
        String[] huila = {"Neiva", "Acevedo", "Agrado", "Aipe", "Algeciras", "Altamira", "Baraya", "Campoalegre", "Colombia", "Elías", "Garzón", "Gigante", "Guadalupe", "Hobo", "Íquira", "Isnos", "La Argentina", "La Plata", "Nátaga", "Oporapa", "Paicol", "Palermo", "Palestina", "Pital", "Pitalito", "Rivera", "Saladoblanco", "San Agustín", "Santa María", "Suaza", "Suhza", "Tarqui", "Tello", "Teruel", "Tesalia", "Timaná", "Villavieja", "Yaguará"};
        Arrays.sort(huila);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Huila", huila);

        // 18. LA GUAJIRA (15 municipios)
        String[] laGuajira = {"Riohacha", "Albania", "Barrancas", "Dibulla", "Distracción", "El Molino", "Fonseca", "Hatonuevo", "Maicao", "Manaure", "San Juan del Cesar", "Uribia", "Urumita", "Villanueva", "La Jagua del Pilar"};
        Arrays.sort(laGuajira);
        MUNICIPIOS_POR_DEPARTAMENTO.put("La Guajira", laGuajira);

        // 19. MAGDALENA (30 municipios)
        String[] magdalena = {"Santa Marta", "Algarrobo", "Aracataca", "Ariguaní", "Cerro de San Antonio", "Chibolo", "Ciénaga", "Curumaní", "El Banco", "El Piñón", "El Retén", "Fundación", "Guamal", "Nueva Granada", "Pedraza", "Pijiño del Carmen", "Pivijay", "Plato", "Puebloviejo", "Remolino", "Sabanas de San Ángel", "Salamina", "San Sebastián de Buenavista", "San Zenón", "Santa Ana", "Sitionuevo", "Tenerife", "Zapayán", "Zona Bananera", "Concordia"};
        Arrays.sort(magdalena);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Magdalena", magdalena);

        // 20. META (29 municipios)
        String[] meta = {"Villavicencio", "Acacías", "Barranca de Upía", "Cabuyaro", "Castilla la Nueva", "Cubarral", "Cumaral", "El Calvario", "El Castillo", "El Dorado", "Fuente de Oro", "Granada", "Guamal", "La Macarena", "Lejanías", "Mapiripán", "Mesetas", "Puerto Concordia", "Puerto Gaitán", "Puerto Lleras", "Puerto López", "Puerto Rico", "Restrepo", "San Carlos de Guaroa", "San Juan de Arama", "San Juanito", "San Martín", "Uribe", "Vista Hermosa"};
        Arrays.sort(meta);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Meta", meta);

        // 21. NARIÑO (64 municipios)
        String[] narino = {"Pasto", "Albán", "Aldana", "Ancuyá", "Arboleda", "Barbacoas", "Belén", "Buesaco", "Chachagüí", "Colón", "Consacá", "Contadero", "Córdoba", "Cuaspud", "Cumbal", "Piedrancha", "El Charco", "El Peñol", "El Rosario", "El Tablón de Gómez", "El Tambo", "Funes", "Guachucal", "Guaitarilla", "Gualmatán", "Iles", "Imués", "Ipiales", "La Cruz", "La Florida", "La Llanada", "La Tola", "La Unión", "Leiva", "Linares", "Los Andes", "Magüí Payán", "Mallama", "Mosquera", "Nariño", "Olaya Herrera", "Ospina", "Francisco Pizarro", "Policarpa", "Potosí", "Providencia", "Puerres", "Pupiales", "Ricaurte", "Roberto Payán", "Samaniego", "Sandoná", "San Bernardo", "San Lorenzo", "San Pablo", "San Pedro de Cartago", "Santa Bárbara", "Santacruz", "Sapuyes", "Taminango", "Tangua", "Tumaco", "Túquerres", "Yacuanquer"};
        Arrays.sort(narino);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Nariño", narino);

        // 22. NORTE DE SANTANDER (40 municipios)
        String[] nteSantander = {"Cúcuta", "Ábrego", "Arboledas", "Bochalema", "Bucarasica", "Cácota", "Cáchira", "Chinácota", "Chitagá", "Convención", "Cucutilla", "El Carmen", "El Tarra", "El Zulia", "Gramalote", "Hacarí", "Herrán", "Labateca", "La Esperanza", "La Playa de Belén", "Los Patios", "Lourdes", "Mutiscua", "Ocaña", "Pamplona", "Pamplonita", "Puerto Santander", "Ragonvalia", "Salazar de Las Palmas", "San Calixto", "San Cayetano", "Santiago", "Sardinata", "Silos", "Teorama", "Tibú", "Toledo", "Villa Caro", "Villa del Rosario"};
        Arrays.sort(nteSantander);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Norte de Santander", nteSantander);
        
        // 23. PUTUMAYO (13 municipios)
        String[] putumayo = {"Mocoa", "Colón", "Orito", "Puerto Asís", "Puerto Guzmán", "Puerto Leguízamo", "Sibundoy", "San Francisco", "San Miguel", "Santiago", "Valle del Guamuez", "Villagarzón", "Puerto Caicedo"};
        Arrays.sort(putumayo);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Putumayo", putumayo);
        
        // 24. QUINDÍO (12 municipios)
        String[] quindio = {"Armenia", "Buenavista", "Calarcá", "Circasia", "Córdoba", "Filandia", "Génova", "La Tebaida", "Montenegro", "Pijao", "Quimbaya", "Salento"};
        Arrays.sort(quindio);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Quindío", quindio);
        
        // 25. RISARALDA (14 municipios)
        String[] risaralda = {"Pereira", "Apía", "Balboa", "Belén de Umbría", "Dosquebradas", "Guática", "La Celia", "La Virginia", "Marsella", "Mistrató", "Pueblo Rico", "Quinchía", "Santuario", "Santa Rosa de Cabal"};
        Arrays.sort(risaralda);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Risaralda", risaralda);
        
        // 26. SAN ANDRÉS Y PROVIDENCIA (2 municipios)
        String[] sanAndres = {"San Andrés", "Providencia"};
        Arrays.sort(sanAndres);
        MUNICIPIOS_POR_DEPARTAMENTO.put("San Andrés y Providencia", sanAndres);
        
        // 27. SANTANDER (87 municipios)
        String[] santander = {"Bucaramanga", "Aguada", "Albania", "Aratoca", "Barbosa", "Barichara", "Barrancabermeja", "Betulia", "Bolívar", "Cabrera", "California", "Capitanejo", "Carcasí", "Cepitá", "Cerrito", "Charalá", "Charta", "Chima", "Chipatá", "Cimitarra", "Concepción", "Confines", "Contratación", "Coromoro", "Curití", "El Carmen de Chucurí", "El Guacamayo", "El Playón", "Encino", "Enciso", "Floridablanca", "Galán", "Gámbita", "Girón", "Guaca", "Guadalupe", "Guapotá", "Guavata", "Guepsa", "Hato", "Jesús María", "Jordán", "La Belleza", "La Paz", "Landázuri", "Lebrija", "Los Santos", "Macaravita", "Málaga", "Matanza", "Mogotes", "Molagavita", "Oiba", "Onzaga", "Palmar", "Páramo", "Piedecuesta", "Pinchote", "Puente Nacional", "Puerto Parra", "Puerto Wilches", "Rionegro", "Sabana de Torres", "San Andrés", "San Benito", "San Gil", "San Joaquín", "San José de Miranda", "San Miguel", "San Vicente de Chucurí", "Santa Bárbara", "Santa Helena del Opón", "Simacota", "Socorro", "Suaita", "Sucre", "Suratá", "Tona", "Valle de San José", "Vélez", "Vetas", "Villanueva", "Zapatoca", "San Pedro de la Paz"};
        Arrays.sort(santander);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Santander", santander);

        // 28. SUCRE (26 municipios)
        String[] sucre = {"Sincelejo", "Buenavista", "Caimito", "Colosó", "Corozal", "Coveñas", "El Roble", "Galeras", "Guaranda", "La Unión", "Los Palmitos", "Majagual", "Morroa", "Ovejas", "Palmito", "Sampués", "San Benito Abad", "San Juan de Betulia", "San Marcos", "San Onofre", "San Pedro", "Sincé", "Sucre", "Tolú", "Tolú Viejo", "San Luis de Sincé"};
        Arrays.sort(sucre);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Sucre", sucre);
        
        // 29. TOLIMA (47 municipios)
        String[] tolima = {"Ibagué", "Alpujarra", "Alvarado", "Ambalema", "Anzoátegui", "Armero", "Ataco", "Cajamarca", "Carmen de Apicalá", "Casabianca", "Chaparral", "Coello", "Coyaima", "Cunday", "Dolores", "Espinal", "Falan", "Flandes", "Fresno", "Guamo", "Herveo", "Honda", "Icononzo", "Lérida", "Líbano", "Mariquita", "Melgar", "Murillo", "Natagaima", "Ortega", "Palocabildo", "Piedras", "Planadas", "Prado", "Purificación", "Rioblanco", "Roncesvalles", "Rovira", "Saldaña", "San Antonio", "San Luis", "Santa Isabel", "Suárez", "Valle de San Juan", "Venadillo", "Villahermosa", "Villarrica"};
        Arrays.sort(tolima);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Tolima", tolima);

        // 30. VALLE DEL CAUCA (42 municipios)
        String[] valleDelCauca = {"Cali", "Alcalá", "Andalucía", "Ansermanuevo", "Argelia", "Bolívar", "Buenaventura", "Buga", "Bugalagrande", "Caicedonia", "Calima", "Candelaria", "Cartago", "Dagua", "El Águila", "El Cairo", "El Cerrito", "El Dovio", "Florida", "Ginebra", "Guacarí", "Jamundí", "La Cumbre", "La Unión", "La Victoria", "Obando", "Palmira", "Pradera", "Restrepo", "Riofrío", "Roldanillo", "San Pedro", "Sevilla", "Toro", "Trujillo", "Tuluá", "Ulloa", "Versalles", "Vijes", "Yotoco", "Yumbo", "Zarzal"};
        Arrays.sort(valleDelCauca);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Valle del Cauca", valleDelCauca);

        // 31. VAUPÉS (3 municipios - Entidad Territorial)
        String[] vaupes = {"Mitú", "Carurú", "Taraira", "Papunaua", "Yavaraté", "Pacoa"};
        Arrays.sort(vaupes);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Vaupés", vaupes);
        
        // 32. VICHADA (4 municipios - Entidad Territorial)
        String[] vichada = {"Puerto Carreño", "Cumaribo", "La Primavera", "Santa Rosalía"};
        Arrays.sort(vichada);
        MUNICIPIOS_POR_DEPARTAMENTO.put("Vichada", vichada);
    }
    /**
     * Creates new form Registro_lugar_produccion
     */
    
     public Registro_lugar_produccion() { // <--- AÑADE ESTO
        initComponents();
        // Opcional: Deshabilita el campo para que no se vea vacío en pruebas.
        // Txt_documento_dueno.setText(""); 
        // Txt_documento_dueno.setEnabled(false);
    }
    
    public Registro_lugar_produccion(String documentoDueno) {
        initComponents();
        inicializarDepartamentos();
        // Inicialmente, cargar el JComboBox de municipios vacío o con una opción
        Municipio.setModel(new DefaultComboBoxModel<>(new String[]{"-- Seleccione Departamento Primero --"}));
        // 1. Establecer el valor por defecto
        Txt_documento_dueno.setText(documentoDueno); 
        
        // 2. Deshabilitar la edición del campo
        // Esto evita que el productor cambie su propio documento al registrar un lugar
        //Txt_documento_dueno.setEnabled(false); 
        // Opcionalmente, setEditable(false) solo impide escribir, pero no desactiva el campo
        Txt_documento_dueno.setEditable(false);
    }
    
    /**
     * Configura el JComboBox con los departamentos y la funcionalidad de filtro.
     */
    private void inicializarDepartamentos() {
        DefaultComboBoxModel<String> modeloDepartamentos = 
            new DefaultComboBoxModel<>(DEPARTAMENTOS_COLOMBIA);
        
        Departamento.setModel(modeloDepartamentos);
        FiltroDepartamento.habilitarFiltro(Departamento);
        
        // 1. Añadir el Listener al JComboBox de Departamento
        Departamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarMunicipios();
            }
        });
        
        // 2. Aplicar el filtro también al JComboBox de Municipio (aunque se cargará después)
        FiltroDepartamento.habilitarFiltro(Municipio);
    }

    private void cargarMunicipios() {
        // Obtener el departamento seleccionado
        String departamentoSeleccionado = (String) Departamento.getSelectedItem();
        
        if (departamentoSeleccionado != null && MUNICIPIOS_POR_DEPARTAMENTO.containsKey(departamentoSeleccionado)) {
            // Obtener el array de municipios para ese departamento
            String[] municipios = MUNICIPIOS_POR_DEPARTAMENTO.get(departamentoSeleccionado);
            
            // Crear y establecer el nuevo modelo
            DefaultComboBoxModel<String> modeloMunicipios = new DefaultComboBoxModel<>(municipios);
            Municipio.setModel(modeloMunicipios);
            
            // Re-aplicar el filtro de escritura (importante si el modelo cambió)
            // Ya lo habilitamos una vez en inicializarDepartamentos(), pero la lógica de 
            // ComboBoxFiltro debe ser capaz de manejar el cambio de modelo si lo requiere.
            // Para la implementación dada, con habilitarlo una vez es suficiente, pero 
            // esta llamada garantiza que el filtro esté activo con el nuevo modelo.
            FiltroDepartamento.habilitarFiltro(Municipio); 
            
        } else {
            // Si no hay selección o no hay datos (ej. al inicio)
            Municipio.setModel(new DefaultComboBoxModel<>(new String[]{"-- Sin municipios disponibles --"}));
        }
        
        // Asegurarse de que el municipio comience vacío
        Municipio.setSelectedIndex(-1);
        
        // Limpiar el texto en el editor del municipio al cambiar de departamento
        if (Municipio.isEditable() && Municipio.getEditor().getEditorComponent() instanceof javax.swing.text.JTextComponent) {
            ((javax.swing.text.JTextComponent) Municipio.getEditor().getEditorComponent()).setText("");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Txt_numero_predial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Txt_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Txt_empresa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Txt_telefono_empresa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Departamento = new javax.swing.JComboBox<>();
        Municipio = new javax.swing.JComboBox<>();
        Txt_vereda = new javax.swing.JTextField();
        Btn_registrar = new javax.swing.JButton();
        Btn_cancelar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Txt_documento_dueno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 112));
        jLabel1.setText("Registro de lugar de producción");

        jLabel2.setText("Número de predial:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Empresa responsable:");

        jLabel5.setText("Teléfono empresa:");

        jLabel6.setText("Departamento:");

        jLabel7.setText("Municipio:");

        jLabel8.setText("Vereda:");

        Departamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartamentoActionPerformed(evt);
            }
        });

        Municipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Municipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MunicipioActionPerformed(evt);
            }
        });

        Btn_registrar.setBackground(new java.awt.Color(51, 153, 0));
        Btn_registrar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_registrar.setText("Registrar");
        Btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_registrarActionPerformed(evt);
            }
        });

        Btn_cancelar.setBackground(new java.awt.Color(51, 153, 0));
        Btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_cancelar.setText("Cancelar");
        Btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_cancelarActionPerformed(evt);
            }
        });

        jLabel9.setText("Documento dueño:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Btn_cancelar)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(Btn_registrar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Txt_numero_predial)
                                    .addComponent(Txt_nombre)
                                    .addComponent(Txt_empresa)
                                    .addComponent(Txt_telefono_empresa)
                                    .addComponent(Departamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Municipio, 0, 159, Short.MAX_VALUE)
                                    .addComponent(Txt_vereda)
                                    .addComponent(Txt_documento_dueno))))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Txt_numero_predial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Txt_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Txt_telefono_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Txt_vereda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Txt_documento_dueno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_cancelar)
                    .addComponent(Btn_registrar))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_cancelarActionPerformed
        // Cierra esta ventana
        this.dispose();
        // Abre la otra ventana
        MenuProductor menuP = new MenuProductor();
        menuP.setVisible(true);
        menuP.setLocationRelativeTo(null);
    }//GEN-LAST:event_Btn_cancelarActionPerformed

    private void DepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartamentoActionPerformed
        
    }//GEN-LAST:event_DepartamentoActionPerformed

    private void MunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MunicipioActionPerformed
        
    }//GEN-LAST:event_MunicipioActionPerformed
    
    private ControladorLugarProduccion control = new ControladorLugarProduccion();
    private void Btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_registrarActionPerformed
        // 1. Validar que todos los campos obligatorios estén llenos
        if (!validarCamposVacios()) {
            return; // Detiene el proceso si hay campos vacíos.
        }

        // 2. Obtener los datos de los campos
        String numeroPredial = Txt_numero_predial.getText().trim();
        String nombreLugar = Txt_nombre.getText().trim();
        String empresa = Txt_empresa.getText().trim();
        String telefonoEmpresa = Txt_telefono_empresa.getText().trim();
        String vereda = Txt_vereda.getText().trim();
        String documentoDueno = Txt_documento_dueno.getText().trim(); // <--- Ya se incluyó el campo de dueño

        // Obtener los valores de los JComboBox
        String departamento = (String) Departamento.getSelectedItem();
        String municipio = (String) Municipio.getSelectedItem();
        
        // Se valida la selección de departamento/municipio (se mantiene la validación)
        if (municipio == null || departamento == null || 
            municipio.equals("-- Seleccione Departamento Primero --") || 
            departamento.equals("-- Seleccione --")) { // Agregamos una validación genérica para el dep.
            JOptionPane.showMessageDialog(this, "Debe seleccionar el departamento y el municipio correspondientes.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // *************************************************************************
        // *** 3. LLAMADA AL CONTROLADOR Y MANEJO DE LA RESPUESTA ***
        // *************************************************************************
        
        // Llama al método del controlador, el cual maneja todas las validaciones
        // de formato (dígitos, longitud, etc.) y el guardado en la BD.
        String resultadoRegistro = control.validarRegistro(
            numeroPredial, 
            nombreLugar, 
            empresa,
            telefonoEmpresa, 
            vereda, 
            documentoDueno, // ¡Pasamos el documento del dueño!
            departamento, 
            municipio
        );
        
        // 4. Analizar la respuesta del controlador
        if (resultadoRegistro.startsWith("Registro exitoso.")) {
            // El controlador devuelve "Registro exitoso. Número ICA asignado: XXX"
            JOptionPane.showMessageDialog(this,
                            resultadoRegistro, // Muestra el mensaje con el ICA
                            "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            // El controlador devuelve un mensaje de error (ej: "El Predial debe contener...")
            JOptionPane.showMessageDialog(this,
                            "Error en el registro: " + resultadoRegistro,
                            "Error de Validación/Registro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Btn_registrarActionPerformed
    
    private void limpiarCampos() {
        Txt_numero_predial.setText("");
        Txt_nombre.setText("");
        Txt_empresa.setText("");
        Txt_telefono_empresa.setText(""); 
        Txt_vereda.setText(""); 
    }
    
    private boolean validarCamposVacios() {
        // Campos de texto comunes
        if (Txt_numero_predial.getText().trim().isEmpty() ||
            Txt_nombre.getText().trim().isEmpty() ||
            Txt_empresa.getText().trim().isEmpty() ||
            Txt_telefono_empresa.getText().trim().isEmpty() ||
            Txt_vereda.getText().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro_lugar_produccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_lugar_produccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_lugar_produccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_lugar_produccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_lugar_produccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_cancelar;
    private javax.swing.JButton Btn_registrar;
    private javax.swing.JComboBox<String> Departamento;
    private javax.swing.JComboBox<String> Municipio;
    private javax.swing.JTextField Txt_documento_dueno;
    private javax.swing.JTextField Txt_empresa;
    private javax.swing.JTextField Txt_nombre;
    private javax.swing.JTextField Txt_numero_predial;
    private javax.swing.JTextField Txt_telefono_empresa;
    private javax.swing.JTextField Txt_vereda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

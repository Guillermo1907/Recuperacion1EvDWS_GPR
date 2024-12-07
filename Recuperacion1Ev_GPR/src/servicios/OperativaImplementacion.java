package servicios;

import dtos.Consulta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class OperativaImplementacion implements OperativaInterfaz {
    private final List<Consulta> consultas;
    private final FicheroInterfaz ficheroServicio;

    public OperativaImplementacion() {
        this.consultas = new ArrayList<>();
        this.ficheroServicio = new FicheroImplementacion();

        // Cargar datos desde el archivo al inicializar la aplicación
        cargarDatosDesdeArchivo("citas.txt");
    }

    
 // metodo para cargar las consultas desde el archivo citas.txt
    public void cargarDatosDesdeArchivo(String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length < 6) continue; 
                
                String dni = campos[0].trim();
                String nombrePaciente = campos[1].trim();
                String especialidad = campos[2].trim();
                String fechaString = campos[3].trim();
                String horaString = campos[4].trim();
                boolean asistido = Boolean.parseBoolean(campos[5].trim());

                LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime hora = LocalTime.parse(horaString, DateTimeFormatter.ofPattern("HH:mm"));

                // Crea la consulta
                Consulta consulta = new Consulta(dni, nombrePaciente, especialidad, fecha, hora, asistido);
                consultas.add(consulta);
            }
        } catch (IOException | DateTimeParseException e) {
            System.out.println("Error al leer el archivo de citas: " + e.getMessage());
        }
    }

   
    public void registrarLlegada(String dni) {
        if (validarDni(dni)) {
            if (tieneCitaHoy(dni)) {
                System.out.println("Espere su turno para la consulta. Su especialista le avisará.");
            } else {
                System.out.println("No dispone de cita previa para hoy.");
            }
        } else {
            System.out.println("El DNI introducido no es válido.");
        }
    }

    // Valida el formato y la letra del DNI
  
    public boolean validarDni(String dni) {
        
        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        char[] letrasDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 
                             'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        int numero = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);
        char letraCalculada = letrasDni[numero % 23];
        return letra == letraCalculada;
    }
    
    
    // Verifica si el DNI tiene una cita hoy
    private boolean tieneCitaHoy(String dni) {
        LocalDate hoy = LocalDate.now();
        for (Consulta consulta : consultas) {
            if (consulta.getDni().equals(dni) && consulta.getFecha().equals(hoy)) {
                return true;
            }
        }
        return false;
    }

   
    public void mostrarConsultas(String fecha, String especialidad) {
        List<Consulta> resultado = filtrarConsultas(fecha, especialidad);
        if (resultado.isEmpty()) {
            System.out.println("No hay consultas para la fecha y especialidad indicada.");
        } else {
            for (Consulta consulta : resultado) {
                System.out.println(consulta); 
            }
        }
    }

    
    public void imprimirConsultas(String fecha, String especialidad) {
        List<Consulta> asistidas = filtrarConsultas(fecha, especialidad);
        String nombreArchivo = "citasConAsistencia-" + fecha.replace("-", "") + ".txt";
        ficheroServicio.escribirConsultas(nombreArchivo, asistidas);
    }

    private List<Consulta> filtrarConsultas(String fecha, String especialidad) {
        List<Consulta> resultado = new ArrayList<>();
        LocalDate fechaBusqueda = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        for (Consulta consulta : consultas) {
            if (consulta.getFecha().equals(fechaBusqueda) && consulta.getEspecialidad().equalsIgnoreCase(especialidad)) {
                resultado.add(consulta);
            }
        }
        return resultado;
    }
}

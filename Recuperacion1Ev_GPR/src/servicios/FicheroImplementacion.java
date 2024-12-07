package servicios;

import dtos.Consulta;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FicheroImplementacion implements FicheroInterfaz {
    @Override
    public void escribirConsultas(String archivo, List<Consulta> consultas) {
        try (FileWriter writer = new FileWriter(archivo)) {
            for (Consulta consulta : consultas) {
                writer.write(consulta.toString() + "\n");
            }
            System.out.println("El archivo se ha creado: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}

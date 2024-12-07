package servicios;

import java.util.List;

import dtos.Consulta;

public interface FicheroInterfaz {
	 void escribirConsultas(String archivo, List<Consulta> consultas);
}

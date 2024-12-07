package servicios;

public interface OperativaInterfaz {
	
    //Registra la llegada de un paciente basándose en su DNI.
     
    	void registrarLlegada(String dni);
  
    // Muestra las consultas en consola según la fecha y especialidad 
    	void mostrarConsultas(String fecha, String especialidad);

    //Imprime las consultas filtradas por fecha y especialidad en un fichero txt.
    	void imprimirConsultas(String fecha, String especialidad);
}

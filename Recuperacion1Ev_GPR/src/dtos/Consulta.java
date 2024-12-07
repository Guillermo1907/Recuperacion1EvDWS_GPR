package dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
		
    private String dni;
	private String nombrePaciente;
	private String especialidad;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean asistido;

public Consulta(String dni,String nombrePaciente, String especialidad, LocalDate fecha, LocalTime localTime, boolean asistido) {
	this.dni = dni;
    this.nombrePaciente = nombrePaciente;
    this.especialidad = especialidad;
    this.fecha = fecha;
    this.hora = localTime;
    this.asistido = asistido;
}


public String getDni() {
	return dni;
}

public void setDni(String dni) {
	this.dni = dni;
}

public String getNombrePaciente() {
	return nombrePaciente;
}

public void setNombrePaciente(String nombrePaciente) {
	this.nombrePaciente = nombrePaciente;
}

public String getEspecialidad() {
	return especialidad;
}

public void setEspecialidad(String especialidad) {
	this.especialidad = especialidad;
}

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

public LocalTime getHora() {
	return hora;
}

public void setHora(LocalTime hora) {
	this.hora = hora;
}

public boolean isAsistido() {
	return asistido;
}

public void setAsistido(boolean asistido) {
	this.asistido = asistido;
}


@Override
public String toString() {
	return "Consulta [dni=" + dni + ", nombrePaciente=" + nombrePaciente + ", especialidad=" + especialidad + ", fecha="
			+ fecha + ", hora=" + hora + ", asistido=" + asistido + "]";
}









}


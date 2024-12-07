package servicios;

import java.util.Scanner;

public class MenuImplementacion implements MenuInterfaz {
    private final OperativaInterfaz opServicio;

    public MenuImplementacion() {
        this.opServicio = new OperativaImplementacion();
    }

    Scanner scan = new Scanner(System.in);
    
    
    public void mostrarMenuPrincipal() {
        int opcion;

        do {
            System.out.println("----------------------------");
            System.out.println("1. Registrar llegada");
            System.out.println("2. Listado de consultas");
            System.out.println("3. Salir");
            System.out.println("----------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    registrarLlegada();
                    break;
                case 2:
                    mostrarSubMenuConsultas();
                    break;
                case 3:
                    System.out.println("Saliendo..");
                    break;
                default:
                    System.out.println("Respuesta no válida. Inténtelo de nuevo!");
            }
        } while (opcion != 3);
    }

    private void registrarLlegada() {
        String dni;
        System.out.print("Introduce su DNI: ");
        scan.nextLine();  
        dni = scan.nextLine();
        
        opServicio.registrarLlegada(dni);
    }

    
    public void mostrarSubMenuConsultas() {
    	 
    	int accion;
    	int especialidadOpcion;
    	String fecha;
    	
    	 do {
    	        System.out.println("--------------Submenu-----------------");
    	        System.out.println("1. Mostrar consultas");
    	        System.out.println("2. Imprimir consultas");
    	        System.out.println("3. Volver al menú principal");
    	        System.out.println("-------------------------------");
    	        System.out.print("Seleccione una acción: ");
    	        
    	        accion = scan.nextInt();

    	        if (accion == 3) {
    	            return; 
    	        } else if (accion < 1 || accion > 3) {
    	            System.out.println("Respuesta no válida. Inténtelo de nuevo!");
    	            continue;
    	        }

    	        
    	        System.out.println("\n--- Seleccione una especialidad ---");
    	        System.out.println("1. Psicología");
    	        System.out.println("2. Traumatología");
    	        System.out.println("3. Fisioterapia");
    	        System.out.print("Opción: ");
    	        especialidadOpcion = scan.nextInt();

    	        if (especialidadOpcion < 1 || especialidadOpcion > 3) {
    	            System.out.println("Especialidad no válida. Inténtelo de nuevo!");
    	            continue;
    	        }

    	        String especialidad = "";
    	        switch (especialidadOpcion) {
                case 1:
                    especialidad = "Psicología";
                    break;
                case 2:
                    especialidad = "Traumatología";
                    break;
                case 3:
                    especialidad = "Fisioterapia";
                    break;
                default:
                    System.out.println("Especialidad no válida.");
                    continue;
            }

    	      
    	        scan.nextLine(); 
    	        System.out.print("\nIngrese la fecha (dd-MM-yyyy): ");
    	        fecha = scan.nextLine();

    	        // Esto valida el formato de fecha
    	        if (!fecha.matches("\\d{2}-\\d{2}-\\d{4}")) {
    	            System.out.println("Formato de fecha inválido. Inténtelo de nuevo!");
    	            continue;
    	        }

    	     
    	        switch (accion) {
                case 1:
                	opServicio.mostrarConsultas(fecha, especialidad);
                    break;
                case 2:
                	opServicio.imprimirConsultas(fecha, especialidad);
                    break;
                default:
                    System.out.println("Acción no válida.");
                    break;
            }
    	    } while (true);

        
    }
}

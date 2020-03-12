/**
 * Proyecto 1: Colecciones
 * Programación Orientada a Objetos
 * 2020-2
 *Grupo: 2
 Profesor: M.I Edgar Tista Garcia
 @author Callejas Sanchez Juan Antonio, Duràn Gonzàlez Lizethm Juan Antonio
 @version 1.0
 */

 import java.util.Scanner;

public class Main {

    public static void mostrarMenu() {
        
        String nameHotel = "\t\tHotel Villa del Mar";
        
        System.out.println("\n\t==================================");
        System.out.println(nameHotel.toUpperCase());
        System.out.println("\n\t1. Registrar nuevo cliente");
        System.out.println("\t2. Reservar habitación");
        System.out.println("\t3. Modificar reservación");
        System.out.println("\t4. Ver clientes registrados");
        System.out.println("\t5. Ver reservación");
        System.out.println("\t6. Ver habitaciones ocupadas");
        System.out.println("\t7. Ver habitaciones disponibles");
        System.out.println("\t8. Salir\n");
            
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        boolean condition = true; 
        Habitacion.cargarHabitaciones();

        do {
            Main.mostrarMenu();

            System.out.print("Elige una opción --> ");
            int key = sc.nextInt();
            switch (key) {
                case 1:
                    System.out.println("\n\tRegistrar nuevo cliente");
                   
                    break;

                case 2:
                    System.out.println("\tReservar habitación");
                    
                    break;

                case 3:
                    System.out.println("\tModificar reservación");

                    break;
                case 4:
                    System.out.println("\tVer clientes registrados");
                    
                    break;
                case 5:
                    System.out.println("\tVer reservación");
                    
                    break;
                case 6:
                    System.out.println("\n\t== Ver habitaciones ocupadas ==");

                    System.out.println("\nIngresa la fecha para la cual se buscaran habitaciones ocupadas");
                    System.out.print("(dd-mm-yy)  --> ");
                    Habitacion.verHabitacionesOcupadas(sc.next());
                    
                    break;
                case 7:
                    System.out.println("\n\t==Ver habitaciones disponibles==");
                    System.out.println("\nIngresa la fecha para la cual se verificara disponibilidad");
                    System.out.print("(dd-mm--aa)  --> ");
                    Habitacion.verHabitacionesDisponibles(sc.next());
                    
                    break;

                case 8:
                    System.out.println("\nCerrando Sesión...");
                    condition = false;
                    break;
            
            
                default:
                    System.out.println("opcion no valida");
                    break;
            }
        } while (condition);

        sc.close();
        
    }
    
}
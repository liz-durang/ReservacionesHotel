/**
 * Proyecto 1: Colecciones
 * Programación Orientada a Objetos
 * 2020-2
 *Grupo: 2
 Profesor: M.I Edgar Tista Garcia
 @author Callejas Sanchez Juan Antonio, Duràn Gonzàlez Lizethm Juan Antonio
 @version 1.0
 */
package Proyecto1POO;

 import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

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


    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        boolean condition = true; 
        //habitaciones precargadas
        Habitacion.cargarHabitaciones();
        //Clientes precargados
        Cliente liz = new Cliente("Lizeth", "liz@gmail.com");
        Cliente.BD_Clientes.add(liz);
        
        Cliente juan = new Cliente("Juan", "juan@gmail.com");
        Cliente.BD_Clientes.add(juan);
        Cliente juanA = new Cliente("Juan Antonio", "Ant@gmail.com");
        Cliente.BD_Clientes.add(juanA);
        Cliente lia = new Cliente("Lia", "lia@gmail.com");
        Cliente.BD_Clientes.add(lia);

        Map <Habitacion,Cliente> BD_reservaciones = new HashMap<>();

        do {
            Main.mostrarMenu();

            
            System.out.print("Elige una opción --> ");
            int key = sc.nextInt();
            switch (key) {
                case 1:
                    System.out.println("\n\t ==Registrar nuevo cliente==");
                    System.out.print("Nombre de cliente: ");        
                    String nombre = sc.next();
                    System.out.print("Email del cliente: ");        
                    String email = sc.next();
                    Cliente nuevoCliente = new Cliente(nombre, email);
                    if(nuevoCliente.registrarCliente()){
                        System.out.println("\nCliente registrado con exito");
                    }else{
                        System.out.println("\nCliente no se pudo registrar, intente de nuevo");
                    }
                    break;

                case 2:
                    
                    System.out.println("\n\t==Reservar habitación==");
                    System.out.println("\n¿Que número de folio de cliente hara la reservación?\n");
                    
                    for (Cliente cliente : Cliente.BD_Clientes ) {
                        System.out.println("\t*" + cliente.getId()+" " + cliente.getNombre());
                    }
                    System.out.print("\n-->");
                    int nocliente1 = sc.nextInt();
                    for (Cliente cliente : Cliente.BD_Clientes) {
                        if (nocliente1 == cliente.getId()){
                            cliente.hacerReservacion(BD_reservaciones);
                        }
                        
                        
                    }
                    break;

                case 3:
                    System.out.println("\tModificar reservación");
                    System.out.println("\n¿Que número de folio de cliente modificara la reservación?\n");
                    for (Cliente cliente : Cliente.BD_Clientes ) {
                        System.out.println("\t*" + cliente.getId()+" " + cliente.getNombre());
                    }
                    System.out.print("\n--> ");
                    int nocliente2 = sc.nextInt();
                    for (Cliente cliente : Cliente.BD_Clientes) {
                        if (nocliente2 == cliente.getId()){
                            cliente.modificarReservacion(BD_reservaciones);
                            break;
                        }
                        
                    }
                    break;
                    
                case 4:
                    System.out.println("\n\tVer clientes registrados\n");
                    Cliente.verClientes(BD_reservaciones);
                    break;
                    
                case 5:
                    System.out.println("\tVer reservación");
                    System.out.println("\n¿De qué número de folio de cliente quiere ver la reservación?\n");
                    for (Cliente cliente : Cliente.BD_Clientes ) {
                        System.out.println("\t*" + cliente.getId()+" " + cliente.getNombre());
                    }
                    System.out.print("\n--> ");
                    int nocliente3 = sc.nextInt();
                    for (Cliente cliente : Cliente.BD_Clientes) {
                        if (nocliente3 == cliente.getId()){
                            if (BD_reservaciones.containsValue(cliente)) 
                                cliente.verReservacion(BD_reservaciones);
                            
                            else
                                System.out.println("El cliente no tiene niguna reservación");
                            
                        break;
                        }
                    }
                    
                    break;
                case 6:
                    System.out.println("\n\t== Ver habitaciones ocupadas ==");

                    System.out.println("\nIngresa la fecha para la cual se buscaran habitaciones ocupadas");
                    System.out.print("(dd-mm-aa)  --> ");
                    Habitacion.verHabitacionesOcupadas(sc.next());
                    
                    break;
                case 7:
                    System.out.println("\n\t==Ver habitaciones disponibles==");
                    System.out.println("\nIngresa la fecha para la cual se verificara disponibilidad");
                    System.out.print("(dd-mm-aa)  --> ");
                    String fecha = sc.next();
                    System.out.println("\n¿Para cuántas personas?");
                    System.out.println("--> ");
                    int cupo = sc.nextInt();
                    Habitacion.verHabitacionesDisponibles(fecha,cupo );
                    
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

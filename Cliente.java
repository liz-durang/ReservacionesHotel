

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

import java.time.*;
import java.time.format.*;
import java.util.*;

public class Cliente{
    static Set <Cliente> BD_Clientes = new HashSet<>();
    static int noCliente = 200; 

    int id;
    String nombre; 
    String email; 
    int noHuespedes;

    LocalDate checkIn;
    LocalDate checkOut; 

    public Cliente ( String name, String email){
        noCliente ++;
        this.nombre = name; 
        this.email = email; 
        this.id = noCliente; 
    }
   
    /*
    *Getters y Setters
    */

    /** 
     * Leer contador de clientes. 
     *  @return int
    */
    public int getNoCliente() {
        return noCliente;
    }

    /** 
     * Ingresar contador de clientes.
     * @param noCliente
     */
    public void setNoCliente( int noCliente) {
        Cliente.noCliente = noCliente;
    }

    /** 
     * Leer numero de folio del cliente.
     * @return int
     */
    public int getId() {
        return id;
    }

    
    /** 
     * Ingresar numero de folio del cliente.
     * @param id
     */
    public void setId( int id) {
        this.id = id;
    }

    
    /** 
     * Leer nombre del cliente.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    
    /** 
     * Ingresar nombre de cliente.
     * @param nombre
     */
    public void setNombre( String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * Leer email del cliente.
     * @return String
     */
    public String getEmail() {
        return email;
    }

    
    /** 
     * Ingresar email del cliente.
     * @param email
     */
    public void setEmail( String email) {
        this.email = email;
    }

    
    /** 
     * Leer numero de huespedes que se consideraran para la reservación.
     * @return int
     */
    public int getNoHuespedes() {
        return noHuespedes;
    }

    
    /** 
     * Ingresar numero de huespedes que se consideraran para la reservación.
     * @param noHuespedes
     */
    public void setNoHuespedes( int noHuespedes) {
        this.noHuespedes = noHuespedes;
    }

    
    /** 
     * Leer fecha de entrada (Check In).
     * @return LocalDate
     */
    public LocalDate getCheckIn() {
        return checkIn;
    }

    
    /** 
     * Ingresar fecha de entrada (Check In).
     * @param checkIn
     */
    public void setCheckIn( LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    
    /** 
     * Leer fecha de salida (Check Out).
     * @return LocalDate
     */
    public LocalDate getCheckOut() {
        return checkOut;
    }

    
    /** 
     * Ingresar fecha de salida (Check Out).
     * @param checkOut
     */
    public void setCheckOut( LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    /*
    * Métodos
    */

    /** 
     * Sobrescritura del método toString para Cliente.
     * @return String
    */
    @Override
    public String toString() {

        return  "Cliente " 
        + this.id + "\n"+ this.nombre + " " +this.email + " " +this.noHuespedes + " huspedes\n"
        + this.getCheckIn() + "----"+this.getCheckOut() 
        + "\n";
    }

    /** 
     * @return boolean
     * Ingresa el cliente a la base de datos de clientes.
     */
    public boolean registrarCliente (){
        BD_Clientes.add(this);
        return true;
    }

    /** 
     * Se pregunta numero de personas que haran la reservacion
     * y su fecha de ingreso (Check In)
     * para mostrar las habitaciones disponibles.
     * Al elegir la habitacion, se pide una fecha de salida.
     * El cliente con la habitacion reservada se ingresa a la base de datos de reservaciones
     * y la agenda de la habitacion se actualiza con las fechas de llegada y salida dell cliente.
     * @param BD_reservaciones
     */
    public void hacerReservacion (final Map <Habitacion,Cliente> BD_reservaciones){
        DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate estancia;
        Scanner sc = new Scanner(System.in);
        System.out.println(this.getNombre()+ ", ¿Para cuantas personas se hará la reservación?");
        System.out.print("\n--> ");
        this.noHuespedes = sc.nextInt();
        String fecha;
        if (noHuespedes <= 6) {

            
            System.out.println("Ingresa tu fecha de Check In");
            System.out.print("\n(dd-mm-aa)--> ");
            fecha = sc.next();
            this.checkIn = LocalDate.parse(fecha, isoFecha);
            
            System.out.println("\nLas habitaciones que tenemos disponibles son: ");
            Habitacion.verHabitacionesDisponibles(fecha, noHuespedes);
            
            for (Habitacion habitacion : Habitacion.BD_habitaciones) {
                if(habitacion.cupo == this.noHuespedes){
                    if(!desocupados(habitacion, checkIn,checkOut))
                        return;
                }
            }

            System.out.println("\n¿Que número de habitacion le gustaria reservar?");
            System.out.print("--> ");
            final int noHabitacion = sc.nextInt();

            System.out.println("Ingresa tu fecha de Check Out");
            System.out.print("\n(dd-mm-aa)--> ");
            fecha = sc.next();
            checkOut = LocalDate.parse(fecha, isoFecha);
            
            for ( Habitacion habitacion : Habitacion.BD_habitaciones) {

                if (noHabitacion  == habitacion.getNoHabitacion()) {
                    estancia = checkIn;
                    do {
                        habitacion.agenda.put(estancia, this);
                        estancia = estancia.plusDays(1);
                    } while (estancia.equals(checkOut)!=true);

                    habitacion.agenda.put(checkOut, this);
                    BD_reservaciones.put(habitacion, this);
                }
            }
            System.out.println("Reservacion realizada");
            
        
    }
        else{
            System.out.println("No tenemos habitaciones disponibles para ese numero de personas");
        }
       
    }

    
    /** 
     * Se pregunta al cliente que fecha quiere modificar de su estancia: la de entrada o la de salida.
     * De acuerdo a la fecha elegida, se modifica la agenda de la habitación
     * y los datos de entrada y salida del cliente.
     * @param BD_reservaciones
     */
    public void modificarReservacion(final Map <Habitacion,Cliente> BD_reservaciones){
        final DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
        final Scanner sc = new Scanner(System.in);

        System.out.println("\n\t"+ this.getNombre() + ", ¿Que deseas hacer?");
        System.out.println("\n\t1. Adelantar fecha de llegada");
        System.out.println("\t2. Aplazar fecha de salida");
        System.out.println("\t3. Aplazar fecha de llegada");
        System.out.println("\t4. Adelantar fecha de salida");

        System.out.print("\nElige una opción --> ");
        final int opc = sc.nextInt();

        String nueva;
        LocalDate estancia, copia;

        switch (opc) {
            case 1:
                System.out.println("\n\t==Adelantar fecha de llegada==");
                System.out.println("\nTu nueva fecha de llegada: ");
                System.out.print("(dd-mm-aa)--> ");
                nueva = sc.next();
                estancia = LocalDate.parse(nueva, isoFecha);
                copia = estancia;

                for ( Habitacion habitacion : BD_reservaciones.keySet()) {
                    if (habitacion.agenda.containsValue(this)){
                        if(!desocupados(habitacion, copia.plusDays(1), checkIn.minusDays(1))){
                            System.out.println("No se pudo completar la accion");
                            System.out.println("Habitacion "+habitacion.noHabitacion+"ocupada para la fecha indicada");
                            return;
                        }
                        do {
                            habitacion.agenda.put(estancia, this);
                            estancia = estancia.plusDays(1);
                        } while (!estancia.isEqual(this.checkIn));
                        this.setCheckIn(copia);
                    }
                   
                    break;        
                    
                }

                System.out.println("\n\tTu reservacion ha sido modificada");

                break;

            case 2: 
                System.out.println("\t==Aplazar fecha de salida==");
                System.out.println("Tu nueva fecha de salida: ");
                System.out.print("(dd-mm-aa)--> ");
                nueva = sc.next();
                estancia = LocalDate.parse(nueva, isoFecha);
                copia = estancia;

                for(Habitacion habitacion: BD_reservaciones.keySet()){
                    if(habitacion.agenda.containsValue(this)){
                        if(!desocupados(habitacion, checkOut.plusDays(1), copia)){
                            System.out.println("No se pudo completar la accion");
                            System.out.println("Habitacion "+habitacion.noHabitacion+"ocupada para la fecha indicada");
                            return;
                        }
                        while(checkOut.isEqual(estancia)!=true){
                            checkOut = checkOut.plusDays(1);
                            habitacion.agenda.put(checkOut, this); 
                        }
                        this.setCheckOut(copia);
                    }
                }
                break;

            case 3: 
                System.out.println("\t==Aplazar fecha de llegada==");
                System.out.println("Tu nueva fecha de llegada: ");
                System.out.print("(dd-mm-aa)--> ");
                nueva = sc.next();
                estancia = LocalDate.parse(nueva, isoFecha);
                copia = checkIn;
                this.setCheckIn(estancia);
                
                for (final Habitacion habitacion : BD_reservaciones.keySet()) {
                    if (habitacion.agenda.containsValue(this)){
                        while(copia.equals(estancia)!=true){
                            habitacion.agenda.remove(copia);
                            copia = copia.plusDays(1);
                        }
                    }
                }
                System.out.println("\n\tTu reservacion ha sido modificada");
                break;

            case 4: 
                System.out.println("\t4. Adelantar fecha de salida");

                System.out.println("Tu nueva fecha de salida: ");
                System.out.print("(dd-mm-aa) --> ");
                nueva = sc.next();
                estancia = LocalDate.parse(nueva, isoFecha);
                copia = checkOut;
                this.setCheckOut(estancia);
 
                for(final Habitacion habitacion: BD_reservaciones.keySet()){
                    if(habitacion.agenda.containsValue(this)){
                        while(estancia.equals(copia)!=true){
                            estancia = estancia.plusDays(1);
                            habitacion.agenda.remove(estancia, this);
                        }
                    }
                }
                System.out.println("\n\tTu reservacion ha sido modificada");
                break;

            default:
                System.out.println("Opción no valida");
                break;
            }
        }

        
    

    
    /** 
     * De la base de datos de reservaciones, 
     * se imprimen todos los datos de las reservaciones, 
     * los datos del cliente y de la habitacion asociada a el.
     * @param BD_reservaciones
     */
    public static void verClientes(final Map <Habitacion,Cliente> BD_reservaciones){
       if (BD_reservaciones.size()<1) {
           System.out.println("No hay reservaciones");
           for (final Cliente cliente : BD_Clientes) {
            System.out.println("\n=====================================\nCliente");
            System.out.println("\tCliente: "+cliente.getNombre());
            System.out.println("\tEmail: "+cliente.getEmail());
            System.out.println("=====================================");
           }
       }

       else{ 
            for (final Cliente cliente : BD_Clientes) {
                System.out.println("\n=====================================\nCliente");
                System.out.println("\tCliente: "+cliente.getNombre());
                System.out.println("\tEmail: "+cliente.getEmail());
                System.out.println("=====================================");
                cliente.verReservacion(BD_reservaciones);
                System.out.println("\n");
            }
        }
    }

    
  
    
    /** 
     * Se imprime la informacion de un cliente en particular
     * Tanto datos del cliente como datos de la habitacion(si es que ya esta reservado)
     * @param BD_reservaciones
     */
    public void verReservacion(final Map <Habitacion,Cliente> BD_reservaciones){
        for( Habitacion habitacion: BD_reservaciones.keySet()){
            if(habitacion.agenda.containsValue(this)){
                System.out.println("=====================================\nReservacion");
                System.out.println("\tNoCliente: "+this.getNoCliente());
                System.out.println("\tCliente: "+ this.getNombre());
                System.out.println("\tHabitacion: "+ habitacion.getNoHabitacion());
                System.out.println("\tNúmero de huespedes: "+ this.getNoHuespedes() );
                System.out.println("\tFecha de llegada: "+ this.getCheckIn().getDayOfMonth()+"/"
                + this.getCheckIn().getMonth()+"/"+ this.getCheckIn().getYear());
                System.out.println("\tFecha de salida: "+ this.getCheckOut().getDayOfMonth()+"/"
                + this.getCheckOut().getMonth()+"/"+ this.getCheckOut().getYear());
                System.out.println("=====================================");
            break;
            }
        else{
                System.out.println("Cliente no tiene ninguna reservación");
            }
        }
    }
    
    /** 
     * Se revisa si las habitaciones estan ocupadas en las fechas que el usuario quiere reservar.
     * Regresa true si encuentra la fecha indicada donde el usuario quiere apartar la habitación.
     * 
     * @param habitacion
     * @param in
     * @param out
     * 
     * @return boolean
     */
    public boolean desocupados(Habitacion habitacion, LocalDate in, LocalDate out){
        LocalDate copia1 = in;
        LocalDate copia2 = out;
        if(habitacion.agenda.isEmpty())
            return true;
        
        
        else if(habitacion.agenda.containsKey(copia1)||habitacion.agenda.containsKey(copia2) )
            return false;
        
        else{
            do{
                if(habitacion.agenda.containsKey(copia1))
                    return false;
                
                in.plusDays(1);
            }while(!in.equals(copia2));
            
            if(habitacion.agenda.containsKey(copia2))
                return false;
            
        }
        return true;
    }


}

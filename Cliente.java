

/**
 * Proyecto 1: Colecciones
 * Programación Orientada a Objetos
 * 2020-2
 *Grupo: 2
 Profesor: M.I Edgar Tista Garcia
 @author Callejas Sanchez Juan Antonio, Duràn Gonzàlez Lizeth, Mancilla Flores Juan Antonio
 @version 1.0
 */

import java.time.*;
import java.time.format.*;
import java.util.*;


/**
 * Clase Cliente
 * Contiene un HashSet de clientes que simula una base de datos de clientes
 * Crea al objeto cliente
 * Se encarga de hacer y modificar reservaciones
 */


public class Cliente{

    static List <Cliente> BD_Clientes = new LinkedList<>();
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
     * @return int
     */
    public int getNoCliente() {
        return noCliente;
    }

    
    /** 
     * @param noCliente
     */
    public void setNoCliente( int noCliente) {
        Cliente.noCliente = noCliente;
    }

    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId( int id) {
        this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre( String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    
    /** 
     * @param email
     */
    public void setEmail( String email) {
        this.email = email;
    }

    
    /** 
     * @return int
     */
    public int getNoHuespedes() {
        return noHuespedes;
    }

    
    /** 
     * @param noHuespedes
     */
    public void setNoHuespedes( int noHuespedes) {
        this.noHuespedes = noHuespedes;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getCheckIn() {
        return checkIn;
    }

    
    /** 
     * @param checkIn
     */
    public void setCheckIn( LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getCheckOut() {
        return checkOut;
    }

    
    /** 
     * @param checkOut
     */
    public void setCheckOut( LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    
    /** 
     * @return String
     */
    /*
    * Métodos
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
     * Ingresa el cliente a la base de datos de clientes
     */
    public boolean registrarCliente (){
        BD_Clientes.add(this);
        return true;
    }

    
    /** 
     * Se pregunta numero de personas que haran la reservacion
     * Y fecha de ingreso
     * Para mostrar las habitaciones disponibles
     * Al elegir la habitacion, se pide una fecha de salida
     * El cliente con la habitacion reservada se ingresa a la base de datos de reservaciones
     * La agenda de la habitacion se actualiza con las fechas de llegada y salida dell cliente
     * @param BD_reservaciones
     */
    public void hacerReservacion (final Map <Habitacion,Cliente> BD_reservaciones){
        DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
        Scanner sc = new Scanner(System.in);
        System.out.println(this.getNombre()+ ", ¿Para cuantas personas se hará la reservación?");
        System.out.print("\n--> ");
        int noHuespedes = sc.nextInt();
        this.noHuespedes = noHuespedes;
        LocalDate checkIn;
        LocalDate checkOut;
        String fecha;
        if (noHuespedes <= 6) {

            
            System.out.println("Ingresa tu fecha de Check In");
            System.out.print("\n(dd-mm-aa)--> ");
            fecha = sc.next();
            checkIn = LocalDate.parse(fecha, isoFecha);
            this.setCheckIn(checkIn);
            
            
           
            System.out.println("\nLas habitaciones que tenemos disponibles son: ");
            Habitacion.verHabitacionesDisponibles(fecha, noHuespedes);

            System.out.println("\n¿Que número de habitacion le gustaria reservar?");
            System.out.print("--> ");
            final int noHabitacion = sc.nextInt();

            System.out.println("Ingresa tu fecha de Check Out");
            System.out.print("\n(dd-mm-aa)--> ");
            fecha = sc.next();
            checkOut = LocalDate.parse(fecha, isoFecha);
            this.setCheckOut(checkOut);
            
            for ( Habitacion habitacion : Habitacion.BD_habitaciones) {

                if (noHabitacion  == habitacion.getNoHabitacion()) {
                    do {
                        habitacion.agenda.put(checkIn, this);
                        checkIn = checkIn.plusDays(1);
                    } while (checkIn.equals(checkOut)!=true);

                    habitacion.agenda.put(checkOut, this);
                    BD_reservaciones.put(habitacion, this);
                }
                break;
            }
            System.out.println("Reservacion realizada");
            
        
    }
        else{
            System.out.println("No tenemos habitaciones disponibles para ese numero de personas");
        }
       
    }

    
    /** 
     * Se pregunta al cliente que fecha quiere modificar de su estancia, si la de entrada o la de salida
     * De acuerdo a la fecha elegida, se modifica la agenda de la habitación
     * y los datos de entrada y salida del cliente
     * @param BD_reservaciones
     */
    public void modificarReservacion(final Map <Habitacion,Cliente> BD_reservaciones){
        final DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
        final Scanner sc = new Scanner(System.in);

        System.out.println("\n\t"+ this.getNombre() + ", ¿Que deseas hacer?");
        System.out.println("\n\t1. Adelantar fecha de llegada");
        System.out.println("\t2. Aplazar fecha de salida");
        System.out.println("\t3. Aplazar fecha de llegada");
        System.out.println("\t4.  Adelantar fecha de salida");

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
                this.setCheckIn(estancia);

                for ( Habitacion habitacion : BD_reservaciones.keySet()) {
                    if (habitacion.agenda.containsValue(this)){
                        do {
                            habitacion.agenda.put(estancia, this);
                            estancia = estancia.plusDays(1);
                        } while (!estancia.isEqual(this.checkIn));
                    }
                    habitacion.getAgenda();
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
                this.setCheckOut(estancia);

                for(Habitacion habitacion: BD_reservaciones.keySet()){
                    if(habitacion.agenda.containsKey(this.checkOut)){
                        while(checkOut.isEqual(estancia)!=true){
                            checkOut = checkOut.plusDays(1);
                            habitacion.agenda.put(checkOut, this); 
                        }
                        habitacion.getAgenda();
                    }
                }
                break;

            case 3: 
                System.out.println("\t==Aplazar fecha de llegada==");
                System.out.println("Tu nueva fecha de llegada: ");
                System.out.print("(dd-mm-aa)--> ");
                nueva = sc.next();
                estancia = LocalDate.parse(nueva, isoFecha);
                this.setCheckIn(estancia);
                copia = checkIn;

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
                this.setCheckOut(estancia);
                copia = checkOut;
                checkOut = estancia;
 
                for(final Habitacion habitacion: BD_reservaciones.keySet()){
                    if(habitacion.agenda.containsKey(this.checkOut)){
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
       }
       else{ 
            for (final Cliente cliente : BD_Clientes) {
                cliente.verReservacion(BD_reservaciones);
            }
        }
    }

    
    /** 
     * Se imprime la informacion de un cliente en particular
     * Tanto datos del cliente como datos de la habitacion
     * Si no tiene reservación, lo indica.
     * @param BD_reservaciones
     */
    public void verReservacion(final Map <Habitacion,Cliente> BD_reservaciones){
        for( Habitacion habitacion: BD_reservaciones.keySet()){
            if(habitacion.agenda.containsValue(this)){
                System.out.println("=====================================\nReservacion");
                System.out.println("\tNoCliente: "+this.getNoCliente());
                System.out.println(" Cliente: "+ this.getNombre());
                System.out.println("\tHabitacion: "+ habitacion.getNoHabitacion());
                System.out.println("\tNúmero de huespedes: "+ this.getNoHuespedes() );
                System.out.println("\tFecha de llegada: "+ this.getCheckIn().getDayOfMonth()+"/"
                + this.getCheckIn().getMonth()+"/"+ this.getCheckIn().getYear());
                System.out.println("\tFecha de salida: "+ this.getCheckOut().getDayOfMonth()+"/"
                + this.getCheckOut().getMonth()+"/"+ this.getCheckOut().getYear());
                System.out.println("=====================================");
            }
        else{
            System.out.println("Cliente no tiene ninguna reservación");
        }
        }
    }


    }

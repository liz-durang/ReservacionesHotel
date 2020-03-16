/**
 * Proyecto 1: Colecciones
 * Programación Orientada a Objetos
 * 2020-2
 *Grupo: 2
 Profesor: M.I Edgar Tista Garcia
 @author Callejas Sanchez Juan Antonio, Duràn Gonzàlez Lizeth, Mancilla Flores Juan Antonio
 @version 1.0
 */

import java.util.*;
import java.time.*;
import java.time.format.*;

public class Habitacion {

    static ArrayList <Habitacion> BD_habitaciones = new ArrayList<>(10);

    int noHabitacion;
    int precio;
    int cupo;
    String detalles; 

    TreeMap <LocalDate, Cliente> agenda;

    public Habitacion(final int noHabitacion, final int precio, final int cupo, final String detalles) {
        this.noHabitacion = noHabitacion;
        this.precio = precio;
        this.cupo = cupo;
        this.detalles = detalles;
        agenda = new TreeMap<>();
    }
    
     /*
    *Getters y Setters
    */

    /** 
     * @return int
     */
   
    public int getNoHabitacion() {
        return noHabitacion;
    }

    
    /** 
     * @param noHabitacion
     */
    public void setNoHabitacion( int noHabitacion) {
        this.noHabitacion = noHabitacion;
    }

    
    /** 
     * @return int
     */
    public int getPrecio() {
        return precio;
    }

    
    /** 
     * @param precio
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    
    /** 
     * @return int
     */
    public int getCupo() {
        return cupo;
    }

    
    /** 
     * @param cupo
     */
    public void setCupo( int cupo) {
        this.cupo = cupo;
    }

    
    /** 
     * @return String
     */
    public String getDetalles() {
        return detalles;
    }

    
    /** 
     * @param detalles
     */
    public void setDetalles( String detalles) {
        this.detalles = detalles;
    }

    public void getAgenda() {
         for (Map.Entry <LocalDate, Cliente> elemento: agenda.entrySet()) {
             System.out.println(elemento.getKey()+"-"+elemento.getValue().getNombre());
         }
    }

    
    /** 
     * @param fecha
     * @param cliente1
     */
    public void setAgenda( LocalDate fecha, Cliente cliente1) {
        this.agenda.put(fecha, cliente1);
    }

   /**
   *Métodos
   */
    
    @Override
    public String toString() {

        return "Habitacion " 
        + this.noHabitacion + "\n$"+this.precio + " " +this.cupo + " personas " + this.detalles+
        "\n";
    }

    /**
     * Método que carga habitaciones y las ingresa a la base de datos de habitaciones
     */
   public static void cargarHabitaciones() {
        String info = "\n*Cama KingSize *40m² *Balcón *Wifi gratis *Room Service";
        Habitacion _101 = new Habitacion(101, 3000, 1, info);
        BD_habitaciones.add (_101);
        
        info = "\n*Dos camas individuales *30m² *MiniBar *Wifi gratis *Aire Acondicionado";
        Habitacion _102 = new Habitacion(102, 3300, 2, info);
        BD_habitaciones.add (_102);
       
        info = "\n*Una cama QueenSize y dos camas individuales  *40m² *Vista al mar *Room Service *Aire Acondicionado";
        Habitacion _103 = new Habitacion(103, 4100, 3, info);
        BD_habitaciones.add (_103);

        info = "\n*Dos camas dobles *44m² *Minibar *Ubicación Preferencial *Balcón";
        Habitacion _104 = new Habitacion(104, 4500, 4, info);
        BD_habitaciones.add (_104);
        
        info = "\n*Tres camas dobles *47m² *cafetera *Wifi gratis *Room Service";
        Habitacion _105 = new Habitacion(105, 7600, 5, info);
        BD_habitaciones.add (_105);
        
        info = "\n*Tres camas dobles *70m² *Terraza privada *Kitchenette *Vista al mar";
        Habitacion _106 = new Habitacion(106, 8500, 6, info);
        BD_habitaciones.add (_106);
        /*
        info = "\n*Tres camas dobles *60m² *Terraza privada *Minibar *Vista al mar";
        Habitacion _107 = new Habitacion(107, 7500, 6, info);
        BD_habitaciones.add (_107);
       
        info = "\n*Tres camas KingSize *35m² *Bañera *Ventilador *Vista al jardín";
        Habitacion _108 = new Habitacion(108, 7700, 5, info);
        BD_habitaciones.add (_108);

        info = "\n*Dos camas KingSize *35m² *Room Service *TV pantalla plana *Balcón";
        Habitacion _109 = new Habitacion(109, 4300, 4, info);
        BD_habitaciones.add (_109);

        info = "\n*Tres camas dobles *55m² *Caja fuerte *Bañera *Vista al jardín";
        Habitacion _110 = new Habitacion(110, 6700, 3, info);
        BD_habitaciones.add (_110);

        info = "\n*Cama KingSize *35m² *Jacuzzi *Bañera *Vista al mar";
        Habitacion _111 = new Habitacion(110, 5700, 2, info);
        BD_habitaciones.add (_111);

        info = "\n*Cama individual *25m² *Wifi gratis *Tv HD *Vista al mar";
        Habitacion _112 = new Habitacion(110, 2700, 1, info);
        BD_habitaciones.add (_112);*/
   }

   
   /** 
    * Recorre la base de datos de habitaciones 
    * Imprime las habitaciones no ocupadas en cierta fecha ingresada
    * @param fecha
    */
   public static void verHabitacionesOcupadas (String fecha) {
       
        DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
        
        LocalDate date = LocalDate.parse(fecha, isoFecha);
        int i = 0;
        for (Habitacion habitacion : BD_habitaciones) {
            if (habitacion.agenda.containsKey(date)) {
                System.out.println("HABITACION OCUPADA");
                System.out.println(habitacion);
                i++;
            }
        }
        if (i<1) {
            System.out.println("\n\tNo hay habitaciones ocupadas");
        }


   }

   
   /** 
    * Recorre la base de datos habitaciones 
    * Imprimee aquellas habitaciones con cierto cupo en especifico
    * Y que no esten ocupadas en cierta fecha
    * @param fecha
    * @param noHuespedes
    */
    
   public static void verHabitacionesDisponibles(String fecha, int noHuespedes) {
       DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
        
        LocalDate date = LocalDate.parse(fecha, isoFecha);
        int i = 0;
        System.out.println("\n");
        for (Habitacion habitacion : BD_habitaciones) {
            if (!habitacion.agenda.containsKey(date) && (noHuespedes == habitacion.getCupo()) ) {
                System.out.println(habitacion);
                i++;
            }
        }
        if (i< 1) {
            System.out.println("\n\tNo hay habitaciones disponibles"); 
        }
   }
}
  

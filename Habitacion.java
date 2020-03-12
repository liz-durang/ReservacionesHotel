/**
 * Proyecto 1: Colecciones
 * Programación Orientada a Objetos
 * 2020-2
 *Grupo: 2
 Profesor: M.I Edgar Tista Garcia
 @author Callejas Sanchez Juan Antonio, Duràn Gonzàlez Lizethm Juan Antonio
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
    public int getNoHabitacion() {
        return noHabitacion;
    }

    public void setNoHabitacion( int noHabitacion) {
        this.noHabitacion = noHabitacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo( int cupo) {
        this.cupo = cupo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles( String detalles) {
        this.detalles = detalles;
    }

    public void getAgenda() {
         for (Map.Entry <LocalDate, Cliente> elemento: this.agenda.entrySet()) {
             System.out.println(elemento.getKey()+"-"+elemento.getValue().getNombre());
         };
    }

    public void setAgenda( LocalDate fecha, Cliente cliente1) {
        this.agenda.put(fecha, cliente1);
    }

   /**
   *Métodos
   */
    
    @Override
    public String toString() {

        return "=====================================\nHabitacion " 
        + this.noHabitacion + "\n$"+this.precio + " " +this.cupo + " personas " + this.detalles+
        "\n=====================================";
    }

   public static void cargarHabitaciones() {
        String info = "\n*Cama KingSize *40m² *Balcón *Wifi gratis *Room Service";
        Habitacion _101 = new Habitacion(101, 3700, 2, info);
        BD_habitaciones.add (_101);
        
        info = "\n*Dos camas dobles *30m² *MiniBar *Wifi gratis *Aire Acondicionado";
        Habitacion _102 = new Habitacion(102, 3500, 4, info);
        BD_habitaciones.add (_102);
       
        info = "\n*Cama QueenSize *40m² *Vista al mar *Room Service *Aire Acondicionado";
        Habitacion _103 = new Habitacion(103, 4100, 2, info);
        BD_habitaciones.add (_103);

        info = "\n*Dos camas dobles *44m² *Minibar *Ubicación Preferencial *Balcón";
        Habitacion _104 = new Habitacion(104, 4500, 4, info);
        BD_habitaciones.add (_104);
        
        info = "\n*Dos camas dobles *47m² *cafetera *Wifi gratis *Room Service";
        Habitacion _105 = new Habitacion(105, 6600, 4, info);
        BD_habitaciones.add (_105);
        
        info = "\n*Tres camas dobles *70m² *Terraza privada *Kitchenette *Vista al mar";
        Habitacion _106 = new Habitacion(106, 8500, 6, info);
        BD_habitaciones.add (_106);
        
        info = "\n*Tres camas dobles *60m² *Terraza privada *Minibar *Vista al mar";
        Habitacion _107 = new Habitacion(107, 7500, 6, info);
        BD_habitaciones.add (_107);
       
        info = "\n*Cama doble *35m² *Bañera *Ventilador *Vista al jardín";
        Habitacion _108 = new Habitacion(108, 4500, 2, info);
        BD_habitaciones.add (_108);

        info = "\n*Cama KingSize *35m² *Room Service *TV pantalla plana *Balcón";
        Habitacion _109 = new Habitacion(109, 4300, 2, info);
        BD_habitaciones.add (_109);

        info = "\n*Tres camas doble *55m² *Caja fuerte *Bañera *Vista al jardín";
        Habitacion _110 = new Habitacion(110, 6700, 6, info);
        BD_habitaciones.add (_110);
   }

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

   public static void verHabitacionesDisponibles(String fecha) {
       DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
        
        LocalDate date = LocalDate.parse(fecha, isoFecha);
        int i = 0;
        System.out.println("\n");
        for (Habitacion habitacion : BD_habitaciones) {
            if (!habitacion.agenda.containsKey(date)) {
                System.out.println("HABITACION DISPONIBLE");
                System.out.println(habitacion);
                i++;
            }
        }
        if (i< 1) {
            System.out.println("\n\tNo hay habitaciones disponibles"); 
        }


   }


   public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    DateTimeFormatter isoFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
        
    Habitacion.cargarHabitaciones();

    Cliente cl1 = new Cliente ("Liz", "liz@");
    Cliente cl2 = new Cliente ("Ciego", "diego@");
    Cliente cl3 = new Cliente ("Car", "car@");



    for (int i = 0; i < 1; i++) {
        
        System.out.print("Fecha: ");
        String fecha = sc.next();
        LocalDate date = LocalDate.parse(fecha, isoFecha);

        Habitacion.BD_habitaciones.get(0).setAgenda(date, cl1);
        Habitacion.BD_habitaciones.get(1).setAgenda(date, cl2);
        Habitacion.BD_habitaciones.get(2).setAgenda(date, cl3);

        LocalDate date1= date.plusDays(2);
        System.out.println(date.plusDays(2));
        Habitacion.BD_habitaciones.get(3).setAgenda(date1, cl1);
        System.out.println("ver");
        Habitacion.verHabitacionesOcupadas(fecha);
        Habitacion.verHabitacionesDisponibles(fecha);

    }
    
  
    
    

    //Habitacion.verHabitacionesOcupadas(fecha);




    

   }
  

}

  

    


/**
 * Proyecto 1: Colecciones
 * Programación Orientada a Objetos
 * 2020-2
 *Grupo: 2
 Profesor: M.I Edgar Tista Garcia
 @author Callejas Sanchez Juan Antonio, Duràn Gonzàlez Lizethm Juan Antonio
 @version 1.0
 */

import java.time.*;
import java.time.format.*;

public class Cliente{

    static int noCliente = 0; 

    int id;
    String nombre; 
    String email; 
    int noHuespedes;

    LocalDate checkIn;
    LocalDate checkOut; 

    public Cliente (String name, String email){
        noCliente ++;
        this.nombre = name; 
        this.email = email; 
        this.id = noCliente; 
    }

    
    /*
    *Getters y Setters
    */

    public static int getNoCliente() {
        return noCliente;
    }

    public static void setNoCliente(int noCliente) {
        Cliente.noCliente = noCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNoHuespedes() {
        return noHuespedes;
    }

    public void setNoHuespedes(int noHuespedes) {
        this.noHuespedes = noHuespedes;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    /*
    * Métodos
    */

    public void registrarCliente (){
        
    }

    public void verClientes(){

    }

    public void verReservacion(){

    }

    public void hacerReservacion (){

    }

    public void modificarReservacion(){

    }

    


}
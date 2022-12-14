/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class Alumno  implements Serializable  {
    private String nombre;
    private String Apellido;
    private LocalDate FechaNac;
    private String DNI;
    private String Direccion;
    private String CodigoPostal;
    private int Telefono;
    private String Email;
    private LocalDate Fecha_Alta;
    
    private ArrayList<Asignatura> asigDeAlum = new ArrayList();


    public Alumno() {
    }

    public Alumno(String nombre, String Apellido, LocalDate FechaNac, String DNI, String Direccion, String CodigoPostal, int Telefono, String Email, LocalDate Fecha_Alta) {
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.FechaNac = FechaNac;
        this.DNI = DNI;
        this.Direccion = Direccion;
        this.CodigoPostal = CodigoPostal;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Fecha_Alta = Fecha_Alta;
        
    }

    public String getNombre() {
        return this.nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public LocalDate getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(LocalDate FechaNac) {
        this.FechaNac = FechaNac;
    }

    public String getDNI() {
        return this.DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public LocalDate getFecha_Alta() {
        return Fecha_Alta;
    }

    public void setFecha_Alta(LocalDate Fecha_Alta) {
        this.Fecha_Alta = Fecha_Alta;
    }

    public static XStream streamConverter(){
        XStream xStream = new XStream(new DomDriver());
        xStream.registerConverter(new AlumnoConverter());
        xStream.alias("Alumno", Alumno.class);
        xStream.allowTypes(new Class[] {Alumno.class});
        return xStream;
    }

    
}

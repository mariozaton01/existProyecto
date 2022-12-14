/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class Asignatura  implements Serializable   {
    private String Nombre;
    private int ID;
    private ArrayList<Alumno> AlumnosPorAsig= new ArrayList();

    public Asignatura() {
    }

    public Asignatura(String Nombre, int ID) {
        this.Nombre = Nombre;
        this.ID = ID;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<Alumno> getAlumnosPorAsig() {
        return this.AlumnosPorAsig;
    }

    public void setAlumnosPorAsig(ArrayList<Alumno> AlumnosPorAsig) {
        this.AlumnosPorAsig = AlumnosPorAsig;
    }

    public void setAlumno(Alumno alum){
        this.AlumnosPorAsig.add(alum);
    }

    public static XStream streamConverter(){
        XStream xStream = new XStream(new DomDriver());
        xStream.registerConverter(new AsignaturaConverter());
        xStream.alias("Asignatura", Asignatura.class);
        xStream.allowTypes(new Class[] {Alumno.class});
        return xStream;
    }
    
    
}

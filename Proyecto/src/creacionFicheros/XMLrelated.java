package creacionFicheros;
import Clases.Alumno;
import Clases.Asignatura;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class
XMLrelated {

    static File xmlFile = new File("src/Ficheros/ListaAsignaturas.xml");

    public static void main(String[] args) {

    }
    public static void createXML() throws IOException {
        XStream stream = new XStream();
        stream.alias("listaAsignaturas", Asignatura.class);
        stream.aliasField("ListaAlumnos", Asignatura.class, "AlumnosPorAsig");
        stream.aliasField("FechaAlta", Asignatura.class, "Fecha_Alta");
        stream.alias("Alumno", Alumno.class);

        //asignaturas file
        File fileAsig = new File("src/Ficheros/Asignaturas.dat");
        ArrayList<Asignatura> listaAsig = AsignaturasRelated.readFile(fileAsig);

        stream.toXML(listaAsig, new FileOutputStream(xmlFile));

    }

    public static ArrayList<Asignatura> leerXML(File xmlFile){
        Class<?>[] classes = new Class[] { Asignatura.class, Alumno.class };

        XStream stream = new XStream();
        stream.addPermission(AnyTypePermission.ANY);
        stream.allowTypes(classes);

        stream.alias("listaAsignaturas", Asignatura.class);
        stream.aliasField("ListaAlumnos", Asignatura.class, "AlumnosPorAsig");
        stream.aliasField("FechaAlta", Asignatura.class, "Fecha_Alta");
        stream.alias("Alumno", Alumno.class);


        ArrayList<Asignatura> listaAsig = (ArrayList<Asignatura>) stream.fromXML(XMLrelated.xmlFile);
        return listaAsig;
    }

    public static ArrayList<Asignatura> verAsigDeAlumno(int index) throws IOException {
        Alumno alum = AlumnosRelated.getAlumno(index);
        String dni = alum.getDNI();

        ArrayList<Asignatura> listaAsig = leerXML(xmlFile);
        ArrayList<Asignatura> asignaturasAMostrar = new ArrayList<>();

        for (Asignatura asig: listaAsig) {
            for (Alumno alumno: asig.getAlumnosPorAsig() ) {
                if (alumno.getDNI().equals(dni)){
                    asignaturasAMostrar.add(asig);
                    break;
                }
            }
        }
        return asignaturasAMostrar;
    }
    public static void setearTextoAsignaturas(JTextArea textarea, ArrayList<Asignatura> asigsDeAlum) {
        String texto="";
        String asignatura;
        for (Asignatura asig: asigsDeAlum) {
            asignatura= asig.getNombre();
            texto=asignatura +"\n"+texto;
        }

        textarea.setText(texto);

    }
    public static void setearTextoAlumnos(JTextArea textarea, ArrayList<Alumno> asigsDeAlum) {
        String texto="";
        String alumno;
        for (Alumno alum: asigsDeAlum) {
            alumno= alum.getNombre();
            texto= alumno +"\n"+texto;
        }

        textarea.setText(texto);

    }

    public static void getListaAlum(String nombreAsig, JTextArea textArea) {
        ArrayList<Asignatura> listaAsig = leerXML(xmlFile);
        int posicion = 0;
        for (Asignatura asig: listaAsig ) {
            if(asig.getNombre().equals(nombreAsig)){
                break;
            }
            posicion ++;
        }
        ArrayList<Alumno> listaAlumnos;
        listaAlumnos = listaAsig.get(posicion).getAlumnosPorAsig();

        setearTextoAlumnos(textArea,listaAlumnos);
    }
}

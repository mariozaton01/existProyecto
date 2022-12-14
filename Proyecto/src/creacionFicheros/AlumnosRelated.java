package creacionFicheros;

import Clases.*;
import com.sun.jdi.ObjectReference;

import javax.swing.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;

public class AlumnosRelated {

    static File file = new File("src/Ficheros/Alumnos.dat");

    public static void main() throws Exception {
        
        if(file.exists()){
            // en caso de que el fichero exista hay que leer lo que ya hay en el fichero para no sobreescribirlo
            ArrayList<Alumno> listaAlum;

            listaAlum = readFile(file);


            writeFile( listaAlum);
        }
        else {
            ArrayList<Alumno> listaAlum = createAlums();

            writeFile(listaAlum);
        }

    }

    public static String getDNI(int index) throws IOException {
        ArrayList<Alumno> listaAlum;

        listaAlum = readFile(file);
        return listaAlum.get(index).getDNI();
    }

    public static void borrarPorIndex(int index) throws IOException {
        ArrayList<Alumno> listaAlum;

        listaAlum = readFile(file);
        listaAlum.remove(index);
        //escribimos de nuevo el fichero
        writeFile(listaAlum);
    }

    public static boolean comprobarDNIExistente(String dni) throws IOException {
        ArrayList<Alumno> listaAlum ;

        listaAlum = readFile(file);

        for (Alumno alumno: listaAlum ) {
            if (alumno.getDNI().equals(dni)){
                return false;
            }
        }
        return true;
    }

    public static void insertar(Alumno alum) throws Exception{

        // insertar el nuevo alumno en el fichero dat
        ArrayList<Alumno> listaAlum ;

        listaAlum = readFile(file);

        listaAlum.add(alum);
        AsignaturasRelated.addAlumno(alum);

        writeFile(listaAlum);

    }

    public static void borrar(String dni) throws Exception{
        ArrayList<Alumno> listaAlum;


        listaAlum = readFile(file);

        int posicion = 0;
                // recorremos el array de alumnos para conseguir el id de alumno que contenga el dni introducido
            for (Alumno alumno: listaAlum ) {
                if (alumno.getDNI().equals(dni)){
                    break;
                }
                posicion ++;
            }
            // borramos el alumno que contenga el id obtenido anteriormente. Lo hacemos mediante posiciones del array.
            listaAlum.remove(posicion);
            //escribimos de nuevo el fichero
            writeFile(listaAlum);

    }
    public static void listadoAlumnosComboBox(JComboBox comboBox) throws IOException {
        //Leemos el fichero de alumnos.
        ArrayList<Alumno> listaAlum =  readFile(file);
        //Agregamos al comboBox todos los alumnos.
        for (Alumno alum: listaAlum) {
            String nombre= alum.getNombre() +" "+alum.getApellido();
            comboBox.addItem(nombre);
        }
    }

    public static ArrayList<Alumno> createAlums(){

        Alumno a1 = new Alumno("Mario","Zaton", LocalDate.of(2001, 8, 2), "72756384R", "Senda valentin de foronda", "01010", 661322115,"mario.zaton@ikasle.egibide.org", LocalDate.of(2022, 6, 22));
        Alumno a2 = new Alumno("Juan","Da Silva", LocalDate.of(2001, 5, 22), "72796384T", "Calle Bapo", "01013", 661435677,"juan.dasilva@ikasle.egibide.org", LocalDate.of(2022, 6, 18));
        Alumno a3 = new Alumno("Alejandro","Perez", LocalDate.of(1999, 3, 12), "72726384L", "La GLOW", "01012", 661678543,"alejandro.perez@ikasle.egibide.org", LocalDate.of(2022, 6, 8));
        Alumno a4 = new Alumno("Ivan","Villagra", LocalDate.of(1997, 4, 25), "92756384C", "El Bule", "01015", 66121223,"ivan.villagra@ikasle.egibide.org", LocalDate.of(2022, 6, 12));
        Alumno a5 = new Alumno("Guillermo","Jabato", LocalDate.of(2000, 7, 06), "74756377F", "Cerca de Juan", "01013", 66189855,"guillermo.jabato@ikasle.egibide.org", LocalDate.of(2022, 6, 12));

        ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
        listaAlumnos.add(a1);
        listaAlumnos.add(a2);
        listaAlumnos.add(a3);
        listaAlumnos.add(a4);
        listaAlumnos.add(a5);

        return listaAlumnos;

    }
    public  static ArrayList<Alumno> readFile(File fich) throws IOException {
        ArrayList<Alumno> listaAlum = new ArrayList<>();

        FileInputStream fInput = new FileInputStream(fich);
        ObjectInputStream objInput = new ObjectInputStream(fInput);

        boolean cont = true;
        while (cont){
            try{
                Alumno alum = (Alumno) objInput.readObject();

                if(alum != null){
                    listaAlum.add(alum);
                }
                else {
                }
            }
            catch ( Exception e){
                if (e.getMessage() == null) {
                    cont = false;
                }
            }
        }
        return listaAlum;

    }

    public  static void writeFile( ArrayList<Alumno> listaAlum) throws IOException {

        FileOutputStream fOutput = new FileOutputStream(file);
        ObjectOutputStream objOutput = new ObjectOutputStream(fOutput);
        for (Alumno alumno: listaAlum ) {
            objOutput.writeObject(alumno);
        }
    }

    public static String verListaAlumnos() throws IOException {
        String texto ="";
        ArrayList<Alumno> listaAlum = new ArrayList<>();
        listaAlum = readFile(file);

        for (Alumno alum:listaAlum ) {
            String nombre= "- " +alum.getNombre() +" "+alum.getApellido() + "--" + alum.getDNI();
            texto= texto+"\n"+ nombre;
        }
        return texto;

    }

    public static Alumno getAlumno(int index) throws IOException {
        ArrayList<Alumno> listaAlum = readFile(file);
        return listaAlum.get(index);
    }
}



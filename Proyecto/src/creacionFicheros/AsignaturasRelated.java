package creacionFicheros;

import Clases.Alumno;
import Clases.Asignatura;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class AsignaturasRelated {

    static File fich = new File("src/Ficheros/Asignaturas.dat");

    public static void main() throws IOException {

        if (!fich.exists()){

            ArrayList<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();
            String[] asigStrings = {"Programacion", "Acceso a datos","EIE"};

            File alumFile = new File("src/Ficheros/Alumnos.dat");

            ArrayList<Alumno> listaAlum = AlumnosRelated.readFile(alumFile);

            int id = 1;
            for (String nombre: asigStrings) {

                Asignatura asig = new Asignatura(nombre,id);
                asig.setAlumnosPorAsig(listaAlum);
                listaAsignaturas.add(asig);
                id ++;
            }

            writeFile(listaAsignaturas);
        }



    }

    public  static ArrayList<Asignatura> readFile(File fich) throws IOException {
        ArrayList<Asignatura> listaAsig = new ArrayList<>();

        FileInputStream fInput = new FileInputStream(fich);
        ObjectInputStream objInput = new ObjectInputStream(fInput);

        boolean cont = true;
        while (cont){
            try{
                Asignatura asig = (Asignatura) objInput.readObject();

                if(asig != null){
                    listaAsig.add(asig);
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
        return listaAsig;

    }
    public  static void writeFile( ArrayList<Asignatura> listaAsig) throws IOException {

        FileOutputStream fOutput = new FileOutputStream(fich);
        ObjectOutputStream objOutput = new ObjectOutputStream(fOutput);
        for (Asignatura asig: listaAsig ) {
            objOutput.writeObject(asig);
        }

    }

    public static ArrayList<Asignatura> verAsigDeAlumno(int index) throws IOException {
        Alumno alum = AlumnosRelated.getAlumno(index);
        String dni = alum.getDNI();

        ArrayList<Asignatura> listaAsig = readFile(fich);
        ArrayList<Asignatura> asignaturasAMostrar = new ArrayList<>();

        for (Asignatura asig: listaAsig) {
            int posicion = 0;
            for (Alumno alumno: asig.getAlumnosPorAsig() ) {
                if (alumno.getDNI().equals(dni)){
                    asignaturasAMostrar.add(asig);
                    break;
                }
                posicion ++;
            }
        }
        return asignaturasAMostrar;
    }

    public static void AsignaturasToComboBox(ArrayList<Asignatura> asignaturas, JComboBox<String> comboBox) {
        for (Asignatura asig: asignaturas) {
            comboBox.addItem(asig.getNombre());
        }

    }

    public static boolean deleteAsigFromAlumno(String dni, int index) throws IOException {
        /*Hago el borrado de la asignatura cambiando el array de alumnos por uno nuevo donde no esté el alumno en cuestión ya que
        * si hago un .remove del array se me borra ese alumno en todos los arrays de alumno de las distintas asignaturas auqnue
        * lo borre solo de una asignatura*/
        ArrayList<Asignatura> listaAsig = readFile(fich);
        Asignatura asig = listaAsig.get(index);

        ArrayList<Alumno>listaAlum =  asig.getAlumnosPorAsig();
        ArrayList<Alumno> newListaAlum = new ArrayList<>();

        boolean borrado = false;
        for (Alumno alum: listaAlum) {
            if(alum.getDNI().equals(dni)){

                borrado = true;
            }
            else {
                newListaAlum.add(alum);
            }

        }
        if (borrado){
            listaAsig.get(index).setAlumnosPorAsig(newListaAlum);

            writeFile(listaAsig);

            return true;
        }
        else {
            return false;
        }
    }
    public static void addAlumno(Alumno alum) throws IOException {
        ArrayList<Asignatura> listaAsig = readFile(fich);
        listaAsig.get(0).getAlumnosPorAsig().add(alum);
        writeFile(listaAsig);

    }

    public static void borrarAlumno(String dni) throws IOException {
        ArrayList<Asignatura> listaAsig = readFile(fich);

        for (Asignatura asig: listaAsig ) {
            ArrayList<Alumno>listaAlum = asig.getAlumnosPorAsig();
            Alumno alumBorrar = null;
            for (Alumno alum: listaAlum ) {
                if (alum.getDNI().equals(dni)){
                    alumBorrar = alum;
                }
            }
            asig.getAlumnosPorAsig().remove(alumBorrar);

        }

        writeFile(listaAsig);

    }
}

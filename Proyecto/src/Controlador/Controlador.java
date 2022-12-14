package Controlador;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Clases.Alumno;
import Excepciones.formatoIncorrecto;
import Exist.ExistDb;
import Ventanas.*;
import creacionFicheros.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
 *
 * @author mario
 */
public class Controlador {

    //Ventana inicial
     private static VentanaInicio vInicio;
     //Ventana en la que mostramos que hacer a continuacion: añadir/retirar alumno del curso o ver los alumnos.
     private static VentanaElegirAñadirRetirar vElegirAñadirRetirar;
     //Ventana en la que añadimos o borramos alumnos al curso. Se usa la misma ventana para las 2 opciones.
     private static VentanaAñadirRetirarAlum vAñadirRetirarAlum;
     //ventana en la que mostramos las opciones despues de seleccionar "Ver Alumnos"
     private static VentanaAlumnos vAlumnos;
     //ventana en la que un alumno renuncia a una asignatura.
    private static VentanaRenunciarAsig vRenunciarAsig;
    //Ventana en la que se muestran los alumnos matriculados en una asignatura.
    private static VentanaAlumnoPorAsignatura vAlumPorAsig;
    //Ventana en la que se muestran las asignaturas de un alumno.
     private static VentanaAsignaturasaDeAlumno vAsigDeAlum;
     //
     private static javax.swing.JFrame vAnterior;
     //Para guardar fecha_alta
     private static  LocalDate fecha;
     //regexp con el que controlamos el formato introducido.
     private static Pattern DATE_PATTERN = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
     //Relacionado con la base de datos

    public static void main(String[] args) throws Exception {
        //ejecutamos los ficheros .dat
        ExistDb.cargarInicial();
        //AlumnosRelated.main();
        //AsignaturasRelated.main();

        //declaramos la ventana inicial
        vInicio = new VentanaInicio();

        // Hacemos visible la ventana inicial.
        vInicio.setVisible(true);
        vInicio.setLocationRelativeTo(null);
    }

    //Metodos de la ventana vInicio:

        public static void bAnadirRetirar() {
        //Mostramos la ventana en la que hay las siguientes opciones: 1.-Añadir un alumno   2.- Retirar un alumno del curso 3.- Retirar al alumno de una asignatura
            vInicio.setVisible(false);

            vElegirAñadirRetirar= new VentanaElegirAñadirRetirar();
            vElegirAñadirRetirar.setVisible(true);
            vElegirAñadirRetirar.setLocationRelativeTo(null);

            vAnterior= vInicio;

    }

    public static void bVerAlumnos() {
        //ventana en la que se nos permite visualizar la informacion de los alumnos.

        vInicio.setVisible(false);

        vAlumnos = new VentanaAlumnos();
        vAlumnos.setVisible(true);
        vAlumnos.setLocationRelativeTo(null);

        vAnterior= vInicio;

    }


    //Metodos de la ventana vElegirAñadirRetirar

    public static void bAnadir( ) {
        //Funcion atribuida al botón de añadir alumnos.
        try{
            //Nos pedirá la fecha con el dia que se hará el alta del alumno.
            LocalDate fechaInicioAltas;
            String fechaString= JOptionPane.showInputDialog("Introduce la fecha en la que añades al alumno.\nSigue el siguiente formato: yyyy-mm-dd");
            if(!DATE_PATTERN.matcher(fechaString).matches()){
                throw new Excepciones.formatoIncorrecto();
            }
                fecha= LocalDate.parse(fechaString);
                LocalDate fechaLimite=LocalDate.of(2022, 10, 15) ;
                fechaInicioAltas= LocalDate.of(2022, 07, 01);

                //En caso de que la fecha sea anterior a la fecha que da comienzo a las altas, lanzaremos una excepción en la que informaremos
                // que la fecha indicada es incorrecta.

                if(fecha.isBefore(fechaInicioAltas)){
                   throw new Excepciones.fechaLimite();
                }
                else{
                    //En caso de que la fecha sea anterior a la fecha limite de las altas, procederemos a mostrar la ventana en la que se insertarán
                    // los datos del nuevo alumno.
                     if(fecha.isBefore(fechaLimite)){
                        vElegirAñadirRetirar.setVisible(false);

                        vAñadirRetirarAlum = new VentanaAñadirRetirarAlum();
                        vAñadirRetirarAlum.setVisible(true);
                        vAñadirRetirarAlum.setLocationRelativeTo(null);
                        vAñadirRetirarAlum.añadir();

                        vAnterior=vElegirAñadirRetirar;
                    }
                    else{
                        //En caso de que exceda la fecha limite, lanzamos una excepción.
                        throw new Excepciones.fechaPosterior();
                    }
                }
        }
        catch (Excepciones.fechaLimite e){
            JOptionPane.showMessageDialog(null, "Todavia no ha iniciado el periodo de matriculacion");
        }
        catch (Excepciones.fechaPosterior e){
            JOptionPane.showMessageDialog(null, "Ha pasado el tiempo limite para iniciar en el curso");
        } catch (formatoIncorrecto e) {
            JOptionPane.showMessageDialog(null, "El formato introducido no es correcto");

        }

    }

    public static void bRetirar() {
        //funcion atribuida al boton de retirar alumno en el que se retirará al alumno del curso.
        vElegirAñadirRetirar.setVisible(false);

        vAñadirRetirarAlum = new VentanaAñadirRetirarAlum();
        vAñadirRetirarAlum.setVisible(true);
        vAñadirRetirarAlum.setLocationRelativeTo(null);
        vAñadirRetirarAlum.retirar();

        vAnterior=vElegirAñadirRetirar;

    }

    public static void bRenunciar() {
        //funcion atribuida al boton de renunciar asignaturas en el que podremos quitar un alumno de la asignatura

        vElegirAñadirRetirar.setVisible(false);

        vRenunciarAsig = new VentanaRenunciarAsig();
        vRenunciarAsig.setVisible(true);
        vRenunciarAsig.setLocationRelativeTo(null);

        vAnterior=vElegirAñadirRetirar;
    }



    //Metodos de la ventana vAlumnos
    public static void bAlumPorAsig() {
        //Muestra los alumnos pertenecientes a una asignatura
        vAlumnos.setVisible(false);

        vAlumPorAsig = new VentanaAlumnoPorAsignatura();
        vAlumPorAsig.setVisible(true);
        vAlumPorAsig.setLocationRelativeTo(null);

        vAnterior=vAlumnos;
    }

    public static void bAsigDeAlum() {
        //Muestra las asignaturas de un alumno
        vAlumnos.setVisible(false);

        vAsigDeAlum = new VentanaAsignaturasaDeAlumno();
        vAsigDeAlum.setVisible(true);
        vAsigDeAlum.setLocationRelativeTo(null);

        vAnterior=vAlumnos;
    }

    //funcionalidad aplicada al boton de cancelar en la que cargamos la ventana anterior.
    //Cada vez que llamamos a una nueva ventana, guardamos en una variable la ventana anterior.
    public static void bCancelar(javax.swing.JFrame v) {
        //v = ventana actual
        if(v.isVisible()){
            v.setVisible(false);
            vAnterior.setVisible(true);
        }
        if (v==vElegirAñadirRetirar){
            vAnterior=vInicio;
            v.setVisible(false);
            vAnterior.setVisible(true);
        }
        if(v==vAlumnos){
            vAnterior=vInicio;
            v.setVisible(false);
            vAnterior.setVisible(true);
        }

    }

    //funcion que recoge la fecha de alta al insertar a un alumno

    public static LocalDate conseguirFecha() {
        return fecha;
    }

    public static void volverInicio(JFrame v) {
        v.dispose();
        vInicio.setVisible(true);
    }

    public static void listadoAlumnosComboBox(JComboBox<String> comboBox) {
        ArrayList<Alumno> listaAlum = ExistDb.getListaAlumnos();
        //Agregamos al comboBox todos los alumnos.
        for (Alumno alum: listaAlum) {
            String nombre= alum.getNombre() +" "+alum.getApellido();
            comboBox.addItem(nombre);
        }

    }

    public static void insertarAlumno(Alumno alum) {
        ExistDb.insertarAlumno(alum);
    }

    public static String getDNI(int index) {
        ArrayList<Alumno> listaAlumno = ExistDb.getListaAlumnos();
        String dni = listaAlumno.get(index).getDNI();
        return dni;
    }

    public static void deleteAlumByDni(String dni) {
        ExistDb.deleteAlumnoByDNI(dni);
    }

    public static boolean comprobarDNIExistente(String dni) {
        ArrayList<Alumno> listaAlum = ExistDb.getListaAlumnos();

        for (Alumno alumno: listaAlum ) {
            if (alumno.getDNI().equals(dni)){
                return false;
            }
        }
        return true;
    }

    public static Alumno getAlumno(int index) {
        ArrayList<Alumno> listaAlum = ExistDb.getListaAlumnos();
        Alumno alum = listaAlum.get(index);

        return  alum;

    }

    public static boolean deleteAsigFromAlumno(String dni, int idAsignatura) {
        try{
            ExistDb.retirarAsigDeAlumno(dni,idAsignatura);
            return  true;
        }
        catch (Exception e){
            return false;
        }


    }
}



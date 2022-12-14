package Exist;
import Clases.Alumno;
import Clases.Asignatura;
import Clases.AsignaturaConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import javax.xml.transform.OutputKeys;
import org.exist.xmldb.EXistResource;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import com.thoughtworks.xstream.XStream;

public class ExistDb {

    static Scanner teclado = new Scanner(System.in);
    static String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
    static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/proyecto"; //URI colección
    static String usu = "admin"; //Usuario
    static String usuPwd = "12345Abcde"; //Clave
    //static String usuPwd = ""; //Clave casa
    static Collection col = null;

    public static Collection con() {

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.getDeclaredConstructor().newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            return col;
        } catch (XMLDBException e) {
            System.out.println("Error al inicializar la BD eXist.");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el driver.");
            //e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Error al instanciar la BD.");
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Error al instanciar la BD.");
            //e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void cargarInicial() throws XMLDBException  {
        //Devuelve true si el dep existe
        if (con() != null) {
            try {
                System.out.printf("Conecta");
                // Inicializamos el recurso
                XMLResource res = null;

                // Creamos el recurso -> recibe 2 parámetros tipo String:
                // s: nombre.xml (si lo dejamos null, pondrá un nombre aleatorio)
                // s1: tipo recurso (en este caso, siempre será XMLResource)
                res = (XMLResource) col.createResource("Alumnos.xml", "XMLResource");

                if(col.getResource("Alumnos.xml") == null){
                    File f = new File("src\\creacionFicheros\\plantillaAlumnos.xml");

                    // Fijamos como contenido ese archivo .xml elegido
                    res.setContent(f);
                    col.storeResource(res); // lo añadimos a la colección

                    // Listamos la colección para ver que en efecto se ha añadido
                    for (String colRe: col.listResources())
                        System.out.println(colRe);
                }
                res = (XMLResource)col.createResource("ListaAsignaturas.xml", "XMLResource");

                if(col.getResource("ListaAsignaturas.xml") == null){
                    File f = new File("src\\creacionFicheros\\plantillaAsignaturas.xml");

                    // Fijamos como contenido ese archivo .xml elegido
                    res.setContent(f);
                    col.storeResource(res); // lo añadimos a la colección

                    // Listamos la colección para ver que en efecto se ha añadido
                    for (String colRe: col.listResources())
                        System.out.println(colRe);
                    // insertamos las asignaturas predeterminadas en la coleccion
                    insertPredefinedAsignaturas();
                }
                // Elegimos el fichero .xml que queremos añadir a la colección


                col.close();
            } catch (Exception e) {
                System.out.println("Error al consultar." + e.getMessage());
                // e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }

    private static void insertPredefinedAsignaturas() {

        if (con() != null) {

            try {
                ArrayList<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();
                String[] asigStrings = {"Programacion", "Acceso a datos","EIE"};

                //creamos Asignaturas a partir del arrayList de strings
                int id = 1;
                for (String nombre: asigStrings) {

                    Asignatura asig = new Asignatura(nombre,id);
                    listaAsignaturas.add(asig);
                    id ++;
                }
                XStream stream = Asignatura.streamConverter();
                //recorremos la lista de asignaturas
                for (Asignatura asig : listaAsignaturas){


                    //insertamos las asignaturas
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para insertar --> update insert ... into
                    servicio.query("update insert " + stream.toXML(asig) + " into /Asignaturas");
                }

                col.close(); //borramos
            } catch (Exception e) {
                System.out.println("Error al insertar empleado.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }

    public static void insertarAlumno(Alumno alum) {

        //Caso concreto: sabemos cuáles son los nodos
        /*String nuevoAlumno = "<Alumno><DNI>" + alum.getDNI() + "</DNI>"
                + "<Nombre>" + alum.getNombre() + "</Nombre><Apellido>" + alum.getApellido() + "</Apellido><Fecha_nac>" + alum.getFechaNac() + "</Fecha_nac>"
                + "<Direccion>" + alum.getDireccion() + "</Direccion>"+ "<CP>" + alum.getCodigoPostal() + "</CP>"
                + "<Telefono>" + alum.getTelefono() + "</Telefono>"+"<Email>" + alum.getEmail() + "</Email>"
                +"<Fecha_alta>" + alum.getFecha_Alta() + "</Fecha_alta>"+"</Alumno>";*/

        if (con() != null) {
            try {
                XStream stream = Alumno.streamConverter();
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para insertar --> update insert ... into
                ResourceSet result = servicio.query("update insert " + stream.toXML(alum) + " into /Alumnos");
                servicio.query("update insert"+ stream.toXML(alum) +"into /Asignaturas/Asignatura/ListaAlumnos");
                col.close(); //borramos
            } catch (Exception e) {
                System.out.println("Error al insertar alumno.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }

    public static ArrayList<Alumno> getListaAlumnos() {
        ArrayList<Alumno> listaAlum = new ArrayList<Alumno>();

        if (con() != null) {
            try {
                XStream stream = Alumno.streamConverter();

                XPathQueryService servicio;
                servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Preparamos la consulta
                ResourceSet result = servicio.query("/Alumnos/Alumno");
                // recorrer los datos del recurso.
                ResourceIterator i;
                i = result.getIterator();
                if (i.hasMoreResources()) {
                    while (i.hasMoreResources()) {
                        Resource r = i.nextResource();
                        listaAlum.add((Alumno) stream.fromXML((String) r.getContent()) );
                    }
                }

                col.close();
            } catch (XMLDBException e) {
                System.out.println(" ERROR"+ e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
        return listaAlum;
    }

    public static void deleteAlumnoByDNI(String dni) {
            if (con() != null) {
                try {
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para borrar un departamento --> update delete
                    servicio.query("update delete /Alumnos/Alumno[DNI='" + dni + "']");//todo revisar fallo delete
                    servicio.query( "update delete /Asignaturas/Asignatura/ListaAlumnos/Alumno[DNI='" + dni + "']");
                    col.close();
                } catch (Exception e) {
                    System.out.println("Error al borrar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
    }

    public static void retirarAsigDeAlumno(String dni, int idAsignatura) {
        if (con() != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para borrar un departamento --> update delete
                ResourceSet result = servicio.query("update delete /Alumnos/Alumno[DNI='" + dni + "']");
                servicio.query( "update delete /Asignaturas/Asignatura[ID = "+idAsignatura +"]/ListaAlumnos/Alumno[DNI= '" + dni + "']");
                col.close();
            } catch (Exception e) {
                System.out.println("Error al borrar.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }

    public static ArrayList<Asignatura> getAsignaturasDeAlumno(String dni) {
        ArrayList<Asignatura> listaAsig = new ArrayList<Asignatura>();

        if (con() != null) {
            try {

                XPathQueryService servicio;
                servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Preparamos la consulta
                ResourceSet result = servicio.query("/Asignaturas/Asignatura[ListaAlumnos/Alumno/DNI ='"+dni+"']");

                // recorrer los datos del recurso.
                ResourceIterator i;

                i = result.getIterator();
                if (i.hasMoreResources()) {
                    while (i.hasMoreResources()) {
                        XStream stream = Asignatura.streamConverter();
                        Resource r = i.nextResource();

                        listaAsig.add((Asignatura) stream.fromXML((String) r.getContent()) );
                        System.out.println("ooo" + listaAsig.get(0));

                    }
                }

                col.close();
                return listaAsig;

            } catch (XMLDBException e) {
                System.out.println(" ERROR"+ e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
        return listaAsig;
    }
}




/*

<Asignatura>
<ID>1</ID>
<Nombre>Progamacion</Nombre>
<listaAlumnos></listaAlumnos>
</Asignatura>
 */

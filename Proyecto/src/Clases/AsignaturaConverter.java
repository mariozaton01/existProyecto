package Clases;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import Clases.Alumno;
import Clases.Asignatura;


public class AsignaturaConverter implements Converter {
    String patternFecha = "yyyy-MM-dd";
    DateFormat formatoFecha = new SimpleDateFormat(patternFecha);

    public boolean canConvert(Class clazz) {
        return clazz.equals(Asignatura.class);
    }

    public void marshal(Object objeto, HierarchicalStreamWriter writer, MarshallingContext context) {
        Asignatura asignatura = (Asignatura) objeto;

        writer.startNode("ID");
        writer.setValue(String.valueOf(asignatura.getID()) );
        writer.startNode("Nombre");
        writer.setValue(asignatura.getNombre());
        writer.endNode();
        writer.startNode("Lista_alumnos");
        if(asignatura.getAlumnosPorAsig() != null){
            for(Alumno alum : asignatura.getAlumnosPorAsig()){
                writer.startNode("Alumno");
                writer.startNode("DNI");
                writer.setValue(alum.getDNI());
                writer.endNode();
                writer.startNode("Nombre");
                writer.setValue(alum.getNombre());
                writer.endNode();
                writer.startNode("Apellido");
                writer.setValue(formatoFecha.format(alum.getApellido()));
                writer.endNode();
                writer.endNode();
            }
        }
        writer.endNode();





    }

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        try{
            Asignatura asignatura = new Asignatura();
            //dni
            reader.moveDown();
            asignatura.setID(Integer.parseInt(reader.getValue().trim()));
            reader.moveUp();
            //nombre
            reader.moveDown();
            asignatura.setNombre(reader.getValue());
            reader.moveUp();
            //arraylist
            reader.moveDown();

            while(reader.hasMoreChildren()){
                Alumno alum = new Alumno();
                //Objeto alumno
                reader.moveDown();
                reader.moveDown();//dni
                alum.setDNI(reader.getValue());
                reader.moveUp();
                reader.moveDown();//nombre
                alum.setNombre(reader.getValue());
                reader.moveUp();
                reader.moveDown();//apellido
                alum.setApellido(reader.getValue());
                reader.moveUp();

                reader.moveUp();//acabamos Alumno
                asignatura.setAlumno(alum);


            }
            reader.moveUp();

            return asignatura;
        }catch(Exception e){
            System.out.println("Ha ocurrido un error al convertir la asignatura " + e.getMessage());
            return null;
        }
    }
}

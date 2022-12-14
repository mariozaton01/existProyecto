package Clases;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


public  class AlumnoConverter implements Converter {

    String patternFecha = "yyyy-MM-dd";
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(patternFecha);

    public boolean canConvert(Class clase) {
        return clase.equals(Alumno.class);
    }

    public void marshal(Object objeto, HierarchicalStreamWriter writer, MarshallingContext context) {
        Alumno alum = (Alumno) objeto;

        writer.startNode("DNI");
        writer.setValue(alum.getDNI());
        writer.startNode("Nombre");
        writer.setValue(alum.getNombre());
        writer.endNode();
        writer.startNode("Apellido");
        writer.setValue((alum.getApellido()));
        writer.endNode();
        writer.startNode("FechaNac");
        writer.setValue(formatoFecha.format(alum.getFechaNac()));
        writer.endNode();
        writer.startNode("Direccion");
        writer.setValue(String.valueOf(alum.getDireccion()));
        writer.startNode("CP");
        writer.setValue(String.valueOf(alum.getCodigoPostal()));
        writer.startNode("Telefono");
        writer.setValue(String.valueOf(alum.getTelefono()));
        writer.startNode("Email");
        writer.setValue(String.valueOf(alum.getEmail()));
        writer.startNode("Fecha_alta");
        writer.setValue(String.valueOf(alum.getFecha_Alta()));
        writer.endNode();

    }

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        try{
            Alumno alum = new Alumno();
            //dni
            reader.moveDown();
            alum.setDNI(reader.getValue().trim());
            reader.moveUp();
            //nombre
            reader.moveDown();
            alum.setNombre(reader.getValue());
            reader.moveUp();
            //apellido
            reader.moveDown();
            alum.setApellido(reader.getValue());
            reader.moveUp();
            //fechanac
            reader.moveDown();
            LocalDate fechaNac= LocalDate.parse(reader.getValue(),formatoFecha);
            alum.setFechaNac(fechaNac);
            reader.moveUp();
            //direccion
            reader.moveDown();
            alum.setDireccion(reader.getValue());
            reader.moveUp();
            //cp
            reader.moveDown();
            alum.setCodigoPostal(reader.getValue());
            reader.moveUp();

            //telefono
            reader.moveDown();
            alum.setTelefono(Integer.parseInt(reader.getValue().trim()));
            reader.moveUp();
            //email
            reader.moveDown();
            alum.setEmail(reader.getValue().trim());
            reader.moveUp();
            //fecha_alta
            reader.moveDown();
            LocalDate fechaAlta= LocalDate.parse(reader.getValue(),formatoFecha);
            alum.setFecha_Alta(fechaAlta);
            reader.moveUp();

            return alum;
        }catch(Exception e){
            System.out.println("Ha ocurrido un error al convertir el alumno " + e.getMessage());
            return null;
        }
    }

}

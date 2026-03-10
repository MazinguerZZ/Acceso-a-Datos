package Json;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class Cafe {

    int grades;
    String type_coffee;
    int precies;

    public Cafe() {
    }

    public Cafe(int grades, String type_coffee, int precies) {
        this.grades = grades;
        this.type_coffee = type_coffee;
        this.precies = precies;
    }

    @Override
    public String toString() {
        return "Cafe{"
                + "grades=" + grades
                + ", type_coffee='" + type_coffee + '\''
                + ", precies=" + precies
                + '}';
    }

    public static void main(String[] args) {

        // Crear objetos Cafe
        Cafe capuchino = new Cafe(30, "capuchino", 3);
        Cafe matutino = new Cafe(40, "matutino", 4);
        Cafe capuchino_frio = new Cafe(20, "capuchino frio", 2);
        Cafe expreso = new Cafe(40, "expreso", 5);
        Cafe latte = new Cafe(35, "latte", 3);

        /* Creamos un array de objetos para que no sea tedioso */
        Cafe[] cafes = {capuchino, matutino, capuchino_frio, expreso, latte};

        // Crear XStream con driver JSON que permite lectura e escritura por igual 
        XStream xs = new XStream(new JettisonMappedXmlDriver());
        xs.setMode(XStream.NO_REFERENCES);
        /*  Es un modo que representa como va a ser el objeto
        por ejemplo: 
        .NO_REFENCES, usa referencias el objeto se escribe completo
        .ID_REFERENCES usa ids y referencias al objeto esto impide que haya duplicados
        .XPATH_RELATIVE_REFERENCES usa referencias de donde esta situado en el XML
         */

        /* Usamos alias para simplificar el nombre de nuestro objeto ya 
        que sino en nuestro Json se veria como org-emprendism.Tarea2  
        Ejemplo <org.emprendism.json> poniendo el alias <cafe> y definimos la clase Cafe.class*/

        xs.alias("cafe", Cafe.class);

        xs.allowTypes(new Class[]{Cafe.class});

        // Escribir JSON 
        try (FileWriter fw = new FileWriter("file.json")) {
            /* Creamos el fichero json */
            xs.toXML(cafes, fw);
            /* Serealizamos el objeto */
            System.out.println("Archivo se creo correctamente con Xstream");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Leer Json
        try (FileReader fr = new FileReader("file.json")) {
            /* Leemos el fichero json */
            Cafe[] cafesLeidos = (Cafe[]) xs.fromXML(fr);
            System.out.println("Cafes: ");
            for (Cafe c : cafesLeidos) {
                /* Recorremos nuestros objetos */
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

      //Escribir JSON en un archivo usando FileWriter
      Gson gson = new Gson();
        try (FileWriter fw = new FileWriter("file.json")) {
            gson.toJson(cafes, fw);
            System.out.println("Archivo JSON creado correctamente con Gson");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Leer JSON desde el archivo usando FileReader
        try (FileReader fr = new FileReader("file.json")) {
            Cafe[] cf = gson.fromJson(fr, Cafe[].class);
            System.out.println("Cafes");
            for (Cafe c : cf) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

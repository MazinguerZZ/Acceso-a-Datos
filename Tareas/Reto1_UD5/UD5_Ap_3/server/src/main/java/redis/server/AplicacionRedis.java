package redis.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.server.modelo.Cafe;
import redis.server.repository.CafeRepositorio;

import java.util.UUID;

/**
 * Clase principal de la aplicación.
 * Arranca Spring Boot y realiza una prueba de concepto
 * para guardar y recuperar un objeto Cafe en Redis.
 */
@SpringBootApplication
public class AplicacionRedis implements CommandLineRunner {

    /** Repositorio para acceder a Redis. */
    private final CafeRepositorio repositorio;

    /**
     * Constructor que inyecta el repositorio de cafés.
     *
     * @param repositorio el repositorio de Cafe
     */
    public AplicacionRedis(CafeRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Método principal de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(AplicacionRedis.class, args);
    }

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * Crea un Café, lo guarda en Redis y luego lo recupera.
     *
     * @param args argumentos de línea de comandos
     */
    @Override
    public void run(String... args) {

        Cafe cafe = new Cafe(
                UUID.randomUUID().toString(),
                "Café Solo"
        );

        repositorio.guardar(cafe);

        Cafe resultado = repositorio.buscarPorId(cafe.getId());

        System.out.println("Café recuperado desde Redis:");
        System.out.println("ID: " + resultado.getId());
        System.out.println("Nombre: " + resultado.getNombre());
    }
}

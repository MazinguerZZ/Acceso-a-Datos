package redis.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.server.modelo.Cafe;

/**
 * Configuración de Redis para la aplicación.
 * Define los beans necesarios para conectarse a Redis y
 * usar RedisTemplate para objetos Cafe.
 */
@Configuration
public class RedisConfiguracion {

    /**
     * Crea la conexión a Redis usando Lettuce.
     * Por defecto se conecta a localhost en el puerto 6379.
     *
     * @return la fábrica de conexiones a Redis
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    /**
     * Crea un RedisTemplate para objetos Cafe.
     *
     * @param connectionFactory la fábrica de conexiones a Redis
     * @return RedisTemplate configurado para usar Cafe como valor
     */
    @Bean
    public RedisTemplate<String, Cafe> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Cafe> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}

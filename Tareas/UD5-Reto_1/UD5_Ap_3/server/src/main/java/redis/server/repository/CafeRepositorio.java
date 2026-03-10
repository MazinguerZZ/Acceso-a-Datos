package redis.server.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.server.modelo.Cafe;

/**
 * Repositorio para guardar y recuperar objetos Cafe en Redis.
 */
@Repository
public class CafeRepositorio {

    /** Plantilla de Redis usada para almacenar cafés. */
    private final RedisTemplate<String, Cafe> redisTemplate;

    /**
     * Constructor que recibe el RedisTemplate.
     * 
     * @param redisTemplate plantilla de Redis para operaciones sobre cafés
     */
    public CafeRepositorio(RedisTemplate<String, Cafe> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Guarda un café en Redis.
     * 
     * @param cafe el café a guardar
     */
    public void guardar(Cafe cafe) {
        redisTemplate.opsForValue().set(cafe.getId(), cafe);
    }

    /**
     * Recupera un café por su id.
     * 
     * @param id la clave del café
     * @return el café correspondiente o null si no existe
     */
    public Cafe buscarPorId(String id) {
        return redisTemplate.opsForValue().get(id);
    }

	public Object getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

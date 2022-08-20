package my.playground.characters.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import my.playground.characters.entity.Characters;

/**
 * {@link CrudRepository} repository for {@link Characters}
 */
@Repository
public interface CharactersRepository extends CrudRepository<Characters, Long> {
}

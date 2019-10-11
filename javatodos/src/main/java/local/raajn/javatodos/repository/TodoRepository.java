package local.raajn.javatodos.repository;

import local.raajn.javatodos.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{

}

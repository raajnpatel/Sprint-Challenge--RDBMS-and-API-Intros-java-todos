package local.raajn.todosjava.repositories;


import local.raajn.todosjava.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
}

package local.raajn.todosjava.repos;

import com.krishannattar.sprint.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<Todo, Long> {

}

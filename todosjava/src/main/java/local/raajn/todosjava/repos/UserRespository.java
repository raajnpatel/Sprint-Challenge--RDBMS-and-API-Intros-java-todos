package local.raajn.todosjava.repos;

import local.raajn.todosjava.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRespository extends CrudRepository<User, Long>
{
}

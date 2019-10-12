package local.raajn.javatodos.repository;

import local.raajn.javatodos.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{

    User findByUsername(String username);
}

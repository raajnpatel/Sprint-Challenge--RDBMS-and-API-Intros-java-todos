package local.raajn.javatodos.service;

import local.raajn.javatodos.models.Todo;
import local.raajn.javatodos.models.User;
import local.raajn.javatodos.models.UserRoles;
import local.raajn.javatodos.repository.RoleRepository;
import local.raajn.javatodos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService
{
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserByName(String name) throws EntityNotFoundException
    {
        return userrepos.findByUsername(name);
    }

    @Override
    public User findUserById(long id)
    {
        return userrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (userrepos.findById(id).isPresent())
        {
            userrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserRoles())
        {
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }

        for (Todo t : user.getTodos())
        {
            newUser.getTodos().add(new Todo(t.getDescription(), LocalDateTime.now(), false, newUser));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, long id)
    {
        User currentUser = userrepos.findById(id).orElseThrow(()-> new EntityNotFoundException(Long.toString(id)));

        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername());
        }

        if (user.getPassword()!=null)
        {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }

        if (user.getUserRoles().size()>0)
        {
            rolerepos.deleteUserRolesByUserId(currentUser.getUserid());

            for (UserRoles ur: user.getUserRoles())
            {
                rolerepos.insertUserRoles(id, ur.getRole().getRoleid());
            }
        }

        if (user.getTodos().size()>0)
        {
            for (Todo t: user.getTodos())
            {
                currentUser.getTodos().add(new Todo(t.getDescription(), LocalDateTime.now(), false, currentUser));
            }
        }
        return userrepos.save(currentUser);
    }
}
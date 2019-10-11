package local.raajn.javatodos.service;

import local.raajn.javatodos.models.Role;
import local.raajn.javatodos.repository.RoleRepository;
import local.raajn.javatodos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRepository userrepos;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll()
                 .iterator()
                 .forEachRemaining(list::add);
        return list;
    }


    @Override
    public Role findRoleById(long id)
    {
        return rolerepos.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
    }


    @Transactional
    @Override
    public void delete(long id)
    {
        rolerepos.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
        rolerepos.deleteById(id);
    }


    @Transactional
    @Override
    public Role save(Role role)
    {
        return rolerepos.save(role);
    }
}

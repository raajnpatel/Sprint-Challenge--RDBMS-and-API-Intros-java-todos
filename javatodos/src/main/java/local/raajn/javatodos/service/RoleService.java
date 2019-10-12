package local.raajn.javatodos.service;


import local.raajn.javatodos.models.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);

}
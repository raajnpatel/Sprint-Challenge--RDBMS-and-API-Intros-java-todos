package local.raajn.javatodos;

import local.raajn.javatodos.models.Role;
import local.raajn.javatodos.models.Todo;
import local.raajn.javatodos.models.User;
import local.raajn.javatodos.models.UserRoles;
import local.raajn.javatodos.service.RoleService;
import local.raajn.javatodos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@Transactional
@Component
class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                                 r1));
        admins.add(new UserRoles(new User(),
                                 r2));
        admins.add(new UserRoles(new User(),
                                 r3));
         User u1 = new User("admin",
                           "password",
                           admins);
        u1.getTodos().add(new Todo("Finish java-orders-swagger", new Date(), u1));
        u1.getTodos().add(new Todo("Feed the turtles", new Date(), u1));
        u1.getTodos().add(new Todo("Complete the sprint challenge", new Date(), u1));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
                                r3));
        datas.add(new UserRoles(new User(),
                                r2));
        User u2 = new User("cinnamon",
                           "1234567",
                           datas);
        u2.getTodos().add(new Todo("Walk the dogs", new Date(), u2));
        u2.getTodos().add(new Todo("provide feedback to my instructor", new Date(), u2));
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u3 = new User("barnbarn",
                           "ILuvM4th!",
                           users);
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u4 = new User("puttat",
                           "password",
                           users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u5 = new User("misskitty",
                           "password",
                           users);
        userService.save(u5);
    }
}

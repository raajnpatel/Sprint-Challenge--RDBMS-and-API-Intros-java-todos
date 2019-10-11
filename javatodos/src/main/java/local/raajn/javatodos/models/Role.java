package local.raajn.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(unique = true, nullable = false)
    private String rolename;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRole> userRoles = new ArrayList<>();

    public Role()
    {
    }

    public Role(String rolename)
    {
        this.rolename = rolename;
    }

    public long getRoleid()
    {
        return roleid;
    }

    public void setRoleid(long roleid)
    {
        this.roleid = roleid;
    }

    public String getRolename()
    {
        return rolename;
    }

    public void setRolename(String rolename)
    {
        this.rolename = rolename;
    }

    public List<UserRole> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles)
    {
        this.userRoles = userRoles;
    }
}
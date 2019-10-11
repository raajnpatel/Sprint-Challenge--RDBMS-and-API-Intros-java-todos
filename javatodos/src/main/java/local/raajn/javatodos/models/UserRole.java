package local.raajn.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "userroles")
public class UserRole extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
    @JoinColumn(name = "userid")
    private User user;

    @Id
    @ManyToOne
    @JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
    @JoinColumn(name = "roleid")
    private Role role;

    public UserRole()
    {
    }

    public UserRole(User user, Role role)
    {
        this.user = user;
        this.role = role;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UserRole))
        {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return getUser().equals(userRole.getUser()) && getRole().equals(userRole.getRole());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(), getRole());
    }
}
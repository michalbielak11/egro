package pl.coderslab.egro.entity;

import lombok.*;
import org.hibernate.validator.constraints.Email;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    @Email
    private String email;
    private int enabled;
    @OneToOne(cascade = {CascadeType.ALL})
    private Order order;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @PreRemove
    public void deleteRoles() {
        this.getRoles().clear();
    }

}

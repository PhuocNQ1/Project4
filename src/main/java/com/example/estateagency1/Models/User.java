package com.example.estateagency1.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.NaturalId;


import javax.persistence.*;
import java.io.Serializable;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Username", length = 50)
    @NaturalId
    private String username;

    @Column(name = "Password", length = 255)
    private String password;

    @Column(name = "Name", length = 50)
    private String name;

    @Column(name = "Phone")
    private Integer phone;

    @Column(name = "Email", length = 50)
    private String email;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();

    @Transient
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }
    @Transient
    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Property> propertyList = new ArrayList<>();

    @Transient
    public void addProperty(Property property) {
        propertyList.add(property);
        property.setUser(this);
    }
    @Transient
    public String avatarPath() {

        if (avatar == null || id == 0) return "/";
        return "/USER_IMAGES/" + avatar;
    }


}
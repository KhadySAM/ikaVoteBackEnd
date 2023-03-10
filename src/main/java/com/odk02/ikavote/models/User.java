package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
  //contrainte d'unicité
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
  })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //champ obligatoire
  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  public User(String username, String email, String password, Set<Role> roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  @NotBlank
  @Size(max = 120)
  private String password;





//  @ManyToMany(mappedBy = "JURY")
//  private List<Evenements> evenements;

  @JoinColumn(name = "id_pays")
  @ManyToOne
  private  Pays pays;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();




/*
public User(Long id, String username, String email, String encode, Long pays, HashSet<Role> roles) {
  }
  public User() {
  }

  public User(String username, String email, String password, String images) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public User(String username, String email, String encode) {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }*/
}

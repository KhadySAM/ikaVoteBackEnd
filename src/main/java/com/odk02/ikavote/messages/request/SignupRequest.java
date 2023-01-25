package com.odk02.ikavote.messages.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
@Setter
@Getter
public class SignupRequest {
 // @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  //@NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

 // @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  //@NotBlank
  private String images;

 /* public String getUsername() {
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

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }*/
}

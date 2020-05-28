package com.siit.sbnz.sbnztim14.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

/**
 * Represents a registered user in the system.
 * A registered user can reserve and buy tickets, etc.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegisteredUser extends User {

   public RegisteredUser(String username, String email, String password, String name) {
      super(username, password, email, name);
      this.authorities.add(Role.REGISTERED_USER);
   }
}
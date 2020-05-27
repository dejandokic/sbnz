package com.siit.sbnz.sbnztim14.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents an user of the system.
 */
@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
public class User {

   /**
    * User's unique identifier
    */
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   /**
    * Username of the user
    */
   @Column(nullable = false)
   protected String username;

   /**
    * Password of the user
    */
   @Column(nullable = false)
   protected String password;

   /**
    * Email address of the user
    */
   @Column(nullable = false)
   protected String email;

   /**
    * First name of the user
    */
   @Column(nullable = false)
   protected String firstName;

   /**
    * Last name of the user
    */
   @Column(nullable = false)
   protected String lastName;

   /**
    * Authorities collection of the user
    */
   @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
   @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
   @Column(name = "authority", nullable = false)
   @Enumerated(EnumType.STRING)
   protected Collection<Role> authorities = new ArrayList<>();

   public User(String username, String email, String password, String firstName, String lastName) {
      this.username = username;
      this.password = password;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
      this.authorities = new ArrayList<>();
   }

   /**
    * Gives the information about whether the user has a given authority, or not
    * @param role Checked authority
    * @return True if user has given authority, otherwise false
    */
   public boolean hasAuthority(Role role) {
      return authorities.contains(role);
   }
}
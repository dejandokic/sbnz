package com.siit.sbnz.sbnztim14.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

/**
 * Represents the system administrator.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {

    public Admin(String username, String email, String password, String name) {
        super(username, password, email, name);
        this.authorities.add(Role.ADMIN);
    }
}
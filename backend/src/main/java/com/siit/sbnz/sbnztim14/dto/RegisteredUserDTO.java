package com.siit.sbnz.sbnztim14.dto;

import com.siit.sbnz.sbnztim14.model.RegisteredUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserDTO {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    private String phone;

    public RegisteredUserDTO(RegisteredUser registeredUser) {
        this.username = registeredUser.getUsername();
        this.email = registeredUser.getEmail();
        this.firstName = registeredUser.getFirstName();
        this.lastName = registeredUser.getLastName();
        this.password = registeredUser.getPassword();
    }

    public RegisteredUser convertToEntity() {
        return new RegisteredUser(username, password, email, firstName, lastName);
    }
}

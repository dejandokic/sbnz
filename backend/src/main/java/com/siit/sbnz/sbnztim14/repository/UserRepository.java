package com.siit.sbnz.sbnztim14.repository;

import com.siit.sbnz.sbnztim14.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    <E extends User> Optional<E> findByUsernameIgnoreCase(String username);

    <E extends User> Optional<E> findByEmail(String email);

    <E extends User> E save(E user);
}

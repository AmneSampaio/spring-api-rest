package com.concrete.projeto.refactor.repository;


import com.concrete.projeto.refactor.model.Address;
import com.concrete.projeto.refactor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findByAddress(Address address);
    boolean existsByCpfOrLogin(String cpf, String login);
}

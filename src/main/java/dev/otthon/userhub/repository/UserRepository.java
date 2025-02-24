package dev.otthon.userhub.repository;

import dev.otthon.userhub.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    User findByCpfOrEmail(String cpf, String email);
}

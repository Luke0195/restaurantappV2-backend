package br.com.waiterapp.application.repositories;

import br.com.waiterapp.application.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT u FROM User as u where u.status != 'ADMIN' ")
    Page<User> findAllGetUsers(Pageable pageable);
}

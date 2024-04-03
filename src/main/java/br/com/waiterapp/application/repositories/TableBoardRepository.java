package br.com.waiterapp.application.repositories;

import br.com.waiterapp.application.domain.tableboard.TableBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableBoardRepository extends JpaRepository<TableBoard, UUID> {
    Optional<TableBoard> findByName(String name);

}

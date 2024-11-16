package ru.nefedova.nexign.output.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nefedova.nexign.output.persistance.model.TaskEntity;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByOrderByUpdateDateDesc();

    boolean existsById(long id);

}

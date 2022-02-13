package ru.milexe.test_task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends CrudRepository<StudentEntity, Long> {
List<StudentEntity> findAllByGroup(Optional<GroupEntity> group);
}

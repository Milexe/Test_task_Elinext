package ru.milexe.test_task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.milexe.test_task.entity.StudentEntity;

public interface StudentRepo extends CrudRepository<StudentEntity, Long> {
}

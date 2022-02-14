package ru.milexe.test_task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.milexe.test_task.entity.LectureEntity;

public interface LectureRepo extends CrudRepository<LectureEntity, Long> {
}

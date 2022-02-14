package ru.milexe.test_task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.TimetableEntity;

import java.sql.Date;
import java.util.List;

public interface TimetableRepo extends CrudRepository<TimetableEntity, Long> {
    public List<TimetableEntity> findAllByGroupAndDay(GroupEntity group, String day);
}

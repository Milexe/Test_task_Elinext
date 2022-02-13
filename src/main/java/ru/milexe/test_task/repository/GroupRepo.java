package ru.milexe.test_task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.milexe.test_task.entity.GroupEntity;

public interface GroupRepo extends CrudRepository<GroupEntity, Long> {
    GroupEntity findByNumber(Long number);
}

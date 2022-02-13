package ru.milexe.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.model.Group;
import ru.milexe.test_task.repository.GroupRepo;

@Service
public class GroupService {

    @Autowired
    private GroupRepo groupRepo;

    public Group createGroup(GroupEntity group){
        return Group.toModel(groupRepo.save(group));
    }

    public Group getGroup(Long id) throws Exception {
        if(!groupRepo.findById(id).isPresent())
            throw new Exception("группа не найдена");
        return Group.toModel(groupRepo.findById(id).get());
    }
}

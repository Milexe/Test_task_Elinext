package ru.milexe.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.model.Group;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.StudentRepo;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private StudentRepo studentRepo;

    public Group createGroup(GroupEntity group){
        return Group.toModel(groupRepo.save(group));
    }

    public Group getGroup(Long id){
        return Group.toModel(groupRepo.findById(id).get());
    }

    public Group deleteGroup(Long id){
        Group deletedGroup = Group.toModel(groupRepo.findById(id).get());
        List<StudentEntity> studentsToDelete = studentRepo.findAllByGroup(groupRepo.findById(id));
        studentRepo.deleteAll(studentsToDelete);
        groupRepo.deleteById(id);
        return deletedGroup;
    }

    public Group updateGroup(GroupEntity group){
        GroupEntity updatedGroup = groupRepo.findById(group.getId()).get();
        updatedGroup.setNumber(group.getNumber());
        updatedGroup.setSpeciality(group.getSpeciality());
        return Group.toModel(groupRepo.save(updatedGroup));
    }
}

package ru.milexe.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.model.Group;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.StudentRepo;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Group> getGroups(){
        List<GroupEntity> list = (List<GroupEntity>) groupRepo.findAll();
        List<Group> resultList = list.stream().map(Group::toModel).collect(Collectors.toList());
        return resultList;
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
        updatedGroup.setSpeciality(group.getSpeciality()); // this logic was added to view list of students in updated group
        return Group.toModel(groupRepo.save(group));
    }
}

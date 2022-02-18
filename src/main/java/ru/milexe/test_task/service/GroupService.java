package ru.milexe.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milexe.test_task.exception.DataAlreadyExistsException;
import ru.milexe.test_task.exception.DataNotFoundException;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.model.Group;
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

    public Group createGroup(GroupEntity group) throws DataAlreadyExistsException {
        if(groupRepo.findById(group.getId()).isPresent() | groupRepo.findByNumber(group.getNumber()) != null)
            throw new DataAlreadyExistsException("группа уже существует");
        return Group.toModel(groupRepo.save(group));
    }

    public Group getGroup(Long id) throws DataNotFoundException{
        if(!groupRepo.findById(id).isPresent())
            throw new DataNotFoundException("группа не найденаa");
        return Group.toModel(groupRepo.findById(id).get());
    }

    public List<Group> getGroups(){
        List<GroupEntity> list = (List<GroupEntity>) groupRepo.findAll();
        List<Group> resultList = list.stream().map(Group::toModel).collect(Collectors.toList());
        return resultList;
    }

    public Group deleteGroup(Long id) throws DataNotFoundException, DataAlreadyExistsException {
        if(!groupRepo.findById(id).isPresent())
            throw new DataNotFoundException("группа не найдена");
        GroupEntity entity = groupRepo.findById(id).get();
        if(!entity.getTimetables().isEmpty())
            throw new DataAlreadyExistsException("невозможно удалить группу: существует расписание для этой группы");
        Group deletedGroup = Group.toModel(entity);
        List<StudentEntity> studentsToDelete = studentRepo.findAllByGroup(groupRepo.findById(id));
        studentRepo.deleteAll(studentsToDelete);
        groupRepo.deleteById(id);
        return deletedGroup;
    }

    public Group updateGroup(GroupEntity group) throws DataNotFoundException, DataAlreadyExistsException {
        if(!groupRepo.findById(group.getId()).isPresent())
            throw new DataNotFoundException("группа не найдена");
        GroupEntity updatedGroup = groupRepo.findById(group.getId()).get();
        if(groupRepo.findByNumber(group.getNumber()) != null & group.getNumber() != updatedGroup.getNumber())
            throw new DataAlreadyExistsException("группа уже существует");
        updatedGroup.setNumber(group.getNumber());
        updatedGroup.setSpeciality(group.getSpeciality()); // this logic was added to view list of students in updated group
        return Group.toModel(groupRepo.save(updatedGroup));
    }
}

package ru.milexe.test_task.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.entity.TimetableEntity;
import ru.milexe.test_task.model.Group;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.StudentRepo;
import ru.milexe.test_task.service.GroupService;
import ru.milexe.test_task.service.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceTest {
    @InjectMocks
    private GroupService groupService;
    @Mock
    private GroupRepo groupRepo;
    @Mock
    private StudentRepo studentRepo;


    @Test
    public void createGroupTest() throws Exception {
        GroupEntity group = new GroupEntity(1,"math", 1);
        Mockito.when(groupRepo.findById(group.getId())).thenReturn(Optional.empty());
        Mockito.when(groupRepo.findByNumber(group.getNumber())).thenReturn(null);
        Mockito.when(groupRepo.save(group)).thenReturn(group);

        Group res = groupService.createGroup(group);

        assertEquals(Group.toModel(group).getId(), res.getId());
    }
    @Test
    public void getGroupTest() throws Exception {
        GroupEntity group = new GroupEntity(1,"math", 1);
        Mockito.when(groupRepo.findById(group.getId())).thenReturn(Optional.of(group));

        Group res = groupService.getGroup(group.getId());

        assertEquals(Group.toModel(group).getId(), res.getId());
    }
    @Test
    public void getGroupsTest(){
        GroupEntity group = new GroupEntity(1,"math", 1);
        Mockito.when(groupRepo.findAll()).thenReturn(Arrays.asList(
                new GroupEntity(),
                new GroupEntity(),
                new GroupEntity()
        ));

        List<Group> allGroups = groupService.getGroups();

        assertEquals(3, allGroups.size());
    }


    @Test
    public void deleteGroupTest() throws Exception {
        GroupEntity group = new GroupEntity(1,"math", 1);
        group.setTimetables(new ArrayList<TimetableEntity>() {});
        Mockito.when(studentRepo.findAllByGroup(Optional.of(group))).thenReturn(Arrays.asList());
        Mockito.when(groupRepo.findById(group.getId())).thenReturn(Optional.of(group));

        Group res = groupService.deleteGroup(group.getId());

        assertEquals(Group.toModel(group).getId(), res.getId());
    }


    @Test
    public void updateGroupTest() throws Exception {
        GroupEntity group = new GroupEntity(1,"math", 1);
        Mockito.when(groupRepo.findById(group.getId())).thenReturn(Optional.of(group));
        Mockito.when(groupRepo.findByNumber(group.getNumber())).thenReturn(null);
        Mockito.when(groupRepo.save(group)).thenReturn(group);

        Group res = groupService.updateGroup(group);

        assertEquals(Group.toModel(group).getId(), res.getId());
    }

}

package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testTaskMapper(){
        //Given
        Task task = new Task(1L, "test task", "sample content");
        List<Task> testList= new ArrayList<>();
        testList.add(task);

        //Then
        TaskDto taskDtoTest = taskMapper.mapToTaskDto(task);
        Task taskTest =  taskMapper.mapToTask(taskDtoTest);
        List<TaskDto> testListDto = taskMapper.mapToTaskDtoList(testList);

        //When
        Assert.assertEquals("test task", taskTest.getTittle());
        Assert.assertEquals("sample content", testListDto.get(0).getContent());
        Assert.assertNotNull(taskDtoTest);


    }

}
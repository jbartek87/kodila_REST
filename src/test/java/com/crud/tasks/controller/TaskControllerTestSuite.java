package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTestSuite {

    @InjectMocks
    private TaskController taskController;


    @Mock
    private TaskMapper taskMapper;



    @Mock
    private DbService service;


    @Test
    public void testTaskController(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test tittle", "test content");
        List<TaskDto> myList = new ArrayList<>();
        long taskId = taskDto.getId();
        myList.add(taskDto);

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(myList);
        when(taskMapper.mapToTaskDto(service.saveTask( taskMapper.mapToTask( taskDto ) ))).thenReturn(taskDto);


        //When
        List<TaskDto> testedList = taskController.getTasks();
        TaskDto updatedTaskDto = taskController.updateTask(taskDto);

        //Then
        Assert.assertEquals(1, testedList.size());
        Assert.assertEquals("test content", updatedTaskDto.getContent());







    }

}
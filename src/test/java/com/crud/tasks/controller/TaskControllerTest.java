package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper mapper;

    @Test
    public void testGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "xxx", "yyy");
        taskDtos.add(taskDto);
        when(mapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtos);


        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "xxx", "yyy");
        TaskDto task = new TaskDto(1L,"xxx", "yyy");

        when(service.getTaskById(1L)).thenReturn(java.util.Optional.ofNullable(task1));
        when(mapper.mapToTaskDto(any(Task.class))).thenReturn(task);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("xxx")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("yyy")));
    }



    @Test
    public void testCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "test task", "cont");
        Task task = new Task(1L, "test task", "cont");
        when(mapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));
        verify(service, atLeastOnce()).saveTask(task);
    }

    @Test
    public void testUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto(2L, "Update", "updt cont");
        Task task = new Task (2L, "Update", "updt cont");
        when(service.saveTask(mapper.mapToTask(taskDto))).thenReturn(task);
        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Update")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("updt cont")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Task task = new Task(2L, "Update", "updt cont");
        long taskId = task.getId();

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/task/deleteTask?taskId=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, atLeastOnce()).deleteTaskById(2L);
    }

}

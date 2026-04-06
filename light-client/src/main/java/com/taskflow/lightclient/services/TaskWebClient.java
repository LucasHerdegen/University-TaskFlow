package com.taskflow.lightclient.services;

import com.taskflow.lightclient.dtos.TaskDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TaskWebClient
{
    private final RestClient restClient = RestClient.create();

    public List<TaskDto> fetchTasks()
    {
        return restClient.get()
                .uri("http://localhost:8080/api/task")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TaskDto>>() {});
    }
}

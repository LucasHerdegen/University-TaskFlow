package com.taskflow.lightclient.controllers;

import com.taskflow.lightclient.services.TaskWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
class WebController
{
    private final TaskWebClient taskWebClient;

    @GetMapping("/")
    public String viewHome(Model model)
    {
        var tasks = taskWebClient.fetchTasks();
        model.addAttribute("taskList", tasks);

        return "index";
    }
}

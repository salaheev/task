package javatesttask.task.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PageController {

    @GetMapping
    public String hello() {

        return "This is the main page of the service.\n" +
            "Please open up Postman and start using the service RESTfully.";
    }
}

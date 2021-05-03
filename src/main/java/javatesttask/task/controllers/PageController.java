package javatesttask.task.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PageController {

    @GetMapping
    public String hello() {

        return "Hello to our service";
    }

}

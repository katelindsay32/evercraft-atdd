package com.dojo.evercraft;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

        RestTemplate restTemplate = new RestTemplate();
        String boss = restTemplate.getForObject("http://localhost:8089/boss",
                String.class);
        model.addAttribute("name", boss);

        return "greeting";
    }
}
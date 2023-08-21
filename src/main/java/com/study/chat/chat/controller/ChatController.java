package com.study.chat.chat.controller;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatGet(Model model) {
        log.info("ChatController Test");

        model.addAttribute("name", UUID.randomUUID().toString());
        return "chat";
    }
}
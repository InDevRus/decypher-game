package org.indev.decyphergame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
@RequestMapping("/play")
public class GameController {
    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/dialPad")
    public void dialPad() {

    }

    @GetMapping("/atbash")
    public void atbash() {

    }

    @GetMapping("/caesar")
    public void caesar() {

    }
}

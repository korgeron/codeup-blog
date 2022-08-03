package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {



    @GetMapping("/roll-dice")
    public String diceRollHomePage() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRoll(@PathVariable int n, Model model) {

        int random = (int) (Math.random() * 6) + 1;
        int random1 = (int) (Math.random() * 6) + 1;
        int random2 = (int) (Math.random() * 6) + 1;
        int random3 = (int) (Math.random() * 6) + 1;
        int random4 = (int) (Math.random() * 6) + 1;


        model.addAttribute("userChoice", String.format("%d was your choice", n));
        model.addAttribute("random", String.format("%d was the answer", random));
        model.addAttribute("square1", random1);
        model.addAttribute("square2", random2);
        model.addAttribute("square3", random3);
        model.addAttribute("square4", random4);

        int count = 0;

        if (n == random) {
            model.addAttribute("answer", "YOU WIN!");
        } else {
            model.addAttribute("answer", "YOU LOST, TRY AGAIN!");
        }

        if (n == random1 || n == random2 || n == random3 || n == random4) {
            count++;
        }
        model.addAttribute("count", String.format("Count: %d", count));

        return "roll-dice";
    }

    public static void main(String[] args) {


    }
}

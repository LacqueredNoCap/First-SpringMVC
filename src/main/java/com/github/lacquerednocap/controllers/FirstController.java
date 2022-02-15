package com.github.lacquerednocap.controllers;

import com.github.lacquerednocap.model.CalculatorActions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "firstName", required = false) String firstName,
                            @RequestParam(value = "secondName", required = false) String secondName, Model model) {

        model.addAttribute("message", firstName + " " + secondName);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam("a") int a,
                                 @RequestParam("b") int b,
                                 @RequestParam("action") CalculatorActions action,
                                 Model model) {

        String result = "Oops.. Something went wrong";
        try {
            switch (action) {
                case MULT:
                    result = Long.toString((long) a * b);
                    break;
                case ADD:
                    result = Long.toString((long) a + b);
                    break;
                case SUB:
                    result = Long.toString((long) a - b);
                    break;
                case DIV:
                    try {
                        result = Long.toString((long) a / b);
                    } catch (ArithmeticException e) {
                        result = "Division by zero is not allowed";
                    }
                    break;
            }
        } catch (IllegalArgumentException ignored) {
        }

        model.addAttribute("result", result);

        return "first/calculator";
    }
}

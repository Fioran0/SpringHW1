package com.example.controllers;

import com.example.model.Greeting;
import com.example.validator.EmailValidator;
import com.example.validator.UserNameValidator;
import com.example.writeToFile.FileWorker;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.IIOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, HttpServletResponse response){
        Cookie cookie = new Cookie("username", greeting.getName());
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);
        if(new EmailValidator().validate(greeting.getEmale()) && new UserNameValidator().validate(greeting.getName())) {
            FileWorker.write(greeting.getName(), greeting.getSurname(), greeting.getPatronymic(),
                    greeting.getAge(), greeting.getSalary(), greeting.getEmale(), greeting.getPlaceOfWork());
            return "result";
        } else return "greeting";
    }

    @RequestMapping("/searching")
    public String searcingForm(Model model){
        model.addAttribute("greeting", new Greeting());
        return "searching";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, @RequestParam String surname, HttpServletResponse response){
        /*String line = null;
            try{
                FileReader fr = new FileReader(FileWorker.file);
                BufferedReader reader = new BufferedReader(fr);
                line = reader.readLine();
                while(line != null) {
                    line = reader.readLine();
                    System.out.println(line);
                    String[] strings = new String[2];
                    strings = line.split("\\s+");
                    System.out.println(strings[0]);
                    System.out.println(strings[1]);
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }*/
        System.out.println(name);
        System.out.println(surname);
        return "result";
    }
}

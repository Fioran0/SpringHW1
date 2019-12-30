package com.example.controllers;

import com.example.model.User;
import com.example.validator.EmailValidator;
import com.example.validator.UserNameValidator;
import com.example.writeToFile.FileWorker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.example.writeToFile.FileWorker.file;

@Controller
public class UserController {

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "greeting";
    }

    @PostMapping("/user")
    public String greetingSubmit(@ModelAttribute User user, HttpServletResponse response){
        Cookie cookie = new Cookie("username", user.getName());
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);
        if(new EmailValidator().validate(user.getEmale()) && new UserNameValidator().validate(user.getName())) {
            FileWorker.write(user.getName(), user.getSurname(), user.getPatronymic(),
                    user.getAge(), user.getSalary(), user.getEmale(), user.getPlaceOfWork());
            return "result";
        } else return "user";
    }

    @RequestMapping("/searching")
    public String searcingForm(){
        return "searching";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String name, @RequestParam String surname){
        List<String> list = new ArrayList<>();
        try {
            Files.lines(Paths.get(String.valueOf(file)), StandardCharsets.UTF_8).forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String elem: list) {
            String[] strings = new String[7];
            strings = elem.split("\\s+");
            if (name.equals(strings[0]) && surname.equals(strings[1])) {
                System.out.println("user find");
                User greet = new User(strings[0], strings[1], strings[2], Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), strings[5], strings[6] );
                model.addAttribute("user", greet);
                return "result2";
            }
        }
        return "notfound";
    }

    @GetMapping("/upload")
    public String uploadFileForm(){
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file, Model model){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String str = new String(bytes, StandardCharsets.UTF_8);

                    String[] strings = new String[7];
                    strings = str.split("\\s+");

                        User greet = new User(strings[0], strings[1], strings[2], Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), strings[5], strings[6] );
                        model.addAttribute("greeting", greet);
                        return "result2";

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "notfound";
    }

}

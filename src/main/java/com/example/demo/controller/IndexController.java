package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path="/user")
public class IndexController {

    @Autowired
    private UserDao userDao;

    private User user = new User();

    @GetMapping(path="/index")
    public String index(HttpSession httpSession) {
        httpSession.getAttribute("userLogin");
        return "index1";
    }

    @GetMapping(path="/register")
    public String register() {
        return "register1";
    }

    @GetMapping(path="/login")
    public String login() {
        String str = "";
        String username = user.getUsername();
        if (username != null) {
            str = "index1";
        }else {
            str = "login1";
        }
        return str;
    }

    @PostMapping(path="/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password1") String password1,
                           @RequestParam("password2") String password2) {
//        String username = request.getParameter("username");
//        String password1 = request.getParameter("password1");
//        String password2 = request.getParameter("password2");
        String str = "";
        if (password1.equals(password2)) {
            user = userDao.findByUsername(username);
            if (user == null) {
                User user = new User(username, password1);
                userDao.save(user);
                str = "login1";
            }else {
                str = "register1";
            }
        }

        return  str;
    }

    @GetMapping(path="/ulogin")
    public String login(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user = userDao.findByUsernameAndPassword(username, password);

        String str = "";
        if (user != null) {
            session.setAttribute("userLogin", user);
            str = "index1";
        }else {
            str = "login1";
        }
        return str;
    }
}

package org.phls.personalTrainer.web.controller;

import org.phls.personalTrainer.web.dao.impl.UserDao;
import org.phls.personalTrainer.web.model.impl.User;
import org.phls.personalTrainer.web.service.ScEntityService;
import org.phls.personalTrainer.web.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/LogIn")
public class LogInController extends HttpServlet {
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String LOGIN_PAGE = "/jsp/login.jsp";
    public static final String MAIN_PAGE = "/jsp/main.jsp";
    ScEntityService<User> userService = UserService.getInstance(UserDao.getInstance());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        try {
            if (userService.get(login).getPassword().equals(password)) {
                req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
            }
        } catch (NoSuchElementException e) {
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }
}

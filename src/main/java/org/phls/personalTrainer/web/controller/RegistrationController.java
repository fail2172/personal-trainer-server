package org.phls.personalTrainer.web.controller;

import org.phls.personalTrainer.web.model.impl.User;
import org.phls.personalTrainer.web.service.ScEntityService;
import org.phls.personalTrainer.web.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerUser")
public class RegistrationController extends HttpServlet {
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String PAGE_MODE = "pageMode";
    public static final String CREATE = "create";
    public static final String LOG_IN = "/LogIn";
    public static final String EDIT_USER_PAGE = "/jsp/editUser.jsp";
    ScEntityService<User> userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PAGE_MODE, CREATE);
        req.getRequestDispatcher(EDIT_USER_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);

        userService.add(new User(login, password));

        resp.sendRedirect(LOG_IN);
    }
}

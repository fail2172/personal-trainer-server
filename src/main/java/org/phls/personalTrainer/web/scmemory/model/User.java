package org.phls.personalTrainer.web.scmemory.model;

import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.model.pattern.EntityPattern;
import org.phls.personalTrainer.web.scmemory.model.pattern.UserPattern;

import java.util.Objects;

public class User {
    private final String login;
    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(ScNode userNode) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            EntityPattern userPattern = UserPattern.getInstance();
            var pattern = userPattern.receivePattern(userNode);
            var userConstruction = connection.find(pattern).findFirst();
            if (userConstruction.isEmpty())
                throw new ScException("User construction not found");

            var userConstructionElements = userConstruction.get().toList();

            ScLinkString loginLink = (ScLinkString) userConstructionElements.get(UserPattern.LOGIN_INDEX);
            ScLinkString passwordLink = (ScLinkString) userConstructionElements.get(UserPattern.PASSWORD_INDEX);

            login = connection.getStringLinkContent(loginLink);
            password = connection.getStringLinkContent(passwordLink);
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

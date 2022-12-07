package org.phls.personalTrainer.web.model.impl;

import org.phls.personalTrainer.web.model.ScEntity;
import org.phls.personalTrainer.web.scmemory.node.DiseaseNodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements ScEntity {
    private final String login;
    private final String password;
    private final List<DiseaseNodes> diseases;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        diseases = new ArrayList<>();
    }

    public User(String login, String password, List<DiseaseNodes> diseases) {
        this.login = login;
        this.password = password;
        this.diseases = diseases;
    }

    @Override
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<DiseaseNodes> getDiseases() {
        return diseases;
    }

    public User withDiseases(List<DiseaseNodes> diseases) {
        return new User(login, password, diseases);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(diseases, user.diseases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, diseases);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", diseases=" + diseases +
                '}';
    }
}

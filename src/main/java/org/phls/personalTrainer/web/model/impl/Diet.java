package org.phls.personalTrainer.web.model.impl;

import org.phls.personalTrainer.web.model.ScEntity;
import org.phls.personalTrainer.web.scmemory.node.Products;

import java.util.List;
import java.util.Objects;

public class Diet implements ScEntity {
    private Integer id;
    private final List<Products> breakfast;
    private final List<Products> lunch;
    private final List<Products> dinner;

    public Diet(List<Products> breakfast, List<Products> lunch, List<Products> dinner) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Products> getBreakfast() {
        return breakfast;
    }

    public List<Products> getLunch() {
        return lunch;
    }

    public List<Products> getDinner() {
        return dinner;
    }

    @Override
    public String getLogin() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diet diet = (Diet) o;
        return Objects.equals(id, diet.id)
                && Objects.equals(breakfast, diet.breakfast)
                && Objects.equals(lunch, diet.lunch)
                && Objects.equals(dinner, diet.dinner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breakfast, lunch, dinner);
    }

    @Override
    public String toString() {
        return "Diet{" +
                "id=" + id +
                ", breakfast=" + breakfast +
                ", lunch=" + lunch +
                ", dinner=" + dinner +
                '}';
    }
}
package com.nikitadeveloper.hs2.task1;

public class Builder extends Human {
    private String constructionProject;

    public Builder(String name, int age, String constructionProject) {
        super(name, age);
        this.constructionProject = constructionProject;
    }

    public String getConstructionProject() {
        return constructionProject;
    }

    public void setConstructionProject(String constructionProject) {
        this.constructionProject = constructionProject;
    }

    @Override
    public String toString() {
        return super.toString() + ", ConstructionProject: " + constructionProject;
    }
}

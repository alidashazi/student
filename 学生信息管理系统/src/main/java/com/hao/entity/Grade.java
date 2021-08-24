package com.hao.entity;

import org.springframework.stereotype.Component;

//gradeid是前端传，id是后端
@Component
public class Grade {
    private int id;
    private String name;
    private String remark;

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Grade() {
    }

    public Grade(int id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }
}

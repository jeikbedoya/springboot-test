package com.jeikode.test.patterns.prototype;

import java.util.ArrayList;
import java.util.List;

public class Employees implements Cloneable {

    private List<String> empList;

    public Employees() {
        this.empList = new ArrayList<>();
    }

    public Employees(List<String> empList) {
        this.empList = empList;
    }

    public void loadData(){
        empList.add("Roman");
        empList.add("Maria");
        empList.add("Juan");
        empList.add("Simon");
        empList.add("Luis");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    public Object clone(){
        List<String> temp = new ArrayList<>();
        empList.forEach(temp::add);
        return new Employees(temp);
    }
}

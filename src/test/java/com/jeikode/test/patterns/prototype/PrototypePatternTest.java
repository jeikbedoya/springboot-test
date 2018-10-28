package com.jeikode.test.patterns.prototype;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class PrototypePatternTest {

    @Test
    public void test(){
        Employees emps = new Employees();
        emps.loadData();

        Employees empsNew1 =(Employees) emps.clone();
        Employees empsNew2 = (Employees) emps.clone();

        List<String> list1 = empsNew1.getEmpList();
        list1.add("John");
        List<String> list2 = empsNew2.getEmpList();
        list2.remove("Juan");

        System.out.println("emps List: "+emps.getEmpList());
        System.out.println("empsNew1 List: "+list1);
        System.out.println("empsNew2 List: "+list2);

    }
}

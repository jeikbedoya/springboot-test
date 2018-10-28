package com.jeikode.test.patterns.factory;

import java.security.InvalidParameterException;

public class ComputerFactory {

    private static final String PC="PC";
    private static final String SERVER="PC";

    public static  Computer getComputer(String type, String ram, String hdd,
                                        String cpu){
       if(PC.equals(type)) return new PC(ram, hdd, cpu);
       else if(SERVER.equals(type)) return new Server(ram, hdd, cpu);
       else throw new InvalidParameterException("Not support type "+type);
    }
}

package com.jeikode.test.patterns.adaptor;

public class Socket {

    public Volt getVolt(){
        return new Volt(120);
    }
}

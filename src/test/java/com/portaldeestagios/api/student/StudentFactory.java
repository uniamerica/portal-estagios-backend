package com.portaldeestagios.api.student;

public class StudentFactory {

    public static  Student studentBuilderToBeSaved(){
        return Student.builder()
                .firstName("Bruno")
                .lastName("Segato")
                .age((byte) 26)
                .build();

    }

    public static  Student studentBuilderToBeUpdated(){
        return Student.builder()
                .id(1L)
                .firstName("Bruno")
                .lastName("Segato")
                .age((byte) 26)
                .build();

    }
}

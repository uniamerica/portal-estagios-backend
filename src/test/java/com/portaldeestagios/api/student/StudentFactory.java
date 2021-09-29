package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.inputdto.student.StudentInput;
import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRole;

public class StudentFactory {

    public static  Student studentBuildDefault() {
        return Student.builder()
                .firstName("Thainá")
                .lastName("Chagas")
                .age((byte) 26)
                .id(1L)
                .build();
    }

        public static  Student studentBuilderToBeSaved(){
        return Student.builder()
                .firstName("Bruno")
                .lastName("Segato")
                .age((byte) 26)
                .build();

    }

    public static StudentInput studentBuilderToBeUpdate(){
        return StudentInput.builder()
                .firstName("Thainá")
                .lastName("Weingartner")
                .age((byte) 26)
                .photo("https://images.unsplash.com/photo-1544717305-2782549b5136?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80")
                .build();

    }

    public static Student studentBuilderUpdated(){
        return Student.builder()
                .applicationUser(ApplicationUser.builder()
                        .email("test@test.com")
                        .password("teste123")
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isEnabled(true)
                        .id(1L)
                        .isCredentialsNonExpired(true)
                        .applicationUserRole(ApplicationUserRole.ROLE_ADMIN)
                        .build())
                .firstName("Thainá")
                .lastName("Weingartner")
                .age((byte) 26)
                .photo("https://images.unsplash.com/photo-1544717305-2782549b5136?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80")
                .id(1L)
                .build();
    }

    public static  Student studentWithUser() {
        return Student.builder()
                .applicationUser(ApplicationUser.builder()
                        .email("test@test.com")
                        .password("teste123")
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isEnabled(true)
                        .id(1L)
                        .isCredentialsNonExpired(true)
                        .applicationUserRole(ApplicationUserRole.ROLE_ADMIN)
                        .build())
                .firstName("Thainá")
                .lastName("Chagas")
                .age((byte) 26)
                .id(1L)
                .build();
    }
}

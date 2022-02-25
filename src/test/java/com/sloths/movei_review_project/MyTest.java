package com.sloths.movei_review_project;

import com.sloths.movei_review_project.auth.entities.User;
import com.sloths.movei_review_project.auth.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest {

    private UserRepository userDataAccess;

    @Autowired
    public void setUserDataAccess(UserRepository userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @Test
    public void shouldFindUser_successfully() {
        User user = userDataAccess.findByUsername("xeroumous");
        Assertions.assertThat(user.getUsername()).isEqualTo("xeroumous");
    }
}

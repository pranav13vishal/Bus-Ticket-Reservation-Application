package com.Pranav.RedBus.repositiryTest;

import com.Pranav.RedBus.Entity.User;
import com.Pranav.RedBus.Repository.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test cases for UserRepo")
@DataJpaTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;


    @DisplayName("Test case for save user operation")
    @Test
    public void givenUser_whenSave_thenReturnSavedUser() {
        //given
        User user = new User();
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("Password@123");
        user.setPhoneNumber(12345678900L);
        user.setRole("Admin");
        user.setAge(30);
        user.setGender("Male");
        //when
        User savedUser = userRepo.save(user);
        //then -> assertions
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
        assertThat(savedUser.getEmail()).isEqualTo("johndoe@example.com");
    }
    @Test
    @DisplayName("Test case for saving and finding user by email")
    public void givenUser_whenFindByEmail_thenReturnUser() {
        // given
        User user = new User();
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("Password@123");
        user.setPhoneNumber(12345678900L);
        user.setRole("Admin");
        user.setAge(30);
        user.setGender("Male");
        // Save user to the repository
        userRepo.save(user);
        // when
        Optional<User> retrievedUser = userRepo.findByEmail("johndoe@example.com");
        // then
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getEmail()).isEqualTo("johndoe@example.com");
    }

    @Test
    @DisplayName("Test case for saving and finding user by phone number")
    public void givenUser_whenFindByPhoneNumber_thenReturnUser() {
        // given
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("janedoe@example.com");
        user.setPassword("Password@123");
        user.setPhoneNumber(98765432100L);
        user.setRole("User");
        user.setAge(28);
        user.setGender("Female");
        // Save user to the repository
        userRepo.save(user);
        // when
        Optional<User> retrievedUser = userRepo.findByPhoneNumber(98765432100L);
        // then
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getPhoneNumber()).isEqualTo(98765432100L);
    }
}





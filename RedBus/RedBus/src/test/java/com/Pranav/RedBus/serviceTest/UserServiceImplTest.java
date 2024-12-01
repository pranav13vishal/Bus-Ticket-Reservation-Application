package com.Pranav.RedBus.serviceTest;


import com.Pranav.RedBus.Dto.UserDto;
import com.Pranav.RedBus.Entity.User;
import com.Pranav.RedBus.Repository.UserRepo;
import com.Pranav.RedBus.Service.UserServiceImpl;
import com.Pranav.RedBus.exception.Emailpresent;
import com.Pranav.RedBus.exception.PhoneNumberPresent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setEmail("test@gmail.com");
        userDto.setPassword("Password@1");
        userDto.setPhoneNumber(1234567890L);
        userDto.setName("Test User");
        userDto.setRole("Admin");
        userDto.setAge(25);
        userDto.setGender("Male");

        user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setPassword("encoded@1Password");
        user.setPhoneNumber(1234567890L);
        user.setName("Test User");
        user.setRole("Admin");
        user.setAge(25);
        user.setGender("Male");
    }

    @Test
    void givenUserDto_whenAddUser_thenReturnSavedUserDto() {
        // given
        given(userRepo.findByEmail(userDto.getEmail())).willReturn(Optional.empty());
        given(userRepo.findByPhoneNumber(userDto.getPhoneNumber())).willReturn(Optional.empty());
        given(passwordEncoder.encode(userDto.getPassword())).willReturn("encodedPassword");
        given(modelMapper.map(userDto, User.class)).willReturn(user);
        given(userRepo.save(user)).willReturn(user);
        given(modelMapper.map(user, UserDto.class)).willReturn(userDto);

        // when
        UserDto savedUser = userService.addUser(userDto);

        // then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(userDto.getEmail());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void givenExistingEmail_whenAddUser_thenThrowEmailPresentException() {
        // given
        given(userRepo.findByEmail(userDto.getEmail())).willReturn(Optional.of(user));

        // when/then
        assertThatThrownBy(() -> userService.addUser(userDto))
                .isInstanceOf(Emailpresent.class)
                .hasMessage("Email Already Present");
        verify(userRepo, never()).save(any(User.class));
    }

    @Test
    void givenExistingPhoneNumber_whenAddUser_thenThrowPhoneNumberPresentException() {
        // given
        given(userRepo.findByEmail(userDto.getEmail())).willReturn(Optional.empty());
        given(userRepo.findByPhoneNumber(userDto.getPhoneNumber())).willReturn(Optional.of(user));

        // when/then
        assertThatThrownBy(() -> userService.addUser(userDto))
                .isInstanceOf(PhoneNumberPresent.class)
                .hasMessage("PhoneNumber Already Present");
        verify(userRepo, never()).save(any(User.class));
    }

    @Test
    void givenUserDtoAndId_whenUpdateUser_thenReturnUpdatedUserDto() {
        // given
        given(userRepo.findById(user.getId())).willReturn(Optional.of(user));
        given(userRepo.save(user)).willReturn(user);
        given(modelMapper.map(user, UserDto.class)).willReturn(userDto);

        // when
        UserDto updatedUser = userService.updateUser(userDto, user.getId());

        // then
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getName()).isEqualTo(userDto.getName());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void givenUserId_whenDeleteUser_thenVerifyDeletion() {
        // given
        Long userId = 1L;

        // when
        userService.deleteUser(userId);

        // then
        verify(userRepo, times(1)).deleteById(userId);
    }

    @Test
    void givenUserId_whenViewUser_thenReturnUserDto() {
        // given
        given(userRepo.findById(user.getId())).willReturn(Optional.of(user));
        given(modelMapper.map(user, UserDto.class)).willReturn(userDto);

        // when
        UserDto fetchedUser = userService.viewUser(user.getId());

        // then
        assertThat(fetchedUser).isNotNull();
        assertThat(fetchedUser.getEmail()).isEqualTo(userDto.getEmail());
    }


    @Test
    void whenViewAllUsers_thenReturnListOfUserDtos() {
        // given
        List<User> users = List.of(user);
        List<UserDto> userDtos = List.of(userDto);

        given(userRepo.findAll()).willReturn(users);
        given(modelMapper.map(user, UserDto.class)).willReturn(userDto);

        // when
        List<UserDto> fetchedUsers = userService.viewAllUsers();

        // then
        assertThat(fetchedUsers).isNotNull();
        assertThat(fetchedUsers).hasSize(1);
        verify(userRepo, times(1)).findAll();
    }

}

package com.Pranav.RedBus.Service;

import com.Pranav.RedBus.Dto.UserDto;



import java.util.List;
import java.util.Optional;

public interface UserService {

     UserDto addUser(UserDto userDto) ;
     UserDto updateUser(UserDto userDto,Long id) ;
     void deleteUser(Long id)  ;
     UserDto viewUser(Long id)  ;
     List<UserDto> viewAllUsers()  ;
     UserDto findUserByEmail(String email)  ;


}
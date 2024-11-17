package com.example.Service;

import com.example.Entity.User;
import com.example.Payload.UserDto;
import com.example.Repository.UserRepository;
import com.example.exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    public UserService(UserRepository userRepository , ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // first Entity Object copy to dto  object
    public UserDto mapToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
    // second dto object copy to entity object
    public User mapToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public  UserDto addUser(UserDto Dto){
        User user = mapToEntity(Dto);
        user = userRepository.save(user);
        return mapToDto(user);
    }


    public UserDto updateUser( Long id ,UserDto userDto) {
    User user=mapToEntity(userDto);
    user.setId(id);
    User updatedUser=userRepository.save(user);
    return mapToDto(updatedUser);

    }

    public void deleteUser(long id) {
 userRepository.deleteById(id);

    }

    public List<UserDto> getAllUsers( int pageNo, int pageSize) {
//        List<User> users = userRepository.findAll();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<User> users = userRepository.findAll(pageable).getContent();
        return users.stream().map(this::mapToDto).toList();

    }

    public UserDto getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("User not found: "+id)
        );
        return mapToDto(user);
    }
}

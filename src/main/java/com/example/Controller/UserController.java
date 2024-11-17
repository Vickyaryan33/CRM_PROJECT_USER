package com.example.Controller;

import com.example.Payload.UserDto;
import com.example.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")  // This will be the base URL for all user-related requests
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //http://localhost:8080/api/v1/user/add
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(
            @RequestBody UserDto userDto) {
       UserDto Dto= userService.addUser(userDto);
        return new ResponseEntity<>(Dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/user/update?id=1

    @PutMapping("/update")
     public ResponseEntity<UserDto> updateUser(
             @RequestParam long id,
             @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id , userDto );
        return new  ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/user/delete?id=1
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(
            @RequestParam long id) {
     userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/user/getall?pageNo=0&pageSize=10
    @GetMapping("/getall")
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
       List<UserDto> userDto= userService.getAllUsers(pageNo, pageSize);
       return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/user/userId/1
    @GetMapping("/userId/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable long id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
}


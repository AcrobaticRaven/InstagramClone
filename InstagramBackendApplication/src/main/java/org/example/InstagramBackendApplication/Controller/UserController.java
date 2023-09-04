package org.example.InstagramBackendApplication.Controller;

import org.example.InstagramBackendApplication.Model.SignInInput;
import org.example.InstagramBackendApplication.Model.SignUpOutput;
import org.example.InstagramBackendApplication.Model.User;
import org.example.InstagramBackendApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("User/signUp")
    public SignUpOutput signUpOutput(@RequestBody User user){
      return userService.signUpOutput(user);
    }

    @PostMapping("User/signIn")
    public String signInUser(@RequestBody SignInInput signInInput){
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String signOutUser(@RequestParam String mail){
       return userService.signOutUser(mail);
    }

    @PutMapping("User")
    public String updateUserDetails(@RequestParam String email, @RequestParam String password, @RequestBody User user1) throws NoSuchAlgorithmException {
        return userService.updateUserDetails(email,password,user1);
    }

    @GetMapping("Users")
    public List<User>getAllUsers(){
        return userService.getAllUsers();
    }
}

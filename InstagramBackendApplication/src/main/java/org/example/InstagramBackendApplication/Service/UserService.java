package org.example.InstagramBackendApplication.Service;

import org.example.InstagramBackendApplication.Model.*;
import org.example.InstagramBackendApplication.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;

    public SignUpOutput signUpOutput(User user){

         String signUpStatusMessage = null;
         boolean signUpStatus = true;

         String mail = user.getUserMail();
         if(mail == null){
             signUpStatusMessage = "Invalid email";
             signUpStatus = false;
             return new SignUpOutput(signUpStatusMessage,signUpStatus);
         }
         User existingUser = userRepo.findFirstByUserMail(mail);

         if(existingUser != null){
             signUpStatusMessage = "User already registered";
             signUpStatus = false;
             return new SignUpOutput(signUpStatusMessage,signUpStatus);
         }

         //hashing the password: encrypt the password
try {
    String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
    user.setUserPassword(encryptedPassword);
    userRepo.save(user);

    return new SignUpOutput( "User registered successfully",signUpStatus);
}catch(Exception e){
    signUpStatusMessage = "Error occured during signingUp";
    signUpStatus = false;
    return new SignUpOutput(signUpStatusMessage,signUpStatus);
}
    }





    public String signInUser(SignInInput signInInput){
        String signInStatusMessage = null;
        String mail = signInInput.getEmail();

        if(mail==null){
            signInStatusMessage= "Invalid email";
            return signInStatusMessage;
        }
        User existingUser = userRepo.findFirstByUserMail(mail);
        if(existingUser==null){
            signInStatusMessage = "Email not registered";
            return signInStatusMessage;
        }

        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword((signInInput.getPassword()));
            if (encryptedPassword.equals(existingUser.getUserPassword())) {

                //session should be created since password matched and id is valid

                AuthenticationToken authToken = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(authToken);
                return authToken.toString();
            } else {
                signInStatusMessage = "Invalid credentials";
                return signInStatusMessage;
            }
        }catch(Exception e){
            signInStatusMessage = "Error occured during Sign In";
            return signInStatusMessage;
        }
    }


    public String signOutUser(String email){
        User user = userRepo.findFirstByUserMail(email);
        AuthenticationToken authToken = authenticationService.findFirstByUser(user);
        authenticationService.removeToken(authToken);
        return "User signed out successfully";
    }

    public String updateUserDetails(String email, String password, User user1) throws NoSuchAlgorithmException {
        User emailConnectedUser = userRepo.findFirstByUserMail(email);
        String connectedPassword = emailConnectedUser.getUserPassword();
        String encryptedPassword = PasswordEncrypter.encryptPassword(password);
        if(password == null){
            return "Invalid password";
        }
        if(!(connectedPassword.equals(encryptedPassword))){
            return "Incorrect credentials";
        }else{
            Optional<User>userOptional = userRepo.findById(user1.getUserId());
            if(userOptional.isPresent()){
                User user = userOptional.get();
                user.setUserAge(user1.getUserAge());
                user.setUserMail(user1.getUserMail());
                user.setUserNumber(user1.getUserNumber());
                user.setUserFirstName(user1.getUserFirstName());
                user.setUserLastName(user1.getUserLastName());
                user.setUserPassword(user1.getUserPassword());
                userRepo.save(user);
                return "User has been updated";
            }return "User not found";
        }
    }

    public List<User>getAllUsers(){
        return userRepo.findAll();
    }






}

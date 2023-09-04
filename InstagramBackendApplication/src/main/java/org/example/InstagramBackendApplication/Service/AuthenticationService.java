package org.example.InstagramBackendApplication.Service;

import org.example.InstagramBackendApplication.Model.AuthenticationToken;
import org.example.InstagramBackendApplication.Model.User;
import org.example.InstagramBackendApplication.Repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo authenticationRepo;

    public void saveAuthToken(AuthenticationToken token){
        authenticationRepo.save(token);
    }

    public AuthenticationToken findFirstByUser(User user) {
        return authenticationRepo.findFirstByUser(user);
    }

    public void removeToken(AuthenticationToken authToken) {
        authenticationRepo.delete(authToken);
    }

    public boolean authenticate(String email, String token){
        AuthenticationToken authToken = authenticationRepo.findFirstByToken(token);
        String tokenConnectedMail = authToken.getUser().getUserMail();
        if(tokenConnectedMail == null){
            return false;
        }return tokenConnectedMail.equals(email);
    }
}

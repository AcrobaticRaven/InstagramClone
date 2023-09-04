package org.example.InstagramBackendApplication.Repository;

import org.example.InstagramBackendApplication.Model.AuthenticationToken;
import org.example.InstagramBackendApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {
     AuthenticationToken findFirstByUser(User user) ;

     AuthenticationToken findFirstByToken(String token);
}

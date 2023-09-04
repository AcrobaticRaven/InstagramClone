package org.example.InstagramBackendApplication.Repository;

import org.example.InstagramBackendApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserMail(String mail);
}

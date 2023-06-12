package com.supauction.auction_supmti.Repository;

import com.supauction.auction_supmti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    org.springframework.security.core.userdetails.User findByEmail(String email);

    User findByUsername(String username);
}

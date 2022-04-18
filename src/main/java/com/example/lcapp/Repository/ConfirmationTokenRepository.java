package com.example.lcapp.Repository;

import com.example.lcapp.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    ConfirmationToken findByToken(String token);

    void removeConfirmationTokenByToken(String token);
}

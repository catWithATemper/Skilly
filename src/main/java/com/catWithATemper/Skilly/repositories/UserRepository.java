package com.catWithATemper.Skilly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.catWithATemper.Skilly.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

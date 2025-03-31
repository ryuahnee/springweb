package com.demo.todoapps.restapi.data;

import com.demo.todoapps.restapi.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}

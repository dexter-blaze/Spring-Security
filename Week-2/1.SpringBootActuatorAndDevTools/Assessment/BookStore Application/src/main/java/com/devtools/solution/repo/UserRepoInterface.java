package com.devtools.solution.repo;

import com.devtools.solution.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoInterface extends JpaRepository<User, Integer> {

}

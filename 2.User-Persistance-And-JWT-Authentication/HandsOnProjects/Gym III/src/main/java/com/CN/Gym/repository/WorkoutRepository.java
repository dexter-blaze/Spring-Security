package com.CN.Gym.repository;

import com.CN.Gym.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}

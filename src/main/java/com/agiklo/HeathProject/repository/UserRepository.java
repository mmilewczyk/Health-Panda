package com.agiklo.HeathProject.repository;

import com.agiklo.HeathProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}

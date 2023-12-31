package com.example.auistar.repository;


import com.example.auistar.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface StarRepository extends JpaRepository<Star, UUID> {
    List<Star> findAllByAge(int age);
    Optional<Star> findByName(String name);

}

package com.restaurant.management.repository;


import com.restaurant.management.Logindomain.Amenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenuRepository extends JpaRepository<Amenu, Long> {

}

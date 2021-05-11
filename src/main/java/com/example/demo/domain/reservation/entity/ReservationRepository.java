package com.example.demo.domain.reservation.entity;

import com.example.demo.domain.reservation.entity.ReservationPassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationPassenger, Long> {

}

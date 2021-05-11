package com.example.demo.domain.reservation;

import com.example.demo.domain.customer.entity.Customer;
import com.example.demo.domain.customer.repo.CustomerRepository;
import com.example.demo.domain.reservation.dto.ReservationDto;
import com.example.demo.domain.reservation.entity.ReservationPassenger;
import com.example.demo.domain.reservation.entity.ReservationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;

    public void createReservation(ReservationDto.CreateReq req) {
        //Case1. id로 repository에서 select 한 값을 주입 받아 entity에 주입
        Customer customer = customerRepository.findById(req.getCustomerId()).orElseThrow(IllegalArgumentException::new);
        ReservationPassenger reservationPassenger = req.toEntity(customer);
        ReservationPassenger saved = reservationRepository.save(reservationPassenger);

        //Case2. id로 customer id 객체 생성 후 entity 에 주입
        ReservationPassenger reservationPassenger2 = req.toEntity();
        ReservationPassenger saved2 = reservationRepository.save(reservationPassenger2);

    }
}

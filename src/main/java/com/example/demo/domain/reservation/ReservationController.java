package com.example.demo.domain.reservation;

import com.example.demo.domain.reservation.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public void createReservation(@RequestBody ReservationDto.CreateReq req) {

        reservationService.createReservation(req);
    }
}

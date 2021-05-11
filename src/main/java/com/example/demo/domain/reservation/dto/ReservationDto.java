package com.example.demo.domain.reservation.dto;

import com.example.demo.domain.customer.entity.Customer;
import com.example.demo.domain.reservation.constant.PassengerType;
import com.example.demo.domain.reservation.entity.ReservationPassenger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationDto {

    @Getter
    @NoArgsConstructor
    public static class CreateReq {
        private Long customerId;
        private PassengerType passengerType;
        private String usages;
        private String luggage;

        //Case1. id로 repository에서 select 한 값을 주입 받아 entity에 주입
        public ReservationPassenger toEntity(Customer customer) {
            ReservationPassenger reservationPassenger = ReservationDtoMapper.INSTANCE.toReservationPassengerEntity(this);
            reservationPassenger.setCustomer(customer);
            return reservationPassenger;
        }

        //Case2. id로 customer id 객체 생성 후 entity 에 주입
        public ReservationPassenger toEntity() {
            Customer customer = new Customer(this.customerId);

            ReservationPassenger reservationPassenger = ReservationDtoMapper.INSTANCE.toReservationPassengerEntity(this);
            reservationPassenger.setCustomer(customer);
            return reservationPassenger;
        }

    }
}

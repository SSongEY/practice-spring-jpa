package com.example.demo.domain.reservation.entity;


import com.example.demo.domain.customer.entity.Customer;
import com.example.demo.domain.reservation.constant.PassengerType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationPassenger {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 승객 타입 (예약자/동승자)
    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Length(max = 32)
    // 고객 안심번호
    private String safetyMobileNo;

    @Length(max = 32)
    // 보호자 안심번호
    private String safetyFamilyMobileNo;

    // 용도
    @Length(max = 256)
    @Column(nullable = false)
    private String usages;

    // 수하물
    @Length(max = 32)
    @Column(nullable = false)
    private String luggage;

    // 예약안내문자발송여부
    @Column(nullable = false)
    private boolean sendSmsToCustomer;

    // 예약안내푸시발송여부
    @Column(nullable = false)
    private boolean sendPushToCustomer;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

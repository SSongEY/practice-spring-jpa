package com.example.demo.domain.customer.entity;

import com.example.demo.domain.common.Address;
import com.example.demo.domain.common.Period;
import com.example.demo.domain.coupon.entity.CustomerCoupon;
import com.example.demo.domain.customer.constant.CustomerGender;
import com.example.demo.domain.customer.constant.CustomerType;
import com.example.demo.domain.customer.dto.CustomerDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CustomerGender gender;

    @Enumerated(EnumType.STRING)
    private CustomerType type;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MobilityWeakInfo mobilityWeakInfo;

    @Embedded
    private Period workPeriod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column=@Column(name = "work_city")),
            @AttributeOverride(name="street", column=@Column(name = "work_street")),
            @AttributeOverride(name="zipcode", column=@Column(name = "work_zipcode"))
    })
    private Address workAddress;

    @Embedded
    private Address homeAddress;

    @OneToMany(mappedBy = "customer")
    private List<CustomerCoupon> customerCoupons = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    public void updateInfo(CustomerDto.UpdateReq req) {
        this.gender = CustomerGender.valueOf(req.getGender());
        this.type = CustomerType.valueOf(req.getType());
    }
}

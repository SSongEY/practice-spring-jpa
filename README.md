# 

- customer entity
```java
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
```

- mobileity weak entity
```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public class MobilityWeakInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    protected Customer customer;


    @CreationTimestamp
    LocalDateTime created;

    @UpdateTimestamp
    LocalDateTime updated;

    public MobilityWeakInfo(Customer customer) {
        this.customer = customer;
    }
}
```

- pragnent entity
```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Pregnant")
public class Pregnant extends MobilityWeakInfo {

    private LocalDate babyDue;

    @Builder
    public Pregnant(Customer customer, LocalDate babyDue) {
        super(customer);
        this.babyDue = babyDue;
    }
}
```

- disabled entity
```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Disabled")
public class Disabled extends MobilityWeakInfo {

    private Boolean isCommunicable;
    private String handicapInfo;


    public Disabled(Customer customer, Boolean isCommunicable, String handicapInfo) {
        super(customer);
        this.isCommunicable = isCommunicable;
        this.handicapInfo = handicapInfo;
    }
}
```



- coupon entity
```java
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "coupon")
    private List<CustomerCoupon> customerCoupons = new ArrayList<>();

}
```

- customer coupon mapping entity
```java@Entity
public class CustomerCoupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
}
```


- embeddeable class
```java
@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor @Builder
public class Address {
    private String city;
    private String street;
    private String zipcode;
}

@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Period {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public boolean isWorking() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(this.startDateTime) && now.isBefore(this.endDateTime);
    }
}
```

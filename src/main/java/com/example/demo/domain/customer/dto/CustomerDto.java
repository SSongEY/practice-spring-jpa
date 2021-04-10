package com.example.demo.domain.customer.dto;

import com.example.demo.domain.customer.entity.Customer;
import com.example.demo.domain.customer.entity.Disabled;
import com.example.demo.domain.customer.entity.MobilityWeakInfo;
import com.example.demo.domain.customer.entity.Pregnant;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateReq {
        @NotBlank
        private String name;
        @NotBlank
        private String gender;
        @NotBlank
        private String type;

        private PregnantInfo pregnantInfo;
        private DisabledInfo disabledInfo;


        public Customer toEntity() {
            return CustomerDtoMapper.INSTANCE.toCustomerFromCreateReq(this);
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UpdateReq {
        @NotBlank
        private String gender;
        @NotBlank
        private String type;

        private PregnantInfo pregnantInfo;
        private DisabledInfo disabledInfo;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor @Builder
    public static class ListRes {
        private Long id;
        private String name;
        private String gender;
        private String type;
        private LocalDateTime created;
        private LocalDateTime updated;

        public static ListRes of(Customer customer) {
            return CustomerDtoMapper.INSTANCE.toListResFromCustomer(customer);
        }
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor @Builder
    public static class DetailRes {
        private Long id;
        private String name;
        private String gender;
        private String type;
        private LocalDateTime created;
        private LocalDateTime updated;

        private PregnantInfo pregnantInfo;
        private DisabledInfo disabledInfo;

        public void setPregnantInfo(PregnantInfo pregnantInfo) {
            this.pregnantInfo = pregnantInfo;
        }

        public void setDisabledInfo(DisabledInfo disabledInfo) {
            this.disabledInfo = disabledInfo;
        }

        public static DetailRes of(Customer customer) {
            DetailRes detailRes = CustomerDtoMapper.INSTANCE.toDetailResFromCustomer(customer);

            MobilityWeakInfo mobilityWeakInfo = customer.getMobilityWeakInfo();
            if(mobilityWeakInfo != null) {
                if(mobilityWeakInfo instanceof Pregnant) {
                    detailRes.setPregnantInfo(new PregnantInfo(((Pregnant) mobilityWeakInfo).getBabyDue()));
                } else if(mobilityWeakInfo instanceof Disabled) {
                    Disabled disabled = (Disabled)mobilityWeakInfo;
                    detailRes.setDisabledInfo(new DisabledInfo(disabled.getIsCommunicable(), disabled.getHandicapInfo()));
                }
            }
            return detailRes;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class PregnantInfo {
        @NotNull
        private LocalDate babyDue;

        public Pregnant toEntity(Customer customer) {
            return CustomerDtoMapper.INSTANCE.toPregnant(this, customer);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class DisabledInfo {
        @NotNull
        private Boolean isCommunicable;
        @NotBlank
        private String handicapInfo;

        public Disabled toEntity(Customer customer) {
            return CustomerDtoMapper.INSTANCE.toDisabled(this, customer);
        }
    }
}

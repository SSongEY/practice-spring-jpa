package com.example.demo.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

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

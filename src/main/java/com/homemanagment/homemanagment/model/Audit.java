package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Objects;

@Embeddable
@Getter
class Audit {
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
    @PrePersist
    public void prePersist(){
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        dTF.format(localDateTime);
        createOn = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        dTF.format(localDateTime);
        updateOn = LocalDateTime.now();
    }
}

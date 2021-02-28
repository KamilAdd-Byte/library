package com.homemanagment.homemanagment.model;

import lombok.Getter;
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
    private LocalDate createOn;
    private LocalDate updateOn;
    @PrePersist
    public void prePersist(){
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        dTF.format(localDateTime);
        createOn = LocalDateTime.now().toLocalDate();
    }
    @PreUpdate
    public void preUpdate(){
        updateOn = LocalDate.now();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Audit)) return false;
        Audit audit = (Audit) o;
        return Objects.equals(createOn, audit.createOn) && Objects.equals(updateOn, audit.updateOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createOn, updateOn);
    }
}

package com.homemanagment.homemanagment.model;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@Getter
class Audit {
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
    @PrePersist
    public void prePersist(){
        createOn = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        updateOn = LocalDateTime.now();
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

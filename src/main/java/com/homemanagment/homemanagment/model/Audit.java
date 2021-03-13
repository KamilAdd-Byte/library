package com.homemanagment.homemanagment.model;

import lombok.Getter;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.*;

@Embeddable
@Getter
public
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
}

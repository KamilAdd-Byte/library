package com.homemanagment.homemanagment.model;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class Audit {
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

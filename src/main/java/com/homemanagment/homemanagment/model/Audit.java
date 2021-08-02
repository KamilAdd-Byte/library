package com.homemanagment.homemanagment.model;

import lombok.Getter;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.*;
import java.util.Date;

@Embeddable
@Getter
public
class Audit {
    private Date createOn;
    private Date updateOn;

    @PrePersist
    public void prePersist(){
        createOn = new Date();
    }
    @PreUpdate
    public void preUpdate(){
        updateOn = new Date();
    }
}

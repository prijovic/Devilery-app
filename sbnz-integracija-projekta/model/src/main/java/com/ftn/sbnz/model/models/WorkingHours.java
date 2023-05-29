package com.ftn.sbnz.model.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Time;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkingHours {
    @Column(name = "opensAt")
    Time opensAt;

    @Column(name = "closesAt")
    Time closesAt;
}

package com.ftn.sbnz.model.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LevelsFired {
    Boolean first;
    Boolean second;
    Boolean third;
    Boolean fourth;
    Boolean fifth;
    Boolean sixth;
}

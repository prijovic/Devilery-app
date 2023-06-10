package com.ftn.sbnz.model.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.kie.api.definition.type.Position;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Link {
    @Position(0)
    String word;
    @Position(1)
    String container;
}

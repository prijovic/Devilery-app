package com.ftn.sbnz.model.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import javax.persistence.*;
import java.util.Date;

@Role(Role.Type.EVENT)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report")
@Entity
@SuperBuilder
@Timestamp("createdOn")
public class Report extends BaseEntity {
    @Column(name = "createdOn", nullable = false)
    Date createdOn;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    ReportStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    User customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deliverer_id")
    Deliverer deliverer;

    @Column(name = "comment", nullable = false)
    String comment;
}

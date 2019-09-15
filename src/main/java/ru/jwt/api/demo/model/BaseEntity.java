package ru.jwt.api.demo.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedBy
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

}

package org.sm.entitygraph.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@Entity(name="sub_status")
public class SubStatusEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String desc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id", nullable=false,insertable = false,updatable = false)
    private StatusEntity status;
}

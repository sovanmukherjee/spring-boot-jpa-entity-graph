package org.sm.entitygraph.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.sm.entitygraph.constants.EntityGraphNames;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NamedEntityGraph( name = EntityGraphNames.USER_STATUS,
    attributeNodes = {
        @NamedAttributeNode(value="status", subgraph = "status-subgraph"),
        @NamedAttributeNode("address"),
    },
    subgraphs = {
        @NamedSubgraph(
            name = "status-subgraph",
            attributeNodes = {
                    @NamedAttributeNode("subStatuses")
            }
        )
})

@NamedEntityGraph( name = EntityGraphNames.USER_DETAILS,
        attributeNodes = {
                @NamedAttributeNode("address")
})

@Getter
@Setter
@Entity(name="user")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String roll;
    @Column(name="class_name")
    private String className;

    @Column(name="status")
    private int statusRef;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "status")
    private List<StatusEntity> status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    private AddressEntity address;
}

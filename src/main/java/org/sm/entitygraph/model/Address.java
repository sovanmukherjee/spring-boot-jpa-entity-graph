package org.sm.entitygraph.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class Address {
    private int id;
    private String state;
    private String city;
    private String pin;
}

package org.sm.entitygraph.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class User {
    private int id;
    private String name;
    private String roll;
    private List<Status> status;
    private Address address;
}

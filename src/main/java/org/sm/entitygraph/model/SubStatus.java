package org.sm.entitygraph.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SubStatus {
    private int id;
    private String code;
    private String desc;
}

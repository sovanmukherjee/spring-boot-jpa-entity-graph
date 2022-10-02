package org.sm.entitygraph.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Status {
    private int id;
    private String code;
    private String desc;
    private List<SubStatus> subStatuses;
}

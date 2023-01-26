package com.epam.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Topic {
    private static final long serialVersionUID = 1L;
    long id;
    private String name;
}

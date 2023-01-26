package com.epam.model.entities;

import com.epam.model.entities.enums.PublicationStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
public class Publication implements Serializable {
    private static final long serialVersionUID = 1L;
    private String index;
    private String name;
    private String description;
    private String language;
    private BigDecimal price;
    private String imgLink;
    private long topicId;
    private String topicName;
    private PublicationStatus publicationStatus;

}

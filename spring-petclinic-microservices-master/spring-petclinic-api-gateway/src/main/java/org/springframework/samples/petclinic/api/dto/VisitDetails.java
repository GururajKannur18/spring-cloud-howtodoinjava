package org.springframework.samples.petclinic.api.dto;

import lombok.Value;


@Value
public class VisitDetails {

    private int id;

    private int petId;

    private String date;

    private String description;
}

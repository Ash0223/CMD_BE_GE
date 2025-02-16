package com.tg.Doctor.models;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
public class Clinic {

    @JsonProperty("id")
	private String clinicId;

    private Boolean clinicStatus;

}

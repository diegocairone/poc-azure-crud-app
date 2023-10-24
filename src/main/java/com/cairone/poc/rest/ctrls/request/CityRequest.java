package com.cairone.poc.rest.ctrls.request;

import com.cairone.poc.core.forms.CityForm;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityRequest implements CityForm {

    @NotBlank
    private String name;
}

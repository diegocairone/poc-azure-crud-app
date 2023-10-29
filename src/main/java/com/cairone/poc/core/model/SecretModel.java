package com.cairone.poc.core.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder(setterPrefix = "with")
public class SecretModel {

    @EqualsAndHashCode.Include
    private final String id;
    private final String name;
    private final String value;

}

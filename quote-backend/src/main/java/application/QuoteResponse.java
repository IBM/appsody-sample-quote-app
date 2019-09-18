package application;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuoteResponse {

    @JsonProperty
    private int quotedAmount;

    @JsonProperty
    private String basis;

}
package application;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HealthScoreResponse {

    @JsonProperty
    private int scr;

}
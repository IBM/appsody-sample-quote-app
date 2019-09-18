package application;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HealthScoreResponse {

    @JsonProperty
    private int scr;

    @JsonProperty
    private Map<String,Object> subscores;

}
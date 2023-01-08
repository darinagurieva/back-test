package lesson4.request;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "day",
        "slot",
        "position",
        "type",
        "value"
})
@Data
public class Item {

    @JsonProperty("day")
    public Integer day;
    @JsonProperty("slot")
    public Integer slot;
    @JsonProperty("position")
    public Integer position;
    @JsonProperty("type")
    public String type;
    @JsonProperty("value")
    public Value value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
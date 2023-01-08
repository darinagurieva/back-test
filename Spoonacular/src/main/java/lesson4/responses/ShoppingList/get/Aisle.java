package lesson4.responses.ShoppingList.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisle",
        "items"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aisle {

    @JsonProperty("aisle")
    public String aisle;
    @JsonProperty("items")
    public List<Item> items = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
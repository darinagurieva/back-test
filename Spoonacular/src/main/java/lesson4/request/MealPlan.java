package lesson4.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MealPlan {

    @JsonProperty("name")
    public String name;
    @JsonProperty("items")
    public List<Item> items = null;
    @JsonProperty("publishAsPublic")
    public Boolean publishAsPublic;
    @JsonProperty("item")
    public String item;
    @JsonProperty("aisle")
    public String aisle;
    @JsonProperty("parse")
    public Boolean parse;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
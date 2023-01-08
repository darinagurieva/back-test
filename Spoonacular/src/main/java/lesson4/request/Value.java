package lesson4.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "servings",
        "title",
        "imageType",
        "image",
        "ingredients"
})
@Data
public class Value {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("servings")
    public Integer servings;
    @JsonProperty("title")
    public String title;
    @JsonProperty("imageType")
    public String imageType;
    @JsonProperty("image")
    public String image;
    @JsonProperty("ingredients")
    public List<Ingredient> ingredients = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



}
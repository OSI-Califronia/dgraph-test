package de.mbausch.dgraph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonArray;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Box implements IAsset {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("gatewayName")
    private String gatewayName;

    @JsonProperty("providerName")
    private String providerName;

    @JsonIgnore
    private Set<String> recorderTypes;

    @JsonProperty("recorderTypes")
    public void setRecorderTypesAsJsonString(final String jsonString) {
        recorderTypes = new JsonArray(jsonString).stream().map(value -> (String) value).collect(Collectors.toSet());
    }

    @JsonProperty("recorderTypes")
    public String getRecorderTypesAsJsonString() {
        JsonArray jsonArray = new JsonArray();
        recorderTypes.forEach(jsonArray::add);
        return jsonArray.encode();
    }
}

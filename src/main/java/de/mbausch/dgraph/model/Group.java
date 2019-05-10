package de.mbausch.dgraph.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Group implements IAsset {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}

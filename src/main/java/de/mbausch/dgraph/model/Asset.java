package de.mbausch.dgraph.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Asset implements IAsset {

    @JsonProperty("id")
    private Integer id;

    private Set<Box> boxes;

    private Set<Group> groups;
}

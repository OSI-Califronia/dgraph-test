package de.mbausch.dgraph.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "label", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Asset.class, name = "asset"),
        @JsonSubTypes.Type(value = Box.class, name = "box"),
        @JsonSubTypes.Type(value = Group.class, name = "group")}
)
public interface IAsset {

}

package de.mbausch.dgraph.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AssetTest {

    @Test
    void testSerialize() throws JsonProcessingException {
        Asset asset = new Asset();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(asset);
        assertNotNull(json);
    }
}
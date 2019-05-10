package de.mbausch.dgraph.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redislabs.redisgraph.Record;
import com.redislabs.redisgraph.RedisGraphAPI;
import com.redislabs.redisgraph.ResultSet;
import de.mbausch.dgraph.model.IAsset;
import io.vertx.core.json.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AssetManager {

    private final RedisGraphAPI api;
    private final ObjectMapper mapper;

    public List<IAsset> findForUser() {
        List<IAsset> resultList = new ArrayList<>();

        ResultSet resultSet = api.query("MATCH (b:box) RETURN labels(b) AS label, b  ");
//        ResultSet resultSet = api.query("MATCH (b:box {id:1}) RETURN labels(b) AS label, b  ");

//                ResultSet resultSet =  api.query("MATCH (b:box)<-[rb:assigned]-(a:asset)<-[rg:contains]-(g:group) WHERE a.id = 1 RETURN a, b,g ");

        resultSet.getHeader().forEach(log::info);
        while(resultSet.hasNext()){
            Record record = resultSet.next();

            log.info("raw: " + resultSet.getHeader().stream().map(record::getValue).collect(Collectors.toList()));

            // map to object.
            JsonObject json = new JsonObject();
            resultSet.getHeader().forEach(header -> {
               Object object = record.getValue(header);
               json.put(mapHeader(header), object);
            });
            try {
                Optional.ofNullable(mapper.readValue(json.encode(), IAsset.class)).ifPresent(resultList::add);
            } catch (IOException e) {
                log.warn("nix mit gut", e);
            }
        }

        return resultList;
    }

    private String mapHeader(final String header) {
        if (header.contains(".")) {
            return header.split("\\.")[1];
        }
        return header;
    }
}

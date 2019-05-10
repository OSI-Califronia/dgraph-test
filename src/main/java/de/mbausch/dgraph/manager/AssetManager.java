package de.mbausch.dgraph.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mbausch.dgraph.model.IAsset;
import io.dgraph.DgraphClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AssetManager {

    private final DgraphClient dgraphClient;
    private final ObjectMapper mapper;

    public List<IAsset> findForUser() {
        List<IAsset> resultList = new ArrayList<>();



        return resultList;
    }


}

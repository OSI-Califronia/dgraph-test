package de.mbausch.dgraph.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redislabs.redisgraph.RedisGraphAPI;
import de.mbausch.dgraph.manager.AssetManager;
import de.mbausch.dgraph.model.IAsset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AssetManagerTest {

    private RedisGraphAPI api = new RedisGraphAPI("assets");

    private ObjectMapper mapper = new ObjectMapper();

    private AssetManager assetManager;

    @BeforeEach
    void setUp(){
        assetManager = new AssetManager(api, mapper);

        initGraph();
    }

    @AfterEach
    void tearDown() {
        api.deleteGraph();
    }

    private void initGraph() {
        api.query("CREATE (:group{id:200, name:'Group' })");

        api.query("CREATE (:asset{id:1})");
        api.query("CREATE (:asset{id:2})");
        api.query("CREATE (:asset{id:3})");

        api.query("CREATE (:box{id:100, externalId:'ext123',  recorderTypes:'[\"IBOX\", \"VANLOXX\"]'})");
        api.query("CREATE (:box{id:101, externalId:'extA1'})");
        api.query("CREATE (:box{id:102, externalId:'ext346'})");
        api.query("CREATE (:box{id:103, externalId:'extUiVh12' })");

        api.query("MATCH (g:group),(a:asset) WHERE g.id = 200 AND a.id = 1 CREATE (g)-[r:contains]->(a)");
        api.query("MATCH (g:group),(a:asset) WHERE g.id = 200 AND a.id = 2 CREATE (g)-[r:contains]->(a)");

        api.query("MATCH (a:asset),(b:box) WHERE a.id = 1 AND b.id = 100 CREATE (a)-[r:assigned]->(b)");
        api.query("MATCH (a:asset),(b:box) WHERE a.id = 2 AND b.id = 101 CREATE (a)-[r:assigned]->(b)");
        api.query("MATCH (a:asset),(b:box) WHERE a.id = 3 AND b.id = 102 CREATE (a)-[r:assigned]->(b)");
        api.query("MATCH (a:asset),(b:box) WHERE a.id = 3 AND b.id = 103 CREATE (a)-[r:assigned]->(b)");
    }

    @Test
    void testFindForUser() {
        List<IAsset> resultList = assetManager.findForUser();


    }
}
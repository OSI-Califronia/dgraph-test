package de.mbausch.dgraph;

import com.redislabs.redisgraph.Record;
import com.redislabs.redisgraph.RedisGraphAPI;
import com.redislabs.redisgraph.ResultSet;

import java.util.stream.Collectors;

public class AssetTest {

    public static void main(String[] args) {

        RedisGraphAPI api = new RedisGraphAPI("assets");
//        api.deleteGraph();

//        api.query("CREATE (:group{id:200, name:'Group' })");
//
//        api.query("CREATE (:asset{id:1, name:'Asset 1'})");
//        api.query("CREATE (:asset{id:2, name:'Asset 2'})");
//        api.query("CREATE (:asset{id:3, name:'Asset 3'})");
//
//        api.query("CREATE (:box{id:100, name:'Box 100', externalId:'ext123'})");
//        api.query("CREATE (:box{id:101, name:'Box 101', externalId:'extA1'})");
//        api.query("CREATE (:box{id:102, name:'Box 102', externalId:'ext346'})");
//        api.query("CREATE (:box{id:103, name:'Box 103', externalId:'extUiVh12' })");
//
//        api.query("MATCH (g:group),(a:asset) WHERE g.id = 200 AND a.id = 1 CREATE (g)-[r:contains]->(a)");
//        api.query("MATCH (g:group),(a:asset) WHERE g.id = 200 AND a.id = 2 CREATE (g)-[r:contains]->(a)");
//
//        api.query("MATCH (a:asset),(b:box) WHERE a.id = 1 AND b.id = 100 CREATE (a)-[r:assigned]->(b)");
//        api.query("MATCH (a:asset),(b:box) WHERE a.id = 2 AND b.id = 101 CREATE (a)-[r:assigned]->(b)");
//        api.query("MATCH (a:asset),(b:box) WHERE a.id = 3 AND b.id = 102 CREATE (a)-[r:assigned]->(b)");
//        api.query("MATCH (a:asset),(b:box) WHERE a.id = 3 AND b.id = 103 CREATE (a)-[r:assigned]->(b)");
//
////        ResultSet resultSet =  api.query("MATCH (a:box) RETURN a"); select all boxes
        ResultSet resultSet =  api.query("MATCH (b:box)<-[rb:assigned]-(a:asset)<-[rg:contains]-(g:group) WHERE a.id = 1 RETURN a, b,g ");
//        ResultSet resultSet =  api.query("MATCH (a:asset)-[rg:contains]->(g:group) WHERE a.id = 3 RETURN a, b,g ");

        resultSet.getHeader().forEach(System.out::println);
        while(resultSet.hasNext()){
            Record record = resultSet.next();
            System.out.println(resultSet.getHeader().stream().map(record::getString).collect(Collectors.toList()));
        }

//        MATCH (a:Person),(b:Person)
//WHERE a.name = 'A' AND b.name = 'B'
//CREATE (a)-[r:RELTYPE]->(b)
//RETURN type(r)

//        api.query("CREATE (:person{name:%s,age:%d})", "amit", 30);

//        api.query("MATCH (a:person), (b:person) WHERE (a.name = 'roi' AND b.name='amit') CREATE (a)-[:knows]->(b)");

//        ResultSet resultSet = api.query("MATCH (a:person)-[:knows]->(b:person) RETURN a, b");

//        while(resultSet.hasNext()){
//            Record record = resultSet.next();
//            System.out.println(record.getString("a.name"));
//        }

    }
}

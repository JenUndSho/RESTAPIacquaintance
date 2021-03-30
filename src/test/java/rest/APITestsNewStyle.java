package rest;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class APITestsNewStyle {

    @Test
    public void getMethodOnPage(){

        baseURI = "https://reqres.in";

        given().
            get("/api/users?page=2").
        then().
            statusCode(200).log().all();

    }

    @Test
    public void getMethodReturnsDataWithId(){

        baseURI = "https://reqres.in";

        given().
           get("/api/users?page=2").
        then().
           statusCode(200).
           body("data[1].id", equalTo(8))
           .log().all();

    }

    @Test
    public void testGet(){

        baseURI = "https://reqres.in";

        given().
          get("/api/users?page=2").
        then().
          statusCode(200).
          body("data[3].first_name", equalTo("Byron")).
          body("data.last_name", hasItems("Fields", "Funke"))
          .log().all();

    }

    @Test
    public void testPost(){

    //    Map<String, Object> map = new HashMap<>();
    //    map.put("\"name\"", "\"Evhen\"");
    //    map.put("\"job\"", "\"CJ & Rider\"");
    //    System.out.println(map);

        JSONObject request = new JSONObject();
        request.put("name", "Evhen");
        request.put("job", "some Job");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in";

        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
        when().
                post("/api/users").
        then().
                statusCode(201)
                .log().all();
    }

    @Test
    public void testPut(){
        JSONObject request = new JSONObject();
        request.put("name", "MC Ren");
        request.put("job", "Rapper");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in";

        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
        when().
                put("/api/users/3").
        then().
                statusCode(200)
                .log().all();
    }

    @Test
    public void testPatch(){
        JSONObject request = new JSONObject();
        request.put("name", "Ice Cube");
      //  request.put("job", "Rapper");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in";

        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
        when().
                patch("/api/users/3").
        then().
                statusCode(200)
                .log().all();
    }


    @Test
    public void testDelete(){

        baseURI = "https://reqres.in";

        when().
                delete("/api/users/3").
        then().
                statusCode(204)
                .log().all();
    }

    @Test
    public void testFakeAPIGet(){
        baseURI = "http://localhost:3000";

        given().
                get("/users").
        then().
                statusCode(200).log().all();

    }

    @Test
    public void testFakeAPIPost(){
        baseURI = "http://localhost:3000";

        JSONObject request = new JSONObject();
        request.put("first_name", "Cristiano");
        request.put("last_name", "Ronaldo");
        request.put("occupationId", 2);

        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
        when().
                post("/users").
        then().
                statusCode(201).log().all();
    }

    @Test
    public void testFakeAPIPut(){
        baseURI = "http://localhost:3000";

        JSONObject request = new JSONObject();
        request.put("first_name", "Lionel");
        request.put("last_name", "Messi");


        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
        when().
                put("/users/2").
        then().
                statusCode(200).log().all();

    }

    @Test
    public void testFakeAPIPatch(){
        baseURI = "http://localhost:3000";

        JSONObject request = new JSONObject();
        request.put("first_name", "Roma");

        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
        when().
                patch("/users/1").
        then().
                statusCode(200).log().all();

    }

    @Test
    public void testFakeAPIDelete(){
        baseURI = "http://localhost:3000";

        when().
                delete("/users/2").
        then().
                statusCode(200).log().all();

    }
}

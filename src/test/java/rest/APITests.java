package rest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class APITests {

    @Test
    public void getKievWeather(){

        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Kiev");

        System.out.println("Response body =>\n" + response.asString());

    }


    @Test
    public void checkKievWeatherResponse(){

        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Kiev");

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);


        // System.out.println("Response body =>\n" + response.asString());

    }

    @Test
    public void printAllHeadersWithValues(){
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users?page=2");

        String contentType = response.header("Content-Type");
        System.out.println("Content-Type value : " + contentType);

        String serverType = response.header("Server");
        System.out.println("Server value : " + serverType);

        String acceptLanguage = response.header("Content-Encoding");
        System.out.println("Content-Encoding value : " + acceptLanguage);

        String age = response.header("Age");
        System.out.println("Age : " + age);
    }

    @Test
    public void printAllHeadersWithValuesUsingLoop(){
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users");

        Headers allHeaders = response.headers();

        for(Header h: allHeaders){
            System.out.println(h.getName() + " : " + h.getValue());
        }
    }

    @Test
    public void assertAllHeadersValues(){
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users?page=2");

        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json; charset=utf-8");

        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "cloudflare");

        String acceptLanguage = response.header("Content-Encoding");
        Assert.assertEquals(acceptLanguage, "gzip");

        String via = response.header("Via");
        Assert.assertEquals(via, "1.1 vegur");
    }

    @Test
    public void printResponseBody(){
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users");

        ResponseBody body = response.body();

        System.out.println("Response body is \n" + body.asString());

    }

    @Test
    public void assertResponseBodyContainsString(){
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users");

        String responseBodyAsString = response.body().asString();

        Assert.assertEquals(responseBodyAsString.contains("page"), true);

    }

    @Test
    public void printListsOfData(){
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Integer page = jsonPathEvaluator.get("page");
        System.out.println(page);
        System.out.println("\n\n");

        List listOfData = jsonPathEvaluator.get("data");
        for(Object i : listOfData){
            System.out.println(i);
        }
        System.out.println("\n\n");

        List listOfEmails = jsonPathEvaluator.get("data.email");

        for(Object i : listOfEmails){
            System.out.println(i);
        }

    }

    @Test
    public void printRequestWithParameters(){
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.queryParam("page", "2")
                                       .queryParam("id", "9")
                                       .get("/api/users");

        String responseString = response.asString();
        System.out.println(responseString);
        System.out.println(response.getStatusCode());
    }

    @Test
    public void postInfoAndPrintBodyAndStatus(){
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "Bond");
        requestParams.put("job", "comparator");

        request.body(requestParams.toJSONString());
        Response response = request.post("/api/users");
        //      Response response = request.post("/api/users");
        System.out.println(response.getStatusCode());
        System.out.println(response.body().asString());

    }



}

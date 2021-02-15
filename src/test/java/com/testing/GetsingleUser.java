package com.testing;


//import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;


public class GetsingleUser {
    @BeforeClass
    public static void setUp(){
    baseURI="https://reqres.in/api";
    }
    @Test
    public void validateSingleUserData(){
        //given().get("/users/3").prettyPrint();
        // given().pathParam("id",3).get("/users/{id}").prettyPrint();
            given().pathParam("id", 3).get("/users/{id}").
                    then().
                    body("data.id",equalTo(3)).and().
                    body("data.email",equalTo("emma.wong@reqres.in")).and().
                    body("data.first_name",equalTo("Emma")).and().
                    body("data.last_name",equalTo("Wong"));

        }
    @Test
    public void createUser(){
        //First way

//        Map<String,Object> data=new HashMap<>();
//        data.put("name","Adam");
//        data.put("job","truck driver");

        //Second way

        JSONObject resBody= new JSONObject();
        resBody.put("name","Hadicha");
        resBody.put("job","scholar");

        given().header("Content-type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).body(resBody.toJSONString()).
                when().post("/users").
                then().statusCode(201).log().all();

    }
    @Test
    public void updateUser(){
        JSONObject resData=new JSONObject();
        resData.put("name","kevin de broyne");
        resData.put("job","football player");
        given().header("Content-type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(resData.toJSONString()).
                when().put("/users/7").
                then().statusCode(200).and().
                body("name",equalTo("kevin de broyne")).and().
                body("job",equalTo("football player"));



    }
    @Test
    public void deleteUser(){
        given().delete("/users/7").then().statusCode(204).log().all().  // first way to enter
        header("Content-Length",equalTo("0")).and().
                header("X-Powered-By",equalTo("Express"));


        //        Response response = RestAssured.delete("/users/7");---------// second way to enter
//        String statusLine=response.getStatusLine();
//        System.out.println(statusLine);


    }
}


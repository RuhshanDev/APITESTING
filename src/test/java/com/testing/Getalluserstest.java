package com.testing;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
 import static  org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Getalluserstest {
@BeforeClass
 public static void setUp(){
     RestAssured.baseURI="https://reqres.in/api";
 }

    @Test
    public void getallusers(){

        //https://reqres.in/api/users?page=2

        //RestAssured.baseURI="https://reqres.in/api";
//        given().get("https://reqres.in/api/users?page=2")
//                .then()
//                .statusCode(200);
        Response javob= RestAssured.get("/users?page=2");
        //it print the response into console
       // javob.prettyPrint();
        //converts the response into String format
        String resString= javob.asString();
       // System.out.println(resString);
       // System.out.println(restString.contains("page"));
        int statusCode= javob.statusCode();
        Assert.assertEquals(statusCode,200);


    }
    @Test
    public void headersTest(){
        Response res=RestAssured.get("/users?page=2");
        System.out.println(res.headers());
        String conType=res.header("Content-Type");
        String connection=res.header("Connection");
        Assert.assertEquals("application/json; charset=utf-8",conType);
        Assert.assertEquals("keep-alive",connection);


    }
    @Test
    public void statusLine(){
      Response response=RestAssured.get("/users?page=2") ;
      String statusLine=response.statusLine();
        System.out.println(statusLine);
        Assert.assertEquals("HTTP/1.1 200 OK",statusLine);


    }
    @Test
    public void testPage(){
    Response response=RestAssured.get("/users?page=2");
    response.prettyPrint();
    String page=response.asString();
    Assert.assertTrue(page.contains("page"));
    }

}

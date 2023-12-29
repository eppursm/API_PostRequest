package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_PostRequest {

    @Test
    public void post01(){
        //Request URL ve Body
        String url = "https://jsonplaceholder.typicode.com/posts";
        JSONObject reqBody = new JSONObject();

        reqBody.put("title","foo");
        reqBody.put("body","bar");
        reqBody.put("userId",1);

        //Expected Data
        JSONObject expBody = new JSONObject();

        expBody.put("title","foo");
        expBody.put("body","bar");
        expBody.put("userId",1);

        //Response'u kaydet
        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                post(url);

        JsonPath actBody = response.jsonPath();

        //Asertion

        response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(201);

        Assert.assertEquals(expBody.get("title"),actBody.get("title"));
        Assert.assertEquals(expBody.get("body"),actBody.get("body"));
        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));




    }

}

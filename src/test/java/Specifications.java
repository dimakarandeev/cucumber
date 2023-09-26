import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build(); // - собрать
    }

    public static ResponseSpecification responseSpecification(Integer code){
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
        // если приходит не 200 - то дальше не идём
    }

    public static void installSpecification(RequestSpecification request,
                                            ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}

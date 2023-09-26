import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class ApiRequest {

    // Интерфейс RequestSender - интерфейс, содержащий методы для отправки HTTP запросов.
    // его метод - request

    //Отправка HTTP запроса
    @Test
    public void httpRequestTest() {
        RestAssured
                .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                .when()
                // Отправка HTTP запроса
                    .request("GET", "/booking")
                .then()
                // Проверка кода статуса ответа
                    .statusCode(200);
    }

    @Test
    public void responseOutputTest() {
        // RequestSpecification - класс спецификация запроса
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://rickandmortyapi.com/api/");
        request.queryParam("name","Rick Sanchez");
        // Response - класс, хранящий ответ запроса
        Response response = request.get("/character");
        // Вывод в консоль в не отфоратированном виде
//        response.peek();
        // Вывод в консоль в отфоратированном виде
        response.prettyPeek();
        // Вывод в консоль в не отфоратированном виде и возврат в виде строки
//        String printString = response.print();
//        System.out.println("\r\nprintString: " + printString);
        // Вывод в консоль в отфоратированном виде и возврат в виде строки
//        String prettyPrintString = response.prettyPrint();
//        System.out.println("\r\nprettyPrintString: " + prettyPrintString);
        // Проверка кода статуса ответа
        request.then().statusCode(200);
    }

    @Test
    public void responseGetBodyTest() {
        // RequestSpecification - класс спецификация запроса
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://rickandmortyapi.com/api/");
        request.queryParam("name","Rick Sanchez");
        // Response - класс, хранящий ответ запроса
        Response response = request.get("/character");
        ResponseBody body = response.body();
        System.out.println("body: " + body.asPrettyString());
        // Проверка кода статуса ответа
        request.then().statusCode(200);
    }

    @Test
    public void responseGetCookiesTest() {
        // RequestSpecification - класс спецификация запроса
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://rickandmortyapi.com/api/");
        request.queryParam("name","Rick Sanchez");
        // Response - класс, хранящий ответ запроса
        Response response = request.get("/character");
        String cookie = response.cookie("Cookie 1");
        System.out.println("cookie: " + cookie);
        // Проверка кода статуса ответа
        request.then().statusCode(200);
    }

    @Test
    public void responseContentTypeTest() {
        // RequestSpecification - класс спецификация запроса
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://rickandmortyapi.com/api/");
        request.queryParam("name","Rick Sanchez");

        // Response - класс, хранящий ответ запроса
        Response response = request.get("/character");
        String contentType = response.contentType();
        System.out.println("contentType: " + contentType);
    }

    @Test
    public void test () {
        Specifications.installSpecification(Specifications.requestSpecification(EndPoint.url),
                Specifications.responseSpecification(200));
        RestAssured
                .given()
                //.when()
                // Отправка HTTP запроса
                .request("GET", "/booking")
                .then()
                // Проверка кода статуса ответа
                .statusCode(200);

    }

}

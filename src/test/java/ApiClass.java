import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class ApiClass {
    @Test
    public void givenWhenThenTest() {
        RestAssured
                // В given задаем спецификацию отправляемого запроса
                // URL, параметры, заголовки, тело запроса и т д
                .given()
                    .queryParam("name","Rick Sanchez")
                // В when отправляем запрос и получаем ответ
                .when()
//                    .get("https://rickandmortyapi.com")
                    .get("https://rickandmortyapi.com/api/character")
                // В then задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .then()
                    .statusCode(200);
    }

    // Метод with
    @Test
    public void withWhenThenTest() {
        RestAssured
                // В with задаем спецификацию отправляемого запроса
                // URL, параметры, заголовки, тело запроса и т д
                .with()
                    .queryParam("name","Rick Sanchez")
                // В when отправляем запрос и получаем ответ
                .when()
                    .get("https://rickandmortyapi.com/api/character")
                // В then задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .then()
                    .statusCode(200);
    }

    //Метод when
    @Test
    public void givenExpectWhenTest() {
        RestAssured
                // В given задаем спецификацию отправляемого запроса
                // URL, параметры, заголовки, тело запроса и т д
                .given()
                    .queryParam("name","Rick Sanchez")
                // В expect задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .expect()
                    .statusCode(200)
                // В when отправляем запрос и получаем ответ
                .when()
                    .get("https://rickandmortyapi.com/api/character");
    }

    //Отправка HTTP запроса
    @Test
    public void httpRequestTest() {
        // RestAssured.requestSpecification - статическое поле, хранящее спецификацию запроса
        // RequestSpecBuilder - класс билдер, для создания спецификации запроса
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com/booking")
                .build();
        // Response - класс, хранящий ответ запроса
        // Отправка HTTP запроса
        Response response = RestAssured.request("GET");
        // в ответ (response) записываем результат метода .request("GET");
        // Вывод ответа
        response.prettyPrint();
        // Проверка кода статуса ответа
        response.then().statusCode(200);
    }

    //Отправка HTTP запроса GET
    @Test
    public void httpGETTest() {
        // RestAssured.requestSpecification - статическое поле, хранящее спецификацию запроса
        // RequestSpecBuilder - класс билдер, для создания спецификации запроса
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
        // Response - класс, хранящий ответ запроса
        // Отправка HTTP запроса GET
        Response response = RestAssured.get("/booking");
        // Вывод ответа
        response.prettyPrint();
        // Проверка кода статуса ответа
        response.then().statusCode(200);
    }

    @Test
    public void httpPOSTTest() {
        // Тело запроса
        String jsonString = "{\r\n" +
                "   \"username\" : \"admin\",\r\n" +
                "   \"password\" : \"password123\"\r\n" +
                "}";
        // RestAssured.requestSpecification - статическое поле, хранящее спецификацию запроса
        // RequestSpecBuilder - класс билдер, для создания спецификации запроса
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .setContentType(ContentType.JSON)
                .setBody(jsonString)
                .build();
        // Response - класс, хранящий ответ запроса
        // Отправка HTTP запроса POST
        Response response = RestAssured.post("/auth");
        // Вывод ответа
        response.prettyPrint();
        // Проверка кода статуса ответа
        response.then().statusCode(200);
    }

    @Test
    public void httpPUTTest() {
        // Тело запроса
        String jsonString = "{\r\n" +
                "   \"firstname\" : \"John\",\r\n" +
                "   \"lastname\" : \"Johnes\",\r\n" +
                "   \"totalprice\" : 99999,\r\n" +
                "   \"depositpaid\" : true,\r\n" +
                "   \"bookingdates\" : {\r\n" +
                "       \"checkin\" : \"2022-01-01\",\r\n" +
                "       \"checkout\" : \"2024-01-01\"\r\n" +
                "   },\r\n" +
                "   \"additionalneeds\" : \"Breakfast\"\r\n" +
                "}";
        // RestAssured.requestSpecification - статическое поле, хранящее спецификацию запроса
        // RequestSpecBuilder - класс билдер, для создания спецификации запроса
        // В addCookie нужно вставить токен из предыдущего запроса
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addCookie("token", "c91e15d4fd7d830")
                .setContentType(ContentType.JSON)
                .setBody(jsonString)
                .build();
        // Response - класс, хранящий ответ запроса
        // Отправка HTTP запроса PUT
        Response response = RestAssured.put("/booking/1230");
        // Вывод ответа
        response.prettyPrint();
        // Проверка кода статуса ответа
        response.then().statusCode(200);
    }

    //Отправка HTTP запроса DELETE
    @Test
    public void httpDELETETest() {
        // RestAssured.requestSpecification - статическое поле, хранящее спецификацию запроса
        // RequestSpecBuilder - класс билдер, для создания спецификации запроса
        // В addCookie нужно вставить токен из предыдущего запроса
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addCookie("token", "c91e15d4fd7d830")
                .build();
        // Response - класс, хранящий ответ запроса
        // Отправка HTTP запроса DELETE
        Response response = RestAssured.delete("/booking/1247");
        // Вывод ответа
        response.prettyPrint();
        // Проверка кода статуса ответа
        response.then().statusCode(201);
    }

}

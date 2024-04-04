package stepsanddata;

import pojo.data.DataForUserLogin;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.data.DataForUserRegister;

import static io.restassured.RestAssured.given;

public class StepMethods {
    @Step("Send POST request to /api/auth/register")
    public Response registerUser(String email, String password, String name) {

        Response responseRegister =
                given()
                        .header("Content-type", "application/json")
                        .body(new DataForUserRegister(email, password, name))
                        .post("api/auth/register");

        return responseRegister;
    }

    @Step("Send POST request to /api/auth/login")
    public Response loginUser(String email, String password) {
        Response responseLogin =
                given()
                        .header("Content-type", "application/json")
                        .body(new DataForUserLogin(email, password))
                        .post("api/auth/login");

        return responseLogin;
    }

    @Step("Get field authorization")
    public String getFieldAuthorization(String email, String password) {
        Gson gson = new Gson();
        JsonObject objectLoginResponse = gson.fromJson(loginUser(email, password).body().asString(), JsonObject.class);

        if (objectLoginResponse.get("accessToken") != null) {
            return objectLoginResponse.get("accessToken").getAsString();
        }
        return "";
    }

    @Step("Send DELETE request to /api/auth/user")
    public Response deleteUser(String email, String password) {
        Response responseDelete =
                given()
                        .header("Content-type", "application/json")
                        .header("authorization", getFieldAuthorization(email, password))
                        .delete("api/auth/user");

        return responseDelete;
    }
}

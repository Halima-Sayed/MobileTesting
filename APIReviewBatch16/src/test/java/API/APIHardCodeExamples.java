package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class APIHardCodeExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTExMDY0MzksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTE0OTYzOSwidXNlcklkIjoiNTcwNyJ9.wcEmJt-gp8EjxKI-ZH_3snWdv4Ke9MqBVwhZo2Hzibg";

    @Test
    public void createEmployee(){
       RequestSpecification request= given().header("Content-Type","application/json").header("Authorization",token)
               .body("{\n" +
                       "  \"emp_firstname\": \"u\",\n" +
                       "  \"emp_lastname\": \"rude\",\n" +
                       "  \"emp_middle_name\": \"r\",\n" +
                       "  \"emp_gender\": \"M\",\n" +
                       "  \"emp_birthday\": \"2000-07-28\",\n" +
                       "  \"emp_status\": \"mean\",\n" +
                       "  \"emp_job_title\": \"rudeness\"\n" +
                       "}");
        Response response=request.when().post("/createEmployee.php");

        response.prettyPrint();

        String employee_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        String status=response.jsonPath().getString("Employee.emp_status");
        Assert.assertEquals("mean",status);

    }
}

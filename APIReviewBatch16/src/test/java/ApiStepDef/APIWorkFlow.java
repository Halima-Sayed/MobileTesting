package ApiStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkFlow {
    RequestSpecification request;
    Response response;
    public static String employee_id;

    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        request = given().header("Content-Type","application/json").header("Authorization", GenerateToken.token).body("{\n" +
                "  \"emp_firstname\": \"abc\",\n" +
                "  \"emp_lastname\": \"xyz\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2013-08-01\",\n" +
                "  \"emp_status\": \"conf\",\n" +
                "  \"emp_job_title\": \"qa\"\n" +
                "}");
    }
    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post("/createEmployee.php");
        response.prettyPrint();
    }
    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String key, String value) {
        //response.then().assertThat().body(key,equalTo(value));
        //or
        String actual=response.jsonPath().getString(key);
        Assert.assertEquals(actual,value);
    }
    @Then("the employee id {string} is stored as a global variable")
    public void the_employee_id_is_stored_as_a_global_variable(String empID) {
        employee_id=response.jsonPath().getString(empID);
        System.out.println(employee_id);
    }
}

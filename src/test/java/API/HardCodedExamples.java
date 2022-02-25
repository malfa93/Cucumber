package API;

import com.sun.source.tree.AssertTree;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDU3NTk0MDUsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY0NTgwMjYwNSwidXNlcklkIjoiMzQ1NyJ9.LAWKA-q68lyh4BTwLVguQK-oFFN4LRirXlnWiijGr10";

    static String employee_id;

    @Test
    public void bGetCreatedEmployee(){

        //Storing the base URI to use.


        // preparing the request to get an employee
        RequestSpecification preparedRequest = given().header("Content-Type","application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        Response response =  preparedRequest.when().get("/getOneEmployee.php");

        //System.out.println(response.asString());

        response.prettyPrint(); // prettyPrint() does the same as sysout

        String empID = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpIds = empID.contentEquals(employee_id);

        System.out.println(employee_id);

        //adding assertion to get true return from boolean
        Assert.assertTrue(comparingEmpIds);

        response.then().assertThat().statusCode(200);

        String middleName = response.jsonPath().getString("employee.emp_middle_name");
        Assert.assertTrue(middleName.contentEquals("FNA"));


    }

    // to create an employee

    @Test
    public void aCreateEmployee(){

        RequestSpecification preparedRequest = given().header("Content-Type","application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"emp_firstname\": \"Malfadh\",\n" +
                        "  \"emp_lastname\": \"Alfad\",\n" +
                        "  \"emp_middle_name\": \"FNA\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1993-03-16\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                        "}");

        Response response = preparedRequest.when().post("/createEmployee.php\n");

        response.prettyPrint();


        //we use jasonPath() to get specific information from the json object
        employee_id=response.jsonPath().getString("Employee.employee_id");

        System.out.println(employee_id);

        //Assertion

        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("FNA") );
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("Server", equalTo("Apache/2.4.39 (Win64) PHP/7.2.18"));
        response.then().assertThat().body("Employee.emp_job_title", equalTo("Cloud Consultant"));



    }


    @Test
    public void cUpdateEmployee(){

        //update the existing employee

        RequestSpecification preparedRequest = given().header("Content-Type","application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"employee_id\": \""+ employee_id +"\",\n" +
                        "  \"emp_firstname\": \"Alfa\",\n" +
                        "  \"emp_lastname\": \"Fad\",\n" +
                        "  \"emp_middle_name\": \"FN\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1996-02-20\",\n" +
                        "  \"emp_status\": \"Scrum Master\",\n" +
                        "  \"emp_job_title\": \"SM\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php\n");

        response.prettyPrint();

        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        response.then().assertThat().statusCode(200);


    }


    @Test
    public void dGetUpdatedEmployee(){

        //Storing the base URI to use.


        // preparing the request to get an employee
        RequestSpecification preparedRequest = given().header("Content-Type","application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        Response response =  preparedRequest.when().get("/getOneEmployee.php");

        //System.out.println(response.asString());

        response.prettyPrint(); // prettyPrint() does the same as sysout

        String empID = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpIds = empID.contentEquals(employee_id);

        //adding assertion to get true return from boolean
        Assert.assertTrue(comparingEmpIds);

        response.then().assertThat().statusCode(200);

        String middleName = response.jsonPath().getString("employee.emp_middle_name");
        Assert.assertTrue(middleName.contentEquals("FN"));


    }
}

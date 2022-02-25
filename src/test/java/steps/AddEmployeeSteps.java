package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AddNewEmployeePage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;
import utils.GlobalVariables;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {


//        WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
//        pimOption.click();

        dash.pimOption.click();
    }

    @When("user clicks on Add employee button")
    public void user_clicks_on_add_employee_button() {


//        WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
//        addEmployeeButton.click();

        //dash.addEmployeeButton.click();
        click(dash.addEmployeeButton);


    }



    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {

//        WebElement firstName = driver.findElement(By.id("firstName"));
//        firstName.sendKeys("Nammar");

        //addNewEmployeePage.firstName.sendKeys("Nammar");
        sendText(addNewEmployeePage.firstName, "Nammar");


//        WebElement lastName = driver.findElement(By.id("lastName"));
//        lastName.sendKeys("MS");

        //addNewEmployeePage.lastName.sendKeys("MS");
        sendText(addNewEmployeePage.lastName, "MS");


    }



    @When("user clicks on save button")
    public void user_clicks_on_save_button() {

//        WebElement saveButton = driver.findElement(By.id("btnSave"));
//        saveButton.click();

        //addNewEmployeePage.saveButton.click();
        click(addNewEmployeePage.saveButton);
    }


    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");

        click(addNewEmployeePage.editOption);
        selectDropdown(addNewEmployeePage.maritalStatus, "Other");
        selectDropdown(addNewEmployeePage.nationality, "British");
        click(addNewEmployeePage.saveButton);
    }




    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
//        WebElement firstNameLoc = driver.findElement(By.id("firstName"));
//        firstNameLoc.sendKeys(firstName);

        //addNewEmployeePage.firstName.sendKeys(firstName);
        sendText(addNewEmployeePage.firstName, firstName);

//        WebElement lastNameLoc = driver.findElement(By.id("lastName"));
//        lastNameLoc.sendKeys(lastName);

        //addNewEmployeePage.lastName.sendKeys(lastName);
        sendText(addNewEmployeePage.lastName, lastName);


//        WebElement middleNameLoc = driver.findElement(By.id("middleName"));
//        middleNameLoc.sendKeys(middleName);

        //addNewEmployeePage.middleName.sendKeys(middleName);
        sendText(addNewEmployeePage.middleName, middleName);

    }





    @When("user enters direct data {string} {string} and {string}")
    public void user_enters_direct_data_and(String firstName, String middleName, String lastName) {

//        WebElement firstNameLoc = driver.findElement(By.id("firstName"));
//        firstNameLoc.sendKeys(firstName);

        addNewEmployeePage.firstName.sendKeys(firstName);

//        WebElement lastNameLoc = driver.findElement(By.id("lastName"));
//        lastNameLoc.sendKeys(lastName);

        addNewEmployeePage.lastName.sendKeys(lastName);

//        WebElement middleNameLoc = driver.findElement(By.id("middleName"));
//        middleNameLoc.sendKeys(middleName);

        addNewEmployeePage.middleName.sendKeys(middleName);

    }






    @When("user add multiple employees and verify they are added")
    public void user_add_multiple_employees_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> employeeNames = dataTable.asMaps();


        for (Map<String, String> emp :
                employeeNames) {
            String firstNameValue = emp.get("firstName");
            String middleNameValue = emp.get("middleName");
            String lastNameValue = emp.get("lastName");

//            WebElement firstNameLoc = driver.findElement(By.id("firstName"));
//            firstNameLoc.sendKeys(firstNameValue);

            //addNewEmployeePage.firstName.sendKeys(firstNameValue);
            sendText(addNewEmployeePage.firstName, firstNameValue);

//            WebElement middleNameLoc = driver.findElement(By.id("middleName"));
//            middleNameLoc.sendKeys(middleNameValue);

            //addNewEmployeePage.middleName.sendKeys(middleNameValue);
            sendText(addNewEmployeePage.middleName, middleNameValue);

//            WebElement lastNameLoc = driver.findElement(By.id("lastName"));
//            lastNameLoc.sendKeys(lastNameValue);

            addNewEmployeePage.lastName.sendKeys(lastNameValue);
            sendText(addNewEmployeePage.lastName, lastNameValue);

//            WebElement saveButton = driver.findElement(By.id("btnSave"));
//            saveButton.click();

            //addNewEmployeePage.saveButton.click();
            click(addNewEmployeePage.saveButton);

            //Assertions in Homework

            Thread.sleep(5000);

//            WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
//            addEmployeeButton.click();

            //dash.addEmployeeButton.click();
            click(dash.addEmployeeButton);

            Thread.sleep(3000);
        }
    }


    @When("user adds multiple employyes from excel file using {string} sheet and verify the added employee")
    public void user_adds_multiple_employyes_from_excel_file_using_sheet_and_verify_the_added_employee(String sheetName) throws InterruptedException {


        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, sheetName);

        Iterator<Map<String, String>> itr = newEmployees.iterator();


        while(itr.hasNext()){
            //it returns the key and value for employees
            Map<String, String> mapNewEmp = itr.next();

            //WebElement firstNameLoc = driver.findElement(By.id("firstName"));
            //addNewEmployeePage.firstName.sendKeys(mapNewEmp.get("FirstName"));
            sendText(addNewEmployeePage.firstName, mapNewEmp.get("FirstName"));

            //WebElement lastNameLoc = driver.findElement(By.id("lastName"));
            //addNewEmployeePage.lastName.sendKeys(mapNewEmp.get("LastName"));
            sendText(addNewEmployeePage.lastName, mapNewEmp.get("LastName"));

            //WebElement middleNameLoc = driver.findElement(By.id("middleName"));
            //addNewEmployeePage.middleName.sendKeys(mapNewEmp.get("MiddleName"));
            sendText(addNewEmployeePage.lastName,mapNewEmp.get("MiddleName"));


            //WebElement empID = driver.findElement(By.id("employeeId"));
            String empIdValue = addNewEmployeePage.empIdLoc.getAttribute("value");

            //WebElement photo = driver.findElement(By.id("photofile"));
            //addNewEmployeePage.photograph.sendKeys(mapNewEmp.get("Photograph"));
            sendText(addNewEmployeePage.photograph, mapNewEmp.get("Photograph"));

            //WebElement checkBox = driver.findElement(By.id("chkLogin"));
            if(!addNewEmployeePage.checkbox.isSelected()){
                addNewEmployeePage.checkbox.click();
            }

//            WebElement username = driver.findElement(By.id("user_name"));
//            WebElement password = driver.findElement(By.id("user_password"));
//            WebElement confirmPassword = driver.findElement(By.id("re_password"));

            //addNewEmployeePage.createUsername.sendKeys(mapNewEmp.get("Username"));
            //addNewEmployeePage.createPassword.sendKeys(mapNewEmp.get("Password"));
            //addNewEmployeePage.rePassword.sendKeys(mapNewEmp.get("Password"));

            sendText(addNewEmployeePage.createUsername, mapNewEmp.get("Username"));
            sendText(addNewEmployeePage.createPassword, mapNewEmp.get("Password"));
            sendText(addNewEmployeePage.rePassword, mapNewEmp.get("Password"));


//            WebElement saveButton = driver.findElement(By.id("btnSave"));
            addNewEmployeePage.saveButton.click();

            Thread.sleep(3000);





            //WebElement empList = driver.findElement(By.id("menu_pim_viewEmployeeList"));
            //dash.employeeListButton.click();
            click(dash.employeeListButton);

            //WebElement empIdSearchField = driver.findElement(By.id("empsearch_id"));
            //employeeListPage.idEmployeeSearch.sendKeys(empIdValue);
            sendText(employeeListPage.idEmployeeSearch, empIdValue);

            //WebElement searchButton = driver.findElement(By.id("searchBtn"));
            //employeeListPage.searchButton.click();
            click(employeeListPage.searchButton);

            //Assertions in Homework

            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));

            for (int i = 0; i < rowData.size(); i++) {


                System.out.println("I'm inside the loop");

                String rowText = rowData.get(i).getText();

                System.out.println(rowText);

                String expectedData = empIdValue + " " + mapNewEmp.get("FirstName") + " " + mapNewEmp.get("MiddleName") + " " + mapNewEmp.get("LastName");

                System.out.println(expectedData);

                Assert.assertEquals(expectedData, rowText);

            }

            // to come back again on add employee screen because hooks and background works just one time


//            WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
            //dash.addEmployeeButton.click();
            click(dash.addEmployeeButton);
            Thread.sleep(3000);

        }
    }

    @When("capture the employee id")
    public void capture_the_employee_id() {
        AddNewEmployeePage ad=new AddNewEmployeePage();
        GlobalVariables.emp_id=ad.empIdLoc.getAttribute("value");
        GlobalVariables.firstName=ad.firstName.getAttribute("value");
        System.out.println("Emp_id "+GlobalVariables.emp_id);
        System.out.println("firstName "+GlobalVariables.firstName);
    }


    @Then("verify the results from UI and Backend")
    public void verify_the_results_from_ui_and_backend() {
        String firstNameFromDb=GlobalVariables.tableData.get(0).get("emp_firstname");
        //System.out.println("First Name from Database "+firstNameFromDb);
        //System.out.println("First Name from GUI "+GlobalVariables.firstName);
        Assert.assertEquals(firstNameFromDb,GlobalVariables.firstName);
    }


    @Then("verify the result from Front end and Back end")
    public void verify_the_result_from_front_end_and_back_end() {
        String firstNameFromDb=GlobalVariables.tableData.get(0).get("employee_id");
        Assert.assertEquals(firstNameFromDb,GlobalVariables.emp_id);
    }





}

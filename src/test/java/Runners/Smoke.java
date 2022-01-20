package Runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        //features we use to provide the path of all the feature files
        features = "src/test/resources/features/",
        //glue is where we find the implementations for gherkins steps
        // we provide the path of  package where we defined all the steps
        glue = "steps",
        //if we set dryRun to true, it will quickly scan all gherkin steps whether they are implemented or not
        //if we set dryRun to true, it also stops actual execution
        //if we set dryRun to false, it will execute all your code.
        dryRun = false,
        //when monochrome is true, it means that it removes all the irrelevant information from the console
        monochrome = true,
        // tags will identify the scenario based on the tag we provide to the feature file
        // tags can have the "or" and the "and" keywords
        //tags = "@smoke or @regression",
        tags = "@excel",
        // plugins is for report generation and other purposes
        plugin = {"pretty", "html:target/cucumber.html"}

)



public class Smoke {
}

package cucumber.options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/feature",plugin = "json:target/jsonReport/cucumber-report.json",glue={"stepDef"})
public class TestRunner {
//    tags = "@deletePlace"
    public void runner(){
        System.out.println("Deeraj kkk");
    }
}

package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.cucumber.CucumberTest;
import test.kafka.KafkaTest;
import test.rest.RESTTest;
import test.selenium.SeleniumTest;

@RunWith(Suite.class)
@SuiteClasses({ CucumberTest.class, KafkaTest.class, RESTTest.class, SeleniumTest.class })
public class AllTests {

}

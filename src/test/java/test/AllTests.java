package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.uniovi.CucumberTest;
import com.uniovi.SeleniumTest;

import test.kafka.KafkaTest;
import test.rest.RESTTest;

@RunWith(Suite.class)
@SuiteClasses({ KafkaTest.class, RESTTest.class })
public class AllTests {

}

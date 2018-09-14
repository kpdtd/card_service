package com.anl.user.cucumber;

import com.anl.user.util.LogFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty",
        "html:target/cucumber"}, features = {
        "src/test/resources/features/activityInfoChargeOrder.feature"})
public class CbssIT {

    private static EmbeddedTomcat tomcat = new EmbeddedTomcat();

    @BeforeClass
    public static void setup() {
        if (!tomcat.isRunning()) {
            tomcat.start();
            tomcat.deploy("card_server");
            LogFactory.getInstance().getLogger().debug("tomcat-start ...");
        }
    }

    @AfterClass
    public static void teardown() {
        if (tomcat.isRunning()) {
            tomcat.stop();
            LogFactory.getInstance().getLogger().debug("tomcat-stop ...");
        }
    }

}

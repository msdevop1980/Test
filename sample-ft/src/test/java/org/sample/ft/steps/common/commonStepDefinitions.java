package org.sample.ft.steps.common;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.assertj.core.api.SoftAssertions;
import org.junit.runner.RunWith;
import org.sample.ft.BaseCucumber;
import org.sample.ft.DbConfig;
import org.sample.ft.FtConfig;
import org.sample.ft.TestDBQuery;
import org.sample.ft.data.CommonHeaderParams;
import org.sample.ft.util.RestUtil;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CommonHeaderParams.class, FtConfig.class, DbConfig.class, TestDBQuery.class})
public class commonStepDefinitions extends BaseCucumber implements En {


    public commonStepDefinitions() {
        Given("^The server is available under path v(\\d+)$", (final Integer version) ->
                 RestUtil.checkApiAvailability(getRestEndPoint(version))
        );
        Given("^The Test Case Id is TC_Read_(\\d+)$", (final Integer arg0) -> {
            System.out.println("Test Case Id :"+arg0);
        });

        Then("^The Test Data Id is <TC_Read_ID>$", () -> {

        });

    }

    @Before
    public void beforeScenario(){
        softAssertions=new SoftAssertions();
    }

    @After
    public void afterScenario(){
        softAssertions.assertThat(softAssertions.errorsCollected().isEmpty());
        softAssertions.assertAll();
    }



}

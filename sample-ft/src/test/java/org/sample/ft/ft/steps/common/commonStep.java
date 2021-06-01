package org.sample.ft.ft.steps.common;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.assertj.core.api.SoftAssertions;
import org.sample.ft.ft.BaseCucumber;
import org.sample.ft.ft.DbConfig;
import org.sample.ft.ft.FtConfig;
import org.sample.ft.ft.TestDBQuery;
import org.sample.ft.ft.data.CommonHeaderParams;
import org.sample.ft.ft.util.RestUtil;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {CommonHeaderParams.class, FtConfig.class, DbConfig.class, TestDBQuery.class})
public class commonStep extends BaseCucumber {

    @Before
    public void beforeScenario(){
        softAssertions=new SoftAssertions();
    }

    @After
    public void afterScenario(){
        softAssertions.assertThat(softAssertions.errorsCollected().isEmpty());
        softAssertions.assertAll();
    }
    public void commonStepDefinition(final CommonHeaderParams commonHeaderParams){
        Given("^ The server is available under path (.*)$",(final String apiVersion)-> RestUtil.checkApiAvailability(getRestEndPoint(apiVersion)));
        Given("^ The Test case Id is  (.*)$",(final String testCaseId)-> initializeAllParams(commonHeaderParams));
        Then("^ The Error Response is (?:\\s)?(.+)?$",(final String errorMessage)->softAssertions.assertThat(errorMessage)
                .as("Error Message").isEqualTo(RestUtil.fromErrorResponse(commonHeaderParams,"resolvedMessage")));
    }

}

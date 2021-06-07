package org.sample.ft.steps.v1;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.sample.ft.BaseCucumber;
import org.sample.ft.data.CommonHeaderParams;
import org.sample.ft.util.RestUtil;
import org.sample.model.User;

import java.util.Map;

public class ReadStepDefinitions extends BaseCucumber implements En {
    private static final String RESOURCE_PATH="/sample/{Id}";
    @Before
    public void beforeScenario(){
        softAssertions=new SoftAssertions();
    }

    @After
    public void afterScenario(){
        softAssertions.assertThat(softAssertions.errorsCollected().isEmpty());
        softAssertions.assertAll();
    }

    public ReadStepDefinitions(final CommonHeaderParams commonHeaderParams) {
        And("^The input for read is$", (final DataTable dataTable) -> {
            Map<Object,String> params=dataTable.asMap(String.class,String.class);
            if(StringUtils.isNoneBlank(params.get("Id"))){
                commonHeaderParams.setRestPathParam(Map.of("Id",params.get("Id")));
            }
        });
        When("^Request to read service$", () -> commonHeaderParams.setSetResponse(RestUtil.executeGetRequest(RESOURCE_PATH,commonHeaderParams)));
        Then("^The response should be$", (final DataTable dataTable) -> {
            Map<String,String>  expected=dataTable.asMap(String.class,String.class);
           User user=commonHeaderParams.getSetResponse().body().as(User.class);
            System.out.println("Id from data table::"+expected.get("Id"));
            System.out.println("Name from data table::"+expected.get("Expected_Name"));
           softAssertions.assertThat(user.getId().equals(expected.get("Id")));

           softAssertions.assertThat(user.getName().equals(expected.get("Expected_Name")));

        });
        /*And("The input for read is$",(final DataTable dataTable)->{
            Map<Object,String> params=dataTable.asMap(String.class,String.class);
            if(StringUtils.isNoneBlank(params.get("Expected Id"))){
                commonHeaderParams.setRestPathParam(Map.of("id",params.get("Expected Id")));
            }
        });

        When("^Request to read service$",()->commonHeaderParams.setSetResponse(RestUtil.executeGetRequest(RESOURCE_PATH,commonHeaderParams)));
    }*/
    }

}

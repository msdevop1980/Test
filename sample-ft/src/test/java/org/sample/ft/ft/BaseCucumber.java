package org.sample.ft.ft;

import cucumber.api.java8.En;
import org.assertj.core.api.SoftAssertions;
import org.sample.ft.ft.data.CommonHeaderParams;

import javax.inject.Inject;

public class BaseCucumber implements En {

    @Inject
    FtConfig ftConfig;

    //DbAccessor create bean and which query to interact with DB and get the data to assert
    protected SoftAssertions softAssertions;

    public void initializeRestRequestParams(){
        /// set all header  params to null / new map
    }

    protected String getRestEndPoint(final String apiVersion){

        return ftConfig.getEndPoint().concat(ftConfig.getRestPath()).concat(apiVersion);
    }
    protected void initializeAllParams(CommonHeaderParams commonHeaderParams){
        commonHeaderParams.setRestRequest(null);
    }

}

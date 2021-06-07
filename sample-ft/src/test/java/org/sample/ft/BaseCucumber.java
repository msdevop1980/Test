package org.sample.ft;

import cucumber.api.java8.En;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.SoftAssertions;
import org.sample.ft.data.CommonHeaderParams;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
@Setter
@Getter
@Component
public class BaseCucumber implements En {

    @Inject
    FtConfig ftConfig;
    //DbAccessor create bean and which query to interact with DB and get the data to assert
    protected SoftAssertions softAssertions;

    protected String getRestEndPoint(final Integer apiVersion){

        return ftConfig.getEndPoint().concat("v").concat(ftConfig.getRestPath());
    }
    public void initializeAllParams(CommonHeaderParams commonHeaderParams){
        commonHeaderParams.setRestRequest(null);
        commonHeaderParams.setSetResponse(null);
        commonHeaderParams.setRestPathParam(new HashMap<>());


    }

}

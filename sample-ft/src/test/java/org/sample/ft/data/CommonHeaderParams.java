package org.sample.ft.data;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@Component
public class CommonHeaderParams {
    // map of query params
    // map of rest headers
    // map of path params
    // paas above all if available  for the REST request to RestUtil
    private Object restRequest;
    private ExtractableResponse<Response> setResponse;
    private Map<String,String > restPathParam=new HashMap<>();

}

package org.sample.ft.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.sample.ft.data.CommonHeaderParams;


import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.Collections.emptyMap;

public final class RestUtil {
    private static final String WADL_PATH="?_wadl";

    public static void checkApiAvailability(final String restEndPoint){
        setRestRequestConfig();
        RestAssured.baseURI =restEndPoint;
        given().when().log().uri().get(WADL_PATH).then().log().status().statusCode(HTTP_OK);
    }

    public static ExtractableResponse<Response> executeGetRequest(final String resourcePath,final CommonHeaderParams commonHeaderParams){
        setRestRequestConfig();
        return given().urlEncodingEnabled(false)
                .accept(ContentType.JSON)
                .headers(nullSafeMap(commonHeaderParams.getRestPathParam()))
                .queryParams(nullSafeMap(commonHeaderParams.getRestPathParam()))
                .pathParams(nullSafeMap(commonHeaderParams.getRestPathParam()))
                .log()
                .everything()
                .when()
                .get(resourcePath)
                .then()
                .log()
                .everything()
                .extract();
    }
    public static String fromErrorResponse(final CommonHeaderParams commonHeaderParams,final String errorParams){
        // have mapp eroor code and return correspong message as string .
        return "Error Response";
    }

    private static Map<String, ?> nullSafeMap(Map<String, String> restPathParam) {
        return restPathParam!=null ? restPathParam : emptyMap();
    }

    private static void setRestRequestConfig() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config= RestAssuredConfig.config()
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory((clazz,str) ->prepareObjectMapper()));
    }

    public static ObjectMapper prepareObjectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS,true)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}

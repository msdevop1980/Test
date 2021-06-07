package org.sample.integration.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.sample.integration.external.api.SampleApi;
import org.sample.integration.external.api.SoapServiceEndPointPortType;

public class InvokeExternalService {

    //inject both
    JaxRsClient jaxRsClient;
    JaxWsClient jaxWsClient;

    public SoapServiceEndPointPortType callToSaop(){
        JaxWsProxyFactoryBean jaxWsClientFactoryBean=(JaxWsProxyFactoryBean)jaxWsClient.jaxWsClientFactoryBean();
        jaxWsClientFactoryBean.setServiceClass(SoapServiceEndPointPortType.class);
        jaxWsClientFactoryBean.setAddress("integrationUrl".concat("serviceName"));
       // jaxWsClientFactoryBean.setServiceName("Servie. qname");

        return (SoapServiceEndPointPortType)jaxWsClientFactoryBean.create();

    }
    public void callToRest(){

        SampleApi sampleApi= jaxRsClient.clientProxyFactoryBean(SampleApi.class,"resourceBashPath+version","");
        sampleApi.callServiceMethod();
    }
}

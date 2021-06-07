package org.sample.integration.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.collect.Lists;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.local.LocalConduit;
import org.jboss.com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class JaxRsClient {
    private final Bus bus;
    protected  JaxRsClient(@Qualifier("JaxRsClientBus") final Bus bus){
        this.bus=bus;
    }

public  <T> T clientProxyFactoryBean(Class<T> resourceClass, final String resourceBasePath,final String headerInfoObject){
        JAXRSClientFactoryBean jaxrsClientFactoryBean=new JAXRSClientFactoryBean();
    jaxrsClientFactoryBean.setBus(bus);
    jaxrsClientFactoryBean.setAddress(resourceBasePath);
    jaxrsClientFactoryBean.setResourceClass(resourceClass);
   // jaxrsClientFactoryBean.setHeaders("set headers");
    jaxrsClientFactoryBean.setProviders(getProviders());
    // set and in and out intereseptors
    WebClient webClient=jaxrsClientFactoryBean.createWebClient();
    webClient.accept(MediaType.APPLICATION_JSON_TYPE);
    ClientConfiguration clientConfiguration=WebClient.getConfig(webClient);
    clientConfiguration.getRequestContext().put(LocalConduit.DIRECT_DISPATCH,Boolean.TRUE);

    return jaxrsClientFactoryBean.create(resourceClass, Collections.EMPTY_LIST);
}

    private List<Object> getProviders() {
        ObjectMapper objectMapper=getObjectMapper();
        JacksonJaxbJsonProvider jacksonJaxbJsonProvider=new JacksonJaxbJsonProvider(objectMapper,JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
       return Lists.newArrayList(jacksonJaxbJsonProvider);
    }

    private ObjectMapper getObjectMapper() {

        return new ObjectMapper();
    }
}

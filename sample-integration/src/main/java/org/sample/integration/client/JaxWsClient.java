package org.sample.integration.client;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.apache.cxf.Bus;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;

import java.util.ArrayList;
import java.util.Map;

public class JaxWsClient {
    //inject
    //@named("")
    private Bus bus;

    public ClientProxyFactoryBean jaxWsClientFactoryBean(){

        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setBus(bus);
        jaxWsProxyFactoryBean.setProperties(getProp());
        jaxWsProxyFactoryBean.setFeatures(new ArrayList<>(bus.getFeatures()));
        return jaxWsProxyFactoryBean;
    }

    private Map<String, Object> getProp() {
        return Map.of(Message.SCHEMA_VALIDATION_ENABLED,true,Message.EXCEPTION_MESSAGE_CAUSE_ENABLED,true);
    }
}

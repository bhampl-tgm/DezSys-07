package at.ac.tgm.hit.syt.dezsys.hamplwortha.soa;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/knowledge/search/*");
    }

    @Bean(name = "knowledge")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema knowledgeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("KnowledgePort");
        wsdl11Definition.setLocationUri("/knowledge/search");
        wsdl11Definition.setTargetNamespace(KnowledgeEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchema(knowledgeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema knowledgeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("knowledge.xsd"));
    }
}

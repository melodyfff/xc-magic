package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import com.xinchen.guides.producing_web_service.GetCountryRequest;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.*;


/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/5/16 23:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTests {

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @LocalServerPort
    private int port = 0;

    @Before
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetCountryRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    public void testSendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetCountryRequest request = new GetCountryRequest();
        request.setName("Spain");
        assertThat(ws.marshalSendAndReceive("http://localhost:"
                + port + "/ws", request)).isNotNull();

    }
}

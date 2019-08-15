package com.xinchen.spring.beans.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 15/08/2019 21:19
 */
@Component
public class ResourceObject {
    @Value("service.xml")
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}

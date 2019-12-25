package me.icro.java.spring.icrospring.iocv2.io;

import java.net.URL;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-25 2:09 PM
 */
public class ResourceLoader {

    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}

package me.icro.java.spring.icrospring.iocv2.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 定位资源的接口
 *
 * Created by Lin on 2019/12/25.
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}

package me.icro.java.springboot.scaffold.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-15 1:52 PM
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class ResourceNotFoundException2 extends RuntimeException {

    private static final long serialVersionUID = -3867093349073261777L;

    public ResourceNotFoundException2(String message) {
        super(message);
    }
}

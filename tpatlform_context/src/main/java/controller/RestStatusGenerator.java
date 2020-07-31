package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import response.Response;

/**
 * 用于生成SWAGGER文档中的全局状态码
 *
 * @author dehuisun
 */
@RestController
public class RestStatusGenerator {

    @RequestMapping(value = "/status", method = RequestMethod.OPTIONS)
    public Response requireStatusCodes() {
        return null;
    }

}

package org.redrock.shiinaframework.demo;

import org.redrock.shiinaframework.annotation.Autowired;
import org.redrock.shiinaframework.annotation.Controller;
import org.redrock.shiinaframework.annotation.RequestMapper;
import org.redrock.shiinaframework.annotation.HttpMethod;
import org.redrock.shiinaframework.been.ResponseEntity;
import org.redrock.shiinaframework.been.ShiinaContext;

/**
 * @author: Shiina18
 * @date: 2019/3/6 18:03
 * @description: 测试用controller
 */
@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapper(value = "/hello")
    public ResponseEntity hello(ShiinaContext context) {
        return new ResponseEntity(10000, "hello " + context.getParam("name"));
    }

    @RequestMapper(value = "/world")
    public ResponseEntity world(ShiinaContext context) {
        return new ResponseEntity(10000, "hello world!");
    }

    @RequestMapper(value = "/world", method = HttpMethod.POST)
    public ResponseEntity postWorld(ShiinaContext context) {
        return mainService.yoyoyo();
    }
}

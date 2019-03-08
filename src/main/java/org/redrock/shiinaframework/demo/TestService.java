package org.redrock.shiinaframework.demo;

import org.redrock.shiinaframework.annotation.Component;
import org.redrock.shiinaframework.been.ResponseEntity;

/**
 * @author: Shiina18
 * @date: 2019/3/6 19:30
 * @description: 测试用service
 */
@Component
public class TestService {
    public ResponseEntity yoyoyo() {
        return new ResponseEntity(10000, "hello world!(post)");
    }
}

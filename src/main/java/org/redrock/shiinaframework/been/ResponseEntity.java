package org.redrock.shiinaframework.been;

import lombok.Data;

/**
 * @author: Shiina18
 * @date: 2019/3/7 15:14
 * @description: 返回的数据类
 */
@Data
public class ResponseEntity<T> {
    int code;
    String info;
    T data;

    public ResponseEntity(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public ResponseEntity(int code, String info, T t) {
        this.code = code;
        this.info = info;
        this.data = t;
    }
}

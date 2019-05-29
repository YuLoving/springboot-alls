package nj.zj.study.exception;

import lombok.Data;

@Data
public class MyException extends  RuntimeException {

    private final ErrorCodeEnum response;

}

package nj.zj.study.exception;

public enum ErrorCodeEnum {
    AUTHORITY("403", "权限不足，无法访问系统资源"),
    LOGIN_WITHOUT("504", "因为登录超时，无法访问系统资源"),
    FAIL("505", "验证码错误"),
    LOGIN_INCORRECT("506", "用户登录时身份认证失败"),
    LOGIN_FAIL("507", "登录失败"),
    SUCCESS("200", "操作成功"),
    NO_TOKEN("508", "缺少请求头参数,Authorization传递是token值所以参数是必须的"),
    TOKEN_INVALID("509", "用户token无效"),
    ROLE_NULL("102", "角色ID不能为空"),

    PARAM_ERR("103","参数不合法!"),
    NOT_FIND_DATA("101","未查询到数据");


    /**
     * 返回代码
     */
    private String code;

    /**
     * 返回错误信息
     */
    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ErrorCodeEnum getMessage(String code) {

        for (ErrorCodeEnum codeEnum : ErrorCodeEnum.values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }

        return null;
    }
}

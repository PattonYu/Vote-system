package demo.sicau.votesystem.entity.VO;

/**
 * @Author PattonYu
 * @Date 11:25 2018/10/28
 * @Description:
 */
public enum ResultEnumVO {

    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    PARAM_ERROR(10001,"参数错误"),
    LOGIN_ERROR(10002,"登录错误"),
    RESOURCE_NOT_FOUND(10003,"资源不存在"),
    RESOURCE_EXIST(10004,"资源已存在"),
    TIME_OUT(10005,"请求超时");

    private int code;

    private String msg;

    ResultEnumVO(int code,String msg){
        this.code =code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
package demo.sicau.votesystem.entity.VO;

/**
 * @Author PattonYu
 * @Date 14:36 2018/10/28
 * @Description:
 */
public class ResultVO {

    private int code;

    private String msg;

    private Object data;

    public ResultVO(){
        this.code = -1;
        this.msg = null;
        this.data = null;
    }

    public ResultVO( int code,String msg,Object data){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg =msg;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

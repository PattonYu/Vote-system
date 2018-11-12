package demo.sicau.votesystem.util;

import demo.sicau.votesystem.entity.VO.ResultEnumVO;
import demo.sicau.votesystem.entity.VO.ResultVO;
import org.springframework.stereotype.Component;

/**
 * @Author PattonYu
 * @Date 14:38 2018/10/28
 * @Description:
 */
@Component
public class ResultVOUtil {

    /**
     * UNKOWN_ERROR
     * */
    public ResultVO unknownError(){
        return new ResultVO(ResultEnumVO.UNKNOWN_ERROR.getCode(),
                ResultEnumVO.UNKNOWN_ERROR.getMsg());
    }

    public ResultVO unknownError(String msg){
        ResultVO resultVO = this.unknownError();
        resultVO.setMsg(msg);
        return resultVO;
    }

    public ResultVO unknownError(Object data){
        ResultVO resultVO = this.unknownError();
        resultVO.setData(data);
        return resultVO;
    }

    public ResultVO unknownError(String msg,Object data){
        ResultVO resultVO = this.unknownError(data);
        resultVO.setMsg(msg);
        return resultVO;
    }

    /**
     * SUCCESS
     * */
    public ResultVO success(){
        return new ResultVO(ResultEnumVO.SUCCESS.getCode(),
                ResultEnumVO.SUCCESS.getMsg());
    }

    public ResultVO success(String msg){
        ResultVO resultVO = this.success();
        resultVO.setMsg(msg);
        return resultVO;
    }
     public ResultVO success(Object data){
        ResultVO resultVO = this.success();
        resultVO.setData(data);
        return resultVO;
     }
     public ResultVO success(String msg,Object data){
        ResultVO resultVO = this.success(data);
        resultVO.setMsg(msg);
        return resultVO;
     }

    /**
     * PARAM_ERROR
     * */
    public ResultVO paramError(){
        return new ResultVO(ResultEnumVO.PARAM_ERROR.getCode(),
                ResultEnumVO.PARAM_ERROR.getMsg());
    }

    public ResultVO paramError(String msg){
        ResultVO resultVO = this.paramError();
        resultVO.setMsg(msg);
        return resultVO;
    }

    public ResultVO paramError(Object data){
        ResultVO resultVO = this.paramError();
        resultVO.setData(data);
        return resultVO;
    }

    public ResultVO paramError(String msg,Object data){
        ResultVO resultVO = this.paramError(data);
        resultVO.setMsg(msg);
        return resultVO;
    }
    /**
     *LOGIN_ERROR
     * */
    public ResultVO loginError(){
        return new ResultVO(ResultEnumVO.LOGIN_ERROR.getCode(),
                ResultEnumVO.LOGIN_ERROR.getMsg());
    }

    public ResultVO loginError(String msg){
        ResultVO resultVO = this.loginError();
        resultVO.setMsg(msg);
        return resultVO;
    }

    public ResultVO loginError(Object data){
        ResultVO resultVO = this.loginError();
        resultVO.setData(data);
        return resultVO;
    }

    public ResultVO loginError(String msg,Object data){
        ResultVO resultVO = this.loginError(data);
        resultVO.setMsg(msg);
        return resultVO;
    }
    /**
     * RESOURCE_NOT_FOUND
     * */
    public ResultVO resourceNotFound(){
        return new ResultVO(ResultEnumVO.RESOURCE_NOT_FOUND.getCode(),
                ResultEnumVO.RESOURCE_NOT_FOUND.getMsg());
    }

    public ResultVO resourceNotFound(String msg){
        ResultVO resultVO = this.resourceNotFound();
        resultVO.setMsg(msg);
        return resultVO;
    }

    public ResultVO resourceNotFound(Object data){
        ResultVO resultVO = this.resourceNotFound();
        resultVO.setData(data);
        return resultVO;
    }

    public ResultVO resourceNotFound(String msg,Object data){
        ResultVO resultVO = this.resourceNotFound(data);
        resultVO.setMsg(msg);
        return resultVO;
    }
    /**
     * RESOURCE_EXIST
     * */
    public ResultVO resourceExist(){
        return new ResultVO(ResultEnumVO.RESOURCE_EXIST.getCode(),
                ResultEnumVO.RESOURCE_EXIST.getMsg());
    }

    public ResultVO resourceExist(String msg){
        ResultVO resultVO = this.resourceExist();
        resultVO.setMsg(msg);
        return resultVO;
    }

    public ResultVO resourceExist(Object data){
        ResultVO resultVO = this.resourceExist();
        resultVO.setData(data);
        return resultVO;
    }

    public ResultVO resourceExist(String msg,Object data){
        ResultVO resultVO = this.resourceExist(data);
        resultVO.setMsg(msg);
        return resultVO;
    }
}

package demo.sicau.votesystem.service.imp;

import demo.sicau.votesystem.constant.DataConstant;
import demo.sicau.votesystem.dao.Dao;
import demo.sicau.votesystem.entity.PO.UserPO;
import demo.sicau.votesystem.entity.VO.ResultVO;
import demo.sicau.votesystem.service.UserService;
import demo.sicau.votesystem.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author PattonYu
 * @Date 10:30 2018/10/28
 * @Description:
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private Dao dao;

    @Autowired
    private ResultVOUtil resultVOUtil;

    @Override
    public ResultVO getAllUser() {
        try {
            List<UserPO> userPOList = dao.selectUsers();
            if (userPOList.size() == 0) {
                return resultVOUtil.success("没有用户");
            } else {
                return resultVOUtil.success(userPOList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultVOUtil.unknownError();
        }
    }

    @Override
    public ResultVO login(int role, String username, String password, HttpServletRequest request) {

        if (" ".equals(username.trim()) && " ".equals(password.trim())) {
            return resultVOUtil.paramError();
        } else {
            if (role == 0) {
                try {
                    //执行查询操作，可能抛出异常，所以要try catch
                    UserPO userPO = dao.selectAdminByPwdAndUsername(username, password);
                    if (userPO == null) {
                        //查不到用户,即 用户名或密码错误
                        return resultVOUtil.resourceNotFound();
                    } else {
                        //存放用户凭据
                        HttpSession session = request.getSession();
                        //存放用户id到会话Session中，前一个参数是键，后一个是值
                        session.setAttribute(DataConstant.ADMIN_SESSION_NAME, userPO.getId());
                        //用户登陆成功，返回凭据
                        return resultVOUtil.success("管理员登陆成功", userPO.getId());
                    }
                } catch (Exception e) {
                    // 打印异常堆栈信息
                    e.printStackTrace();
                    // 一旦抛出异常就返回未知错误信息
                    return resultVOUtil.unknownError(e.toString());
                }
            } else if (role == 1) {
                try {
                    //执行查询操作，可能抛出异常，所以要try catch
                    UserPO userPO = dao.selectUserByPwdAndUsername(username, password);
                    if (userPO == null) {
                        //查不到用户,即 用户名或密码错误
                        return resultVOUtil.resourceNotFound();
                    } else {
                        //存放用户凭据
                        HttpSession session = request.getSession();
                        //存放用户id到会话Session中，前一个参数是键，后一个是值
                        session.setAttribute(DataConstant.USER_SESSION_NAME, userPO.getId());
                        //用户登陆成功，返回凭据
                        return resultVOUtil.success("用户登陆成功", userPO.getId());
                    }
                } catch (Exception e) {
                    // 打印异常堆栈信息
                    e.printStackTrace();
                    // 一旦抛出异常就返回未知错误信息
                    return resultVOUtil.unknownError(e.toString());
                }
            } else
                return resultVOUtil.paramError();
        }

    }

/*
    @Override
    public ResultVO getUser(int page, int limit) {

        //判断用户是否登陆，并且是否拥有权限
        try{
            Map<String,Object> resMap = new HashMap<>();
            // 查询数据库总共有多少个用户
            int count = dao.selectUserCount();
            resMap.put("count",count);
            if(count == 0){
                // 如果数据库中没用用户，就不同进行分页查找
                return resultVOUtil.success(resMap);
            }else{
                // 分页查询用户列表
                List<UserPO> userPOList = dao.selectUserByPaging((page - 1) * limit,limit);
                resMap.put("users",userPOList);
                return resultVOUtil.success(resMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultVOUtil.unknownError(e.getMessage());
        }

    }

    @Override
    public ResultVO deleteUserById(String uid) {
        if("".equals(uid.trim())){
            return resultVOUtil.paramError();
        }else {
            try{
                UserPO userPO = dao.selectUserById(uid);
                if(userPO == null){
                    return resultVOUtil.resourceNotFound("该用户不存在");
                }else{
                    // 如果不为空表示该用户存在，继续进行删除操作
                    boolean hasDelete = dao.deleteUserById(uid);
                    if (hasDelete){
                        return resultVOUtil.success("删除用户成功!");
                    }else{
                        return resultVOUtil.unknownError();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                return resultVOUtil.unknownError(e.getMessage());
            }
        }
    }

    @Override
    public ResultVO addUser(UserPO userPO) {
        try{
            // 先查询数据库中是否有这一个username所对应的用户
           UserPO foundUser = dao.selectUserByUsername(userPO.getUsername());
            if(foundUser == null){
                boolean hasInsert = dao.insertUser(userPO);
                if (hasInsert){
                    return resultVOUtil.success();
                }else {
                    return resultVOUtil.unknownError();
                }
            }else {
                return resultVOUtil.resourceExist("创建失败,该用户已存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return resultVOUtil.unknownError(e.getMessage());
        }
    }

    @Override
    public ResultVO updateUser(UserPO userPO) {
        try{
            // 先查询数据库中是否有这一个username所对应的用户
            UserPO foundUser = dao.selectUserByUsername(userPO.getUsername());
            // 如果数据库中没有这个用户则不进行修改
            if(foundUser == null){
                return resultVOUtil.resourceNotFound("修改失败，该用户不存在");

            }else {
                boolean hasUpdate = dao.updateUser(userPO);
                if (hasUpdate){
                    return resultVOUtil.success();
                }else {
                    return resultVOUtil.unknownError();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return resultVOUtil.unknownError(e.getMessage());
        }
    }
*/

}



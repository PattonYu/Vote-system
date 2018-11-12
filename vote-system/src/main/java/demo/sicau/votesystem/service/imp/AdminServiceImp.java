package demo.sicau.votesystem.service.imp;

import demo.sicau.votesystem.dao.Dao;
import demo.sicau.votesystem.entity.PO.UserPO;
import demo.sicau.votesystem.entity.VO.ResultVO;
import demo.sicau.votesystem.service.AdminService;
import demo.sicau.votesystem.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PattonYu
 * @date 2018/11/4 19:31
 * Description:
 */
@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private Dao dao;

    @Autowired
    private ResultVOUtil resultVOUtil;

    @Override
    public ResultVO getUser(int page, int limit) {
        //判断用户是否登陆，并且是否拥有权限
        try {
            Map<String, Object> resMap = new HashMap<>();
            // 查询数据库总共有多少个用户
            int count = dao.selectUserCount();
            resMap.put("count", count);
            if (count == 0) {
                // 如果数据库中没用用户，就不同进行分页查找
                return resultVOUtil.success(resMap);
            } else {
                // 分页查询用户列表
                List<UserPO> userPOList = dao.selectUserByPaging((page - 1) * limit, limit);
                resMap.put("users", userPOList);
                return resultVOUtil.success(resMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultVOUtil.unknownError(e.getMessage());
        }
    }

    @Override
    public ResultVO deleteUserById(String uid) {
        if ("".equals(uid.trim())) {
            return resultVOUtil.paramError();
        } else {
            try {
                UserPO userPO = dao.selectUserById(uid);
                if (userPO == null) {
                    return resultVOUtil.resourceNotFound("该用户不存在");
                } else {
                    // 如果不为空表示该用户存在，继续进行删除操作
                    boolean hasDelete = dao.deleteUserById(uid);
                    if (hasDelete) {
                        return resultVOUtil.success("删除用户成功!");
                    } else {
                        return resultVOUtil.unknownError();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return resultVOUtil.unknownError(e.getMessage());
            }
        }
    }

    @Override
    public ResultVO addUser(UserPO userPO) {
        try {
            // 先查询数据库中是否有这一个username所对应的用户
            UserPO foundUser = dao.selectUserByUsername(userPO.getUsername());
            if (foundUser == null) {
                boolean hasInsert = dao.insertUser(userPO);
                if (hasInsert) {
                    return resultVOUtil.success();
                } else {
                    return resultVOUtil.unknownError();
                }
            } else {
                return resultVOUtil.resourceExist("创建失败,该用户已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultVOUtil.unknownError(e.getMessage());
        }
    }


    @Override
    public ResultVO updateUser(UserPO userPO) {
        try{
            // 先查询数据库中是否有这一个username所对应的用户
            UserPO foundUser = dao.selectUserById(userPO.getId());
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
}

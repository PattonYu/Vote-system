package demo.sicau.votesystem.service;

import demo.sicau.votesystem.entity.PO.UserPO;
import demo.sicau.votesystem.entity.VO.ResultVO;

/**
 * @author PattonYu
 * @date 2018/11/4 17:12
 * Description:
 */
public interface AdminService {

    ResultVO getUser(int page, int limit);

    ResultVO deleteUserById(String uid);

    ResultVO addUser(UserPO userPO);

    ResultVO updateUser(UserPO userPO);

}

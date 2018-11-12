package demo.sicau.votesystem.service;

import demo.sicau.votesystem.entity.VO.ResultVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author PattonYu
 * @Date 8:58 2018/10/28
 * @Description:
 */

public interface UserService {

    ResultVO getAllUser();

    ResultVO login(int role, String username, String password, HttpServletRequest request);

//    ResultVO getUser(int page, int limit);
//
//    ResultVO deleteUserById(String uid);
//
//    ResultVO addUser(UserPO userPO);
//
//    ResultVO updateUser(UserPO userPO);
}

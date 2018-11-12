package demo.sicau.votesystem.dao;

import demo.sicau.votesystem.entity.PO.PlayerPO;
import demo.sicau.votesystem.entity.PO.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author PattonYu
 * @Date 8:57 2018/10/28
 * @Description:
 */
@Mapper
public interface Dao {

    List<UserPO> selectUsers();

    UserPO selectUserByPwdAndUsername(@Param("username") String username,
                                      @Param("password") String password);
    List<UserPO> selectAdmins();

    UserPO selectAdminByPwdAndUsername(@Param("username") String username,
                                      @Param("password") String password);

    List<UserPO> selectUserByPaging(@Param("index") int page,@Param("limit") int limit);

    int selectUserCount();

    UserPO selectUserById(@Param("uid") String uid);

    boolean deleteUserById(@Param("uid") String uid);

    boolean insertUser(@Param("userPO") UserPO userPO);

    boolean updateUser(@Param("userPO") UserPO userPO);

    UserPO selectUserByUsername(@Param("username") String username);

    int selectPlayerCount();

    List<PlayerPO> selectPlayerByPaging(@Param("index") int page,@Param("limit") int limit);

    PlayerPO selectPlayerByRealName(@Param("realName") String realName);

    boolean insertPlayer(@Param("playerPO") PlayerPO playerPO);

    PlayerPO selectPlayerById(@Param("pid") String pid);

    boolean deletePlayerById(@Param("pid") String pid);

    boolean updatePlayer(@Param("playerPO") PlayerPO playerPO);

    boolean updateVoteById(@Param("pid") String pid);

    //UserPO updateUserOperated(@Param("uid") String uid,@Param("pid") String pid);

}

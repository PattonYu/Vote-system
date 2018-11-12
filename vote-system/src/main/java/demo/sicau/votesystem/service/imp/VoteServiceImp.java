package demo.sicau.votesystem.service.imp;

import demo.sicau.votesystem.dao.Dao;
import demo.sicau.votesystem.entity.PO.PlayerPO;
import demo.sicau.votesystem.entity.PO.UserPO;
import demo.sicau.votesystem.entity.VO.ResultVO;
import demo.sicau.votesystem.service.VoteService;
import demo.sicau.votesystem.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PattonYu
 * @date 2018/11/6 23:10
 * Description:
 */
@Service
public class VoteServiceImp implements VoteService {

    @Autowired
    private Dao dao;

    @Autowired
    private ResultVOUtil resultVOUtil;

    @Override
    public ResultVO getPlayer(int page, int limit) {
        //判断用户是否登陆，并且是否拥有权限
        try {
            Map<String, Object> resMap = new HashMap<>();
            int count = dao.selectPlayerCount();
            resMap.put("count", count);
            if (count == 0) {
                return resultVOUtil.success(resMap);
            } else {
                List<PlayerPO> playerPOList = dao.selectPlayerByPaging((page - 1) * limit, limit);
                resMap.put("players", playerPOList);
                return resultVOUtil.success(resMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultVOUtil.unknownError(e.getMessage());
        }
    }

    @Override
    public ResultVO addPlayer(PlayerPO playerPO) {
        try {
            PlayerPO foundUser = dao.selectPlayerByRealName(playerPO.getRealName());
            if (foundUser == null) {
                boolean hasInsert = dao.insertPlayer(playerPO);
                if (hasInsert) {
                    return resultVOUtil.success();
                } else {
                    return resultVOUtil.unknownError();
                }
            } else {
                return resultVOUtil.resourceExist("创建失败,该选手已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultVOUtil.unknownError(e.getMessage());
        }
    }

    @Override
    public ResultVO deletePlayerById(String pid) {
        if ("".equals(pid.trim())) {
            return resultVOUtil.paramError();
        } else {
            try {
                PlayerPO playerPO = dao.selectPlayerById(pid);
                if (playerPO == null) {
                    return resultVOUtil.resourceNotFound("该选手不存在");
                } else {
                    boolean hasDelete = dao.deletePlayerById(pid);
                    if (hasDelete) {
                        return resultVOUtil.success("删除选手成功!");
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
    public ResultVO updatePlayer(PlayerPO playerPO) {
        try {
            PlayerPO foundPlayer = dao.selectPlayerById(playerPO.getId());
            if (foundPlayer == null) {
                return resultVOUtil.resourceNotFound("修改失败，该选手不存在");
            } else {
                boolean hasUpdate = dao.updatePlayer(playerPO);
                if (hasUpdate) {
                    return resultVOUtil.success();
                } else {
                    return resultVOUtil.unknownError();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultVOUtil.unknownError(e.getMessage());
        }
    }

    @Override
    public ResultVO addVote(String pid) {
        if ("".equals(pid.trim())) {
            return resultVOUtil.paramError();
        } else {
            try {
                PlayerPO playerPO = dao.selectPlayerById(pid);
                if (playerPO == null) {
                    return resultVOUtil.resourceNotFound("该选手不存在");
                } else {
                    boolean hasVote = dao.updateVoteById(pid);
                    if (hasVote) {
                        return resultVOUtil.success("投票成功!");
                    } else {
                        return resultVOUtil.resourceNotFound("该选手不存在");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return resultVOUtil.unknownError(e.getMessage());
            }
        }
    }
//    @Override
//    public ResultVO addVote(String uid, String pid) {
//        if ("".equals(pid.trim())) {
//            return resultVOUtil.paramError();
//        } else {
//            try {
//               // UserPO userPO = dao.updateUserOperated(uid,pid);
//                PlayerPO playerPO = dao.addVoteById(pid);
//                if (playerPO == null) {
//                    return resultVOUtil.resourceNotFound("该选手不存在");
//                } else {
//                    boolean hasDelete = dao.deletePlayerById(pid);
//                    if (hasDelete) {
//                        return resultVOUtil.success("投票成功!");
//                    } else {
//                        return resultVOUtil.unknownError();
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return resultVOUtil.unknownError(e.getMessage());
//            }
//        }
//
//    }



}

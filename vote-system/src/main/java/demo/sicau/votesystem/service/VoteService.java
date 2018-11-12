package demo.sicau.votesystem.service;

import demo.sicau.votesystem.entity.PO.PlayerPO;
import demo.sicau.votesystem.entity.VO.ResultVO;

/**
 * @author PattonYu
 * @date 2018/11/6 22:32
 * Description:
 */
public interface VoteService {

    ResultVO getPlayer(int page, int limit);

    ResultVO addPlayer(PlayerPO playerPO);

    ResultVO deletePlayerById(String pid);

    ResultVO updatePlayer(PlayerPO playerPO);

//    ResultVO addVote(String uid,String pid);

    ResultVO addVote(String pid);
}

package demo.sicau.votesystem.controller;

import demo.sicau.votesystem.entity.PO.PlayerPO;
import demo.sicau.votesystem.entity.VO.ResultVO;
import demo.sicau.votesystem.service.VoteService;
import demo.sicau.votesystem.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author PattonYu
 * @date 2018/11/6 22:30
 * Description:
 */
@RequestMapping("/vote")
@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/getPlayer")
    public ResultVO getPlayer(@RequestParam("page") int page,
                              @RequestParam("limit") int limit) {
        return voteService.getPlayer(page, limit);
    }

    @PostMapping("/addPlayer")
    public ResultVO addPlayer(@RequestParam("realName") String realName,
                              @RequestParam("profile") String profile){
        PlayerPO playerPO = new PlayerPO(IDUtil.getUUID(),realName,profile);
        return voteService.addPlayer(playerPO);
    }

    @PostMapping("/deletePlayerById")
    public ResultVO deletePlayer(@RequestParam("pid") String pid){
        return voteService.deletePlayerById(pid);
    }

    @PostMapping("/updatePlayer")
    public ResultVO updatePlayer(@RequestParam("pid") String pid,
                               @RequestParam("realName") String realName,
                               @RequestParam("profile") String profile){
        PlayerPO playerPO = new PlayerPO(pid,realName,profile);
        return voteService.updatePlayer(playerPO);
    }

    @PostMapping("/getVote")
    public ResultVO getVote(@RequestParam("pid") String pid){
        return voteService.addVote(pid);
    }
//    @PostMapping("/getVote")
//    public ResultVO getVote(@RequestParam("uid") String uid,@RequestParam("pid") String pid){
//        return voteService.addVote(uid,pid);
//    }


}

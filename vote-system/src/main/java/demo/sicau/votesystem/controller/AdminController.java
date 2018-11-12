package demo.sicau.votesystem.controller;

import demo.sicau.votesystem.entity.PO.UserPO;
import demo.sicau.votesystem.entity.VO.ResultVO;
import demo.sicau.votesystem.service.AdminService;
import demo.sicau.votesystem.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author PattonYu
 * @date 2018/11/4 17:16
 * Description:
 */
@RequestMapping("/admin")
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/getUser")
    public ResultVO getUser(@RequestParam("page") int page,
                            @RequestParam("limit") int limit) {
        return adminService.getUser(page, limit);
    }

    @PostMapping("/deleteUserById")
    public ResultVO deleteUserById(@RequestParam("uid") String uid){
        return adminService.deleteUserById(uid);
    }

    @PostMapping("/addUser")
    public ResultVO addUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("realName") String realName,
                            @RequestParam(name = "email",required = false) String email){
        UserPO userPO = new UserPO(IDUtil.getUUID(),username,password,realName,email);
        return adminService.addUser(userPO);
    }

    @PostMapping("/updateUser")
    public ResultVO updateUser(@RequestParam("uid") String uid,
                               @RequestParam("password") String password,
                               @RequestParam("realName") String realName,
                               @RequestParam(name = "email",required = false) String email){
        UserPO userPO = new UserPO(uid,password,realName,email);
        return adminService.updateUser(userPO);
    }
}

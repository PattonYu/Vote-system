package demo.sicau.votesystem.controller;

import demo.sicau.votesystem.entity.VO.ResultVO;
import demo.sicau.votesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author PattonYu
 * @Date  2018/10/28 10:24
 * @Description:
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public ResultVO getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public ResultVO login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("role") int role,
                          HttpServletRequest request) {
        return userService.login(role, username, password, request);
    }
    /*
    @GetMapping("/getUser")
    public ResultVO getUser(@RequestParam("page") int page,
                            @RequestParam("limit") int limit) {
        return userService.getUser(page, limit);
    }

    @PostMapping("/deleteUserById")
    public ResultVO deleteUserById(@RequestParam("uid") String uid){
        return userService.deleteUserById(uid);
    }

    @PostMapping("/addUser")
    public ResultVO addUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("realName") String realName,
                            @RequestParam(name = "email",required = false) String email){
        UserPO userPO = new UserPO(IDUtil.getUUID(),username,password,realName,email);
        return userService.addUser(userPO);
    }

    @PostMapping("/updateUser")
    public ResultVO updateUser(@RequestParam("uid") String uid,
                               @RequestParam("password") String password,
                               @RequestParam("realName") String realName,
                               @RequestParam(name = "email",required = false) String email){
        UserPO userPO = new UserPO(uid,password,realName,email);
        return userService.updateUser(userPO);
    }
    */
}

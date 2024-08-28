package org.example.book.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.example.book.common.WebContext;
import org.example.book.dao.entity.User;
import org.example.book.service.UserService;
import org.example.book.util.RedisUtils;
import org.example.book.util.Result;
import org.example.book.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private WebContext webContext;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/Register")
    public Result Register(@RequestBody User user) {

        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return new Result(400, "用户名或者密码为空");
        }

        if (ObjectUtil.isNotEmpty(userService.getUserByName(user))) {
            return new Result(400, "用户名已存在");
        }

        userService.add(user);

        return new Result(200, "注册成功");
    }


    @PostMapping("/Login")
    public Result login(@RequestBody User user) {
        String token = "";
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return new Result(400, "用户名或者密码为空");
        }
         user = userService.getUser(user);
        if (ObjectUtil.isNotEmpty(user)) {
            token = tokenUtil.generateToken(user.getUsername(),user.getId());
            redisUtils.set(user.getUsername(),token);
            return new Result(200, token);
        }
        return new Result(400, "用户不存在");

    }


    @GetMapping("/LoginOut")
    public Result LoginOut(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7); // 去掉"Bearer "前缀
            // 验证token是否有效
            if (tokenUtil.validateToken(token)) {
                String username = tokenUtil.getUsernameFromToken(token);
                redisUtils.delete(username);
                return new Result(200, "退出成功");
            }
        }
        return new Result(400, "退出失败");
    }
}

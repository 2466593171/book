package org.example.book.service;

import org.example.book.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
public interface UserService extends IService<User> {

    User getUserByName(User user);

    void add(User user);

    User getUser(User user);
}

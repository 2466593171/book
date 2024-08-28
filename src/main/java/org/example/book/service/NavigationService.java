package org.example.book.service;

import org.example.book.dao.entity.Navigation;
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
public interface NavigationService extends IService<Navigation> {

    public void add(Navigation navigation);

    public void addList(List<Navigation> navigations);
}

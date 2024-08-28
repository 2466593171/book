package org.example.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.book.dao.entity.Navigation;
import org.example.book.dao.mapper.NavigationMapper;
import org.example.book.service.NavigationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
@Service
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, Navigation> implements NavigationService {

    @Autowired
    NavigationMapper navigationMapper;

    @Override
    public void add(Navigation navigation) {
        navigationMapper.insert(navigation);
    }

    @Override
    public void addList(List<Navigation> navigations) {
        navigationMapper.addList(navigations);
    }
}

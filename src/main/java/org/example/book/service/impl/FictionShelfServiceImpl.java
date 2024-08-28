package org.example.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.example.book.dao.entity.FictionShelf;
import org.example.book.dao.mapper.FictionShelfMapper;
import org.example.book.service.FictionShelfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class FictionShelfServiceImpl extends ServiceImpl<FictionShelfMapper, FictionShelf> implements FictionShelfService {

    @Resource
    private FictionShelfMapper fictionShelfMapper;
    @Override
    public List<FictionShelf> getFictionByUserId(Long id) {
        LambdaQueryWrapper<FictionShelf> queryWrapper = new LambdaQueryWrapper<FictionShelf>();
        queryWrapper.eq(FictionShelf::getUserId,id);
        return fictionShelfMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteFictionByFictionIds(List<Long> collect) {
        LambdaQueryWrapper<FictionShelf> queryWrapper = new LambdaQueryWrapper<FictionShelf>();
        queryWrapper.in(FictionShelf::getFictionId,collect);
        fictionShelfMapper.delete(queryWrapper);
    }
}

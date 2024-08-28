package org.example.book.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.book.dao.entity.Fiction;
import org.example.book.dao.entity.FictionShelf;
import org.example.book.dao.mapper.FictionMapper;
import org.example.book.dao.mapper.FictionShelfMapper;
import org.example.book.service.FictionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
@Slf4j
@Service
public class FictionServiceImpl extends ServiceImpl<FictionMapper, Fiction> implements FictionService {

    @Autowired
    private FictionShelfMapper fictionShelfMapper;

    @Autowired
    private FictionMapper fictionMapper;

    public void add(Fiction fiction) {
        try {
            // 在数据库中插入fiction对象
            boolean fictionInserted = fictionMapper.insert(fiction) > 0;
            if (!fictionInserted) {
                throw new RuntimeException("插入fiction对象失败");
            }

            // 创建FictionShelf对象，并设置fictionId属性
            FictionShelf fictionShelf = FictionShelf.of().setFictionId(fiction.getId()).setUserId(fiction.getUserId());

            // 插入fictionShelf对象到数据库中
            fictionShelfMapper.insert(fictionShelf);

        } catch (Exception e) {
            log.error("添加书架异常"+e.getMessage());
            // 如果过程中发生任何异常，事务会自动回滚，确保fictionShelf不会被错误地插入
            throw e; // 再次抛出异常以便上层调用者可以捕获
        }
    }
}

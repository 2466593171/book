package org.example.book.service;

import org.example.book.dao.entity.Fiction;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
public interface FictionService extends IService<Fiction> {
    @Transactional(rollbackFor = Exception.class) // 添加事务管理，确保操作的原子性
    void add(Fiction fiction);
}

package org.example.book.service;

import org.example.book.dao.entity.FictionShelf;
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
public interface FictionShelfService extends IService<FictionShelf> {

    List<FictionShelf> getFictionByUserId(Long id);

    void deleteFictionByFictionIds(List<Long> collect);
}

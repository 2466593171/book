package org.example.book.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.book.dao.entity.Navigation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
public interface NavigationMapper extends BaseMapper<Navigation> {
    public void addList(@Param("Navigations") List<Navigation> Navigations);

    public void add(@Param("Navigations") List<Navigation> Navigations);
}

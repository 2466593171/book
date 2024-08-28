package org.example.book.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import jakarta.annotation.Resource;
import org.example.book.common.WebContext;
import org.example.book.dao.entity.Fiction;
import org.example.book.dao.entity.FictionShelf;
import org.example.book.dao.entity.Navigation;
import org.example.book.dao.entity.User;
import org.example.book.service.FictionService;
import org.example.book.service.FictionShelfService;
import org.example.book.util.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
@RestController
@RequestMapping("/fiction")
public class FictionController {
    @Resource
    private WebContext webContext;
    @Resource
    private FictionService fictionService;
    @Resource
    private FictionShelfService fictionShelfService;
    @PostMapping("/add")
    public Result add(@RequestBody Fiction fiction) {
        fiction.setUserId(webContext.getCurrentUser().getId());
        fiction.setCreateDate(LocalDateTimeUtil.now());
        fictionService.add(fiction);
        return new Result(200, "添加成功");
    }

    @DeleteMapping("/delete")
    @Transactional
    public Result delete(@RequestParam(value = "ids", required = true) String ids) {

        List<Long> collect = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        fictionService.removeByIds(collect);

        fictionShelfService.deleteFictionByFictionIds(collect);
        return new Result(200, "删除成功");
    }
}

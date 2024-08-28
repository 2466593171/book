package org.example.book.controller;
import jakarta.annotation.Resource;
import org.example.book.common.WebContext;
import org.example.book.dao.entity.Fiction;
import org.example.book.dao.entity.FictionShelf;
import org.example.book.dao.entity.User;
import org.example.book.service.FictionService;
import org.example.book.service.FictionShelfService;
import cn.hutool.core.collection.CollectionUtil;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/fictionShelf")
public class FictionShelfController {
    @Resource
    private WebContext webContext;

    @Resource
    private FictionShelfService fictionShelfService;

    @Resource
    private FictionService fictionService;

    @GetMapping("/get")
    public List<Fiction> get() {

        User user = webContext.getCurrentUser();

        List<Fiction> fictions=null;

        List<FictionShelf> fictionShelfList = fictionShelfService.getFictionByUserId(user.getId());

        if(CollectionUtil.isNotEmpty(fictionShelfList)){

            List<Long> fictionIds = fictionShelfList.stream()
                    .map(FictionShelf::getFictionId)
                    .collect(Collectors.toList());

             fictions = fictionService.listByIds(fictionIds);
        }

        return fictions;
    }

}

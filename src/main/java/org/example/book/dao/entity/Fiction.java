package org.example.book.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Fiction implements Serializable {

    public static Fiction of(){
        return new Fiction();
    }

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private java.lang.Long id;

    private String fictionName;

    private LocalDateTime createDate;

    private String author;

    private String type;

    private String newest;

    private String state;

    private String number;

    private String brief;

    private String imgUrl;

    private String fictionUrl;

    private Integer views;

    @TableField(exist = false)
    private Long userId;

    @TableField(exist = false)
    private List<Chapter> chapterList;
}

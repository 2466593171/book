package org.example.book.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
@TableName("chapter_content")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChapterContent implements Serializable {

    public static ChapterContent of(){
        return new ChapterContent();
    }

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private java.lang.Long id;

    private String content;

    private String chapterName;

    private String prechapter;

    private String nextchapter;

    private String directory;

}

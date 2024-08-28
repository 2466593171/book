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
@TableName("fiction_shelf")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FictionShelf implements Serializable {

    private static final long serialVersionUID = 1L;

    public static FictionShelf of(){
        return new FictionShelf();
    }

    @TableId(value = "id", type = IdType.AUTO)
    private java.lang.Long id;

    private java.lang.Long fictionId;

    private Integer sort;

    private java.lang.Long userId;

}

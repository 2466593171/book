package org.example.book.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Navigation implements Serializable {

    public static Navigation of(){
        return new Navigation();
    }

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private java.lang.Long id;
    private String href;
    private String type;
    private String page;


}

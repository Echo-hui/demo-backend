package com.example.demobackend.mapper;

import com.example.demobackend.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author wh
 * @Date 2025/1/22
 */
@Mapper
public interface UserMapper {

    User selectUserById(Integer id);

    @Select("select * from user")
    List<User> getAll();

    @Delete("delete from user where id= #{id}")
    void deleteUserById(Integer id);

    void insertUser(User user);

}

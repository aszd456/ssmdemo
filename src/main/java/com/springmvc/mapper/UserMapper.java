package com.springmvc.mapper;

import com.springmvc.domain.TbUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Insert("INSERT INTO TB_USER(user_name,password,name,sex,age,birthday,created) " +
            "values(#{userName},#{password},#{name},#{sex},#{age},#{birthday},#{created})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveUser(TbUser user);

    @Delete("delete from tb_user where id = #{id}")
    int removeUser(@Param("id") Integer id);

    @Update("update tb_user set name=#{name},sex=#{sex},age=#{age} where id = #{id}")
    void modifyUser(TbUser user);

    @Select("select * from tb_user where id = #{id}")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "age", property = "age"),
            @Result(column = "user_name", property = "userName")
    })
    TbUser selectUserById(Integer id);

    @Select("select * from tb_user")
    List<TbUser> selectAllUser();
}

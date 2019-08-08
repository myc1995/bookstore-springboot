package com.myc.mapper;

import com.myc.entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from user where email=#{email}")
    public boolean emailIsNotExist(String email);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(email,password)values(email,password)")
    public int register(User user);

    @Select("select * from user where email=#{email} and password=#{password}")
    public User login(String email, String password);

    @Update("update user set password=#{newpassword} where email=#{email}")
    public int updatePassword(String email, String newpassword);

    @Select("select * from user where email=#{email} and password=#{password}")
    public boolean passwordIsNotRight(String email, String password);
}

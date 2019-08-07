package com.myc.mapper;

import com.myc.bean.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Select("select * from address where userId =#{userId} order by isDefault desc,createTime desc")
    public List<Address> list(int userId);

    @Select("select count(*) from address where userId=#{userId}")
    public int count(int userId);

    @Update("update address set isDefault=false where userid=#{userId} and isDefault=true")
    public int cancelExistingDefault(int userId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into address values(#{address})")
    public int add(Address address);

    @Select("select * from address where userId=#{userId}")
    public List<Address> findById(int userId);
}

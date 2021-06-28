package jp.co.internous.kabuki.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.kabuki.model.domain.MstUser;

@Mapper
public interface MstUserMapper {
	@Insert("INSERT INTO mst_user ("
			+ "user_name, password, "
			+ "family_name, first_name, family_name_kana, first_name_kana, "
			+ "gender"
			+ ") "
			+ "VALUES ("
			+ "#{userName}, #{password}, "
			+ "#{familyName}, #{firstName}, #{familyNameKana}, #{firstNameKana}, "
			+ "#{gender}"
			+ ")")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(MstUser user);
	
	@Select("SELECT count(id) FROM mst_user WHERE user_name = #{userName}")
	int findCountByUserName(@Param("userName") String userName);
	
	@Select ("SELECT * from mst_user where user_name = #{userName} AND password = #{password}")
	List<MstUser> findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
	
	@Select("SELECT * from mst_user where user_name = #{userName} AND password = #{password}")
	MstUser checkUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
	
	@Update("UPDATE mst_user set password = #{newPassword} where user_name = #{userName} AND password = #{password}")
	void updatePassword(@Param("userName") String userName, @Param("password") String password, @Param("newPassword") String newPassword);
	
	@Select ("SELECT user_name from mst_user where user_name = #{userName}")
	boolean checkUserName(@Param("userName") String userName);
	
	@Select("SELECT family_name, first_name from mst_user where id = #{userId}")
	MstUser mstUser(@Param("userId") Long userId);
	
	@Select("SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUsernameAndPassword(
			@Param("userName") String userName,
			@Param("password") String password);
}
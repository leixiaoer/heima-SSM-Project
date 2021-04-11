package Cn.Lei.Dao;

import Cn.Lei.Domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 会员表
 */
@Repository
public interface IMemberDao {

    @Select("select * from member where id=#{memberId}")
    public Member findById(String memberId) throws Exception;

}

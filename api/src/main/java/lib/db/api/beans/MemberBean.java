package lib.db.api.beans;

import java.sql.SQLException;

import lib.db.api.dao.MemberDAO;
import lib.db.api.objects.Member;

public class MemberBean {
    
    public MemberBean(){}

    public void addMember(Member member) throws SQLException{
        MemberDAO dao = new MemberDAO();
        dao.addMember(member);
    }

    public Member loginMember(Member member) throws SQLException{
        MemberDAO dao = new MemberDAO();
        return dao.loginMember(member);
    }

}

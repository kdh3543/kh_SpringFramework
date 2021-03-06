package kh.spring.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int idCheck(String id) {
		return mybatis.selectOne("Member.selectIdCheck",id);
	}
	
	public int insert(MemberDTO dto) {
		return mybatis.insert("Member.insert",dto);
	}
	
	public int login(String id, String pw) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		return mybatis.selectOne("Member.selectLogin",map);
	}
	
	public int delete(String id) {
		return mybatis.delete("Member.delete",id);
	}
	
	public MemberDTO selectById(String id) {
		return mybatis.selectOne("Member.selectById",id);
	}
	
	public int update(MemberDTO dto) {
		return mybatis.update("Member.update",dto);
	}
	//	@Autowired
//	private JdbcTemplate jdbc;
//	
//	public int idCheck(String id) throws Exception{
//        String sql = "select count(*) from member where id = ?";
//        return jdbc.queryForObject(sql, Integer.class, id);
//    }
//	
//	public int insert(MemberDTO dto)throws Exception{
//		String sql = "insert into member values(?,?,?,?,?,?,?,?,sysdate)";
//		return jdbc.update(sql, dto.getId(),dto.getPw(),dto.getName(),dto.getPhone(),dto.getEmail(),dto.getZipcode(),dto.getAddress1(),dto.getAddress2());		
//	}
//	
//	public int login(String id, String pw) throws Exception{
//		String sql = "select count(*) from member where id = ? and pw = ?";
//		return jdbc.queryForObject(sql, Integer.class,id,pw);
//	}
//	
//	public int delete(String id) throws Exception{
//		String sql = "delete from member where id = ?";
//		return jdbc.update(sql, id);
//	}
//	
//	public MemberDTO selectById(String id) throws Exception{
//		String sql = "select * from member where id=?";
//		
//		return jdbc.queryForObject(sql, new RowMapper<MemberDTO>(){
//			@Override
//			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				MemberDTO dto = new MemberDTO();
//				dto.setId(rs.getString("id"));
//				dto.setName(rs.getString("name"));
//				dto.setPhone(rs.getString("phone"));
//				dto.setEmail(rs.getString("email"));
//				dto.setZipcode(rs.getString("zipcode"));
//				dto.setAddress1(rs.getString("address1"));
//				dto.setAddress2(rs.getString("address2"));
//				dto.setSignup_date(rs.getDate("signup_date"));
//				
//				return dto;
//			}
//		},id);
//	}
//	public int update(MemberDTO dto,String id) throws Exception{
//		String sql = "update member set name=?, phone=?, email=?, zipcode=?, address1=?, address2=? where id=?";
//		return jdbc.update(sql,dto.getName(),dto.getPhone(),dto.getEmail(),
//				dto.getZipcode(),dto.getAddress1(),dto.getAddress2(),id);
//	}
}

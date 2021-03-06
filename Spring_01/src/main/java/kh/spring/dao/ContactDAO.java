package kh.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.ContactDTO;

@Repository
public class ContactDAO {
	
	//MyBatis 방식
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(ContactDTO dto) {
		return mybatis.insert("Contact.insert",dto);
		//Contact라는 namespace에서 insert를 사용하겠다라는 뜻
	}
	
	public List<ContactDTO> selectAll(){
		return mybatis.selectList("Contact.selectAll");
	}
	
	public int selectCount() {
		return mybatis.selectOne("Contact.selectCount");
	}
	
	public int deleteBySeq(int seq) {
		return mybatis.delete("Contact.deleteBySeq",seq);
	}
	
	public int update(String column, String value, int seq) {
		
		Map<String,String> map = new HashMap<>();
		map.put("column", column);
		map.put("value", value);
		map.put("seq", String.valueOf(seq));
		return mybatis.update("Contact.update",map);
	}
	
	public List<ContactDTO> search(int seq){
		return mybatis.selectList("Contact.selectBySeq", seq);
		//return mybatis.selectOne("Contact.selectByKeyword",keyword);
	}
	
	public List<ContactDTO> searchByMultiCon(ContactDTO dto){
		return mybatis.selectList("Contact.searchByMultiCon",dto);
	}
	// Spring JDBC
	
//	@Autowired
//	private JdbcTemplate jdbc;
//	// dependency Injection(DI)
//	
//	public int insert(ContactDTO dto) throws Exception{
//		String sql = "insert into contact values(contact_seq.nextval,?,?)";
//		return jdbc.update(sql, dto.getName(),dto.getContact());
//	}
//	
//	public int delete(int seq) throws Exception{
//		String sql = "delete from contact where seq = ?";
//		return jdbc.update(sql,seq);		
//	}
//	
//	public int update(ContactDTO dto) throws Exception{
//		String sql = "update contact set name=?, contact=? where seq=?";
//		return jdbc.update(sql,dto.getName(),dto.getContact(),dto.getSeq());
//	}
//	
//	public int selectCount() throws Exception{
//		String sql = "select count(*) from contact";
//		return jdbc.queryForObject(sql, Integer.class);
//	}
//	
//	public List<ContactDTO> selectAll() throws Exception{
//		String sql = "select * from contact order by 1";
//		return jdbc.query(sql, new RowMapper<ContactDTO>() {
//			@Override
//			public ContactDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				ContactDTO dto = new ContactDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setName(rs.getString("name"));
//				dto.setContact(rs.getString("contact"));
//				return dto;
//			}
//		});
//	}
//	
//	public List<ContactDTO> search(int seq) throws Exception{
//		String sql = "select * from contact where seq = ?";
//		return jdbc.query(sql, new RowMapper<ContactDTO>() {
//			@Override
//			public ContactDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				ContactDTO dto = new ContactDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setName(rs.getString("name"));
//				dto.setContact(rs.getString("contact"));
//				return dto;
//			}
//		},seq);
//	}
	
	//DBCP를 활용한 바닐라 JDBC 방식
	
//	public int insert(ContactDTO dto) throws Exception{
//		String sql = "insert into contact values(contact_seq.nextval,?,?)";
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getName());
//			pstat.setString(2, dto.getContact());
//			return pstat.executeUpdate();
//		}
//	}
//	
//	public List<ContactDTO> selectAll() throws Exception{
//		String sql = "select * from contact";
//		try (Connection con = bds.getConnection();
//			PreparedStatement pstat = con.prepareStatement(sql);
//			ResultSet rs = pstat.executeQuery();){
//			List<ContactDTO> dto = new ArrayList();
//			while(rs.next()) {
//				int seq = rs.getInt("seq");
//				String name = rs.getString("name");
//				String contact = rs.getString("contact");
//				
//				dto.add(new ContactDTO(seq,name,contact));
//				
//			}
//			return dto;
//		}
//	}
//	
//	public int delete(int seq) throws Exception{
//		String sql = "delete from contact where seq=?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setInt(1, seq);
//			
//			int result = pstat.executeUpdate();
//			return result;
//		}
//	}
//	
//	public int update(ContactDTO dto) throws Exception{
//		String sql = "update contact set name=?, contact=? where seq=?";
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getName());
//			pstat.setString(2, dto.getContact());
//			pstat.setInt(3, dto.getSeq());
//			return pstat.executeUpdate();
//		}
//	}
}

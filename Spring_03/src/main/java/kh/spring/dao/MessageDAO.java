package kh.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MessageDTO;

@Repository
public class MessageDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(MessageDTO dto) {
		return mybatis.insert("Messages.insert",dto);
	}
	
	public int delete(int seq) {
		return mybatis.delete("Messages.delete",seq);
	}
	
	public int update(int seq, String column, String value) {
		Map<String, String> map = new HashMap<>();
		map.put("seq", String.valueOf(seq));
		map.put("column", column);
		map.put("value", value);
		return mybatis.update("Messages.update",map);
	}
	
	public List<MessageDTO> selectAll(){
		return mybatis.selectList("Messages.selectAll");
	}
	
	public List<MessageDTO> selectBySeq(int seq){
		return mybatis.selectList("Messages.selectBySeq",seq);
	}
	
//	@Autowired
//	private JdbcTemplate jdbc;
//	
//	public int insert(MessageDTO dto) throws Exception{
//		String sql = "insert into messages values(messages_seq.nextval,?,?,sysdate)";
//		return jdbc.update(sql, dto.getWriter(),dto.getMessage());
//	}
//	
//	public int delete(int seq) throws Exception{
//		String sql = "delete messages where seq=?";
//		return jdbc.update(sql, seq);
//	}
//	
//	public int update(MessageDTO dto) throws Exception{
//		String sql = "update messages set writer=?, message=? where seq=?";
//		return jdbc.update(sql, dto.getWriter(), dto.getMessage(), dto.getSeq());
//	}
//	
//	public List<MessageDTO> selectAll() throws Exception{
//		String sql = "select * from messages order by 1";
//		return jdbc.query(sql, new RowMapper<MessageDTO>() {
//			@Override
//			public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				
//				int seq = rs.getInt("seq");
//				String writer = rs.getString("writer");
//				String message = rs.getString("message");
//				Timestamp write_date = rs.getTimestamp("write_date");
//				MessageDTO dto = new MessageDTO(seq,writer,message,write_date);
//				return dto;
//			}
//		});
//	}
//	
//	public List<MessageDTO> selectBySeq(int seq) throws Exception{
//		String sql = "select * from messages where seq = ?";
//		return jdbc.query(sql, new RowMapper<MessageDTO>() {
//			@Override
//			public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				int seq = rs.getInt("seq");
//				String writer = rs.getString("writer");
//				String message = rs.getString("message");
//				Timestamp write_date = rs.getTimestamp("write_date");
//				MessageDTO dto = new MessageDTO(seq,writer,message,write_date);
//				return dto;
//			}
//		},seq);
//	}
	
//	public int insert(MessageDTO dto) throws Exception{
//		String sql = "insert into messages values(messages_seq.nextval,?,?,sysdate)";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getWriter());
//			pstat.setString(2, dto.getMessage());
//			
//			return pstat.executeUpdate();
//		}
//	}
	
//	public int update(MessageDTO dto) throws Exception{
//		String sql = "update messages set writer=?, message=? where seq=?";
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getWriter());
//			pstat.setString(2, dto.getMessage());
//			pstat.setInt(3, dto.getSeq());
//			return pstat.executeUpdate();
//		}
//	}
	
//	public int delete(int seq) throws Exception{
//		String sql = "delete messages where seq=?";
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setInt(1, seq);
//			
//			return pstat.executeUpdate();
//		}
//	}
	
//	public List<MessageDTO> selectAll() throws Exception{
//		String sql = "select * from messages order by 1";
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();){
//			List<MessageDTO> list = new ArrayList();
//			while(rs.next()) {
//				int seq = rs.getInt("seq");
//				String writer = rs.getString("writer");
//				String message = rs.getString("message");
//				Timestamp write_date = rs.getTimestamp("write_date");
//				
//				list.add(new MessageDTO(seq,writer,message,write_date));
//			}
//			return list;
//		}
//	}
	

	
}

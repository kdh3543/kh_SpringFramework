package kh.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(BoardDTO dto) {
		int result = mybatis.insert("Board.insert",dto);
		return dto.getSeq();
	}
	
	public List<BoardDTO> selectAll() {
		return mybatis.selectList("Board.selectAll");
	}
	
	public BoardDTO selectBySeq(int seq){
		return mybatis.selectOne("Board.selectBySeq",seq);
	}
	
	public List<BoardDTO> searchByMulti(String title, String writer){
		Map<String,String> map = new HashMap<>();
		map.put("title", title);
		map.put("writer", writer);
		return mybatis.selectList("Board.searchByMulti",map);
	}
	
	public int delete(int seq) {
		return mybatis.delete("Board.delete",seq);
	}
	
	public int update(BoardDTO dto) {
		return mybatis.update("Board.update",dto);
	}
	
	public int addCount(int seq) {
		return mybatis.update("Board.addAcount",seq);
	}
	
//	@Autowired
//	private JdbcTemplate jdbc;
//	
//	public List<BoardDTO> selectAll() throws Exception{
//		String sql = "select * from board";
//		return jdbc.query(sql, new RowMapper<BoardDTO>() {
//			@Override
//			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardDTO dto = new BoardDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setTitle(rs.getString("title"));
//				dto.setContents(rs.getString("contents"));
//				dto.setWrite_date(rs.getTimestamp("write_date"));
//				dto.setView_count(rs.getInt("view_count"));
//				return dto;
//			}
//		});
//	}
//	public int insert(BoardDTO dto) throws Exception{
//		String sql = "insert into board values(board_seq.nextval,?,?,?,sysdate,default)";
//		return jdbc.update(sql,dto.getWriter(),dto.getTitle(),dto.getContents());
//	}
//	public BoardDTO selectBySeq(int seq) throws Exception{
//		String sql = "select * from board where seq = ?";
//		return jdbc.queryForObject(sql, new RowMapper<BoardDTO>() {
//			@Override
//			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardDTO dto = new BoardDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setTitle(rs.getString("title"));
//				dto.setContents(rs.getString("contents"));
//				return dto;
//			}
//		},seq);
//	}
//	
//	public int update(BoardDTO dto ) throws Exception{
//		String sql = "update board set title=?, contents=? where seq=?";
//		return jdbc.update(sql,dto.getTitle(),dto.getContents(),dto.getSeq());
//	}
//	public int deleteBySeq(int seq) throws Exception{
//		String sql = "delete from board where seq = ?";
//		return jdbc.update(sql,seq);
//	}
		
	
}

package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.DTO;

@Component
public class DAO {
	
	@Autowired
	private BasicDataSource bds;
	
	public int insert(DTO dto) throws Exception{
		String sql = "insert into sign values(?, ?)";
		
		try(Connection con = bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
}

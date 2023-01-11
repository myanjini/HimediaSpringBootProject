package board.mapper;

import org.apache.ibatis.annotations.Mapper;

import board.dto.LoginDto;
import board.dto.UserDto;

@Mapper
public interface LoginMapper {
	public UserDto login(LoginDto loginDto) throws Exception;
}

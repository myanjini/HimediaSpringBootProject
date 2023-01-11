package board.service;

import board.dto.LoginDto;
import board.dto.UserDto;

public interface LoginService {
	public UserDto login(LoginDto loginDto) throws Exception;
}

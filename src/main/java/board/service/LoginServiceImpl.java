package board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.LoginDto;
import board.dto.UserDto;
import board.mapper.LoginMapper;

@Service 
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public UserDto login(LoginDto loginDto) throws Exception {
		return loginMapper.login(loginDto);
	}

}

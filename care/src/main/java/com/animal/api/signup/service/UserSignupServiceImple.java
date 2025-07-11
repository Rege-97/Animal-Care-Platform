package com.animal.api.signup.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.animal.api.auth.exception.CustomException;
import com.animal.api.auth.model.vo.UserVO;
import com.animal.api.signup.mapper.SignupMapper;
import com.animal.api.signup.model.request.UserSignupRequestDTO;

@Service
@Primary
public class UserSignupServiceImple implements UserSignupService {
    @Autowired
    private SignupMapper signupMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Override
	public void signupUser(UserSignupRequestDTO dto) {
		// 1. 중복 검사
		if (signupMapper.isDuplicateId(dto.getId()) > 0) {
			throw new CustomException(409, "이미 사용 중인 아이디입니다.");
		}

		if (signupMapper.isDuplicateEmail(dto.getEmail()) > 0) {
			throw new CustomException(409, "이미 가입된 이메일 입니다.");
		}

		if (signupMapper.isDuplicateNickname(dto.getNickname()) > 0) {
			throw new CustomException(409, "이미 사용 중인 닉네임입니다.");
		}
		
		LocalDate parsedBirthDate = LocalDate.parse(dto.getBirthDate());

		// 2. DTO -> VO 매핑
		UserVO user = new UserVO();
		user.setUserTypeIdx(1); // 일반 사용자
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword())); // 암호화
		user.setName(dto.getName());
		user.setNickname(dto.getNickname());
		user.setBirthDate(parsedBirthDate);
		user.setGender(dto.getGender());
		user.setTel(dto.getTel());
		user.setZipCode(dto.getZipCode());
		user.setAddress(dto.getAddress());
		user.setAddressDetail(dto.getAddressDetail());
		user.setStatus(1);

		// 3. DB 저장
		signupMapper.insertUser(user);
	}

}

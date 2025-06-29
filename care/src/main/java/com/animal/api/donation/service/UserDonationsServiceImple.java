package com.animal.api.donation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.animal.api.donation.mapper.UserDonationsMapper;
import com.animal.api.donation.model.request.DonationCommentDeleteRequestDTO;
import com.animal.api.donation.model.request.DonationCommentRequestDTO;
import com.animal.api.donation.model.request.DonationCommentUpdateRequestDTO;
import com.animal.api.donation.model.request.DonationRequestDTO;
import com.animal.api.donation.model.response.AllDonationCommentsResponseDTO;
import com.animal.api.donation.model.response.AllDonationListResponseDTO;
import com.animal.api.donation.model.response.AllDonationUserListResponseDTO;
import com.animal.api.donation.model.response.DonationDetailResponseDTO;

@Service
@Primary
public class UserDonationsServiceImple implements UserDonationsService {

	@Autowired
	private UserDonationsMapper mapper;

	@Override
	public List<AllDonationListResponseDTO> getAllDonations(int listSize, int cp) {

		if (cp == 0) {
			cp = 1;
		}
		cp = (cp - 1) * listSize;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("listSize", listSize);
		map.put("cp", cp);
		List<AllDonationListResponseDTO> donationList = mapper.getAllDonations(map);

		return donationList;
	}

	@Override
	public DonationDetailResponseDTO getDonationDetail(int idx) {

		DonationDetailResponseDTO donationDetail = mapper.getDonationDetail(idx);

		return donationDetail;
	}

	@Override
	public List<AllDonationCommentsResponseDTO> getDonationComments(int idx, int listSize, int cp) {

		if (cp == 0) {
			cp = 1;
		}
		cp = (cp - 1) * listSize;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("idx", idx);
		map.put("listSize", listSize);
		map.put("cp", cp);
		List<AllDonationCommentsResponseDTO> commentList = mapper.getDonationComments(map);

		return commentList;
	}

	@Override
	public List<AllDonationUserListResponseDTO> getDonationUserLists(int idx, int listSize, int cp) {
		if (cp == 0) {
			cp = 1;
		}
		cp = (cp - 1) * listSize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("idx", idx);
		map.put("listSize", listSize);
		map.put("cp", cp);
		List<AllDonationUserListResponseDTO> userList = mapper.getDonationUserLists(map);

		return userList;
	}

	@Override
	public Map addDonationComment(DonationCommentRequestDTO dto) {
		Map map = new HashMap();
		int result = 0;
		String msg = null;
		Boolean errorCheck = false;
		if (dto.getUserIdx() == 0) {
			result = USER_NOT_FOUND;
			msg = "잘못된 접근:유저정보없음";
			errorCheck = true;
		} else if (dto.getDonationIdx() == 0) {
			result = DONATION_NOT_FOUND;
			msg = "잘못된 접근:기부정보없음";
			errorCheck = true;
		} else if (dto.getContent() == null || dto.getContent().equals("")) {
			result = COMMENT_CONTENT_EMPTY;
			msg = "잘못된 접근:값을 입력해주세요";
			errorCheck = true;
		}
		if (!errorCheck) {
			int count = mapper.addDonationComment(dto);

			if (count > 0) {
				result = POST_SUCCESS;
				msg = "응원 댓글 성공";
			} else {
				result = ERROR;
				msg = "잘못된 접근";
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}

	@Override
	public Map updateDonationComment(DonationCommentUpdateRequestDTO dto) {
		Map map = new HashMap();
		int result = 0;
		String msg = null;
		Boolean errorCheck = false;
		if (dto.getIdx() == 0) {
			result = COMMENT_NOT_FOUND;
			msg = "잘못된 접근:댓글정보없음";
			errorCheck = true;
		} else if (dto.getDonationIdx() == 0) {
			result = DONATION_NOT_FOUND;
			msg = "잘못된 접근:기부정보없음";
			errorCheck = true;
		} else if (dto.getUserIdx() == 0) {
			result = USER_NOT_FOUND;
			msg = "잘못된 접근:유저정보없음";
			errorCheck = true;
		} else if (dto.getContent() == null || dto.getContent().equals("")) {
			result = COMMENT_CONTENT_EMPTY;
			msg = "잘못된 접근:값을 입력해주세요";
			errorCheck = true;
		}

		if (!errorCheck) {
			int count = mapper.updateDonationComment(dto);

			if (count > 0) {
				result = POST_SUCCESS;
				msg = "응원 댓글 수정 성공";
			} else {
				result = ERROR;
				msg = "잘못된 접근";
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}

	@Override
	public Map deleteDonationComment(DonationCommentDeleteRequestDTO dto) {
		Map map = new HashMap();
		int result = 0;
		String msg = null;
		Boolean errorCheck = false;
		if (dto.getIdx() == 0) {
			result = COMMENT_NOT_FOUND;
			msg = "잘못된 접근:댓글정보없음";
			errorCheck = true;
		} else if (dto.getUserIdx() == 0) {
			result = USER_NOT_FOUND;
			msg = "잘못된 접근:유저정보없음";
			errorCheck = true;
		}

		if (!errorCheck) {
			int count = mapper.deleteDonationComment(dto);

			if (count > 0) {
				result = DELETE_SUCCESS;
				msg = "응원 댓글 삭제 성공";
			} else {
				result = ERROR;
				msg = "잘못된 접근";
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}

	@Override
	public int getDonationUserPoint(int userIdx) {
		int userPoint = mapper.getDonationUserPoint(userIdx);
		return userPoint;
	}

	@Transactional
	@Override
	public Map addDonation(DonationRequestDTO dto, int userIdx) {
		Map map = new HashMap();
		int result = 0;
		String msg = null;
		Boolean errorCheck = false;
		int userPoint = getDonationUserPoint(userIdx);
		if (userPoint < dto.getDonatedAmount()) {
			result = INSUFFICIENT_POINT;
			msg = "보유하신 포인트가 부족합니다.";
			errorCheck = true;
		}

		if (!errorCheck) {
			int donationDetailInsert = mapper.addDonation(dto);
			int donationUpdate = mapper.updateDonation(dto);
			int userUpdate = mapper.updateUserPoint(dto);

			if (donationDetailInsert > 0 && donationUpdate > 0 && userUpdate > 0) {
				result = POST_SUCCESS;
				msg = "기부 성공";
			} else {
				result = ERROR;
				msg = "잘못된 접근";
				throw new RuntimeException("기부 처리 중 오류 발생: 트랜잭션 롤백");
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;

	}
}

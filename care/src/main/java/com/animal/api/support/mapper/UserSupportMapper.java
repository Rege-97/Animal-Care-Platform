package com.animal.api.support.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.animal.api.support.model.request.SearchNoticeRequestDTO;
import com.animal.api.support.model.response.*;

@Mapper
public interface UserSupportMapper {

	public List<UserNoticeResponseDTO> getAllNotice(Map map);

	public UserNoticeResponseDTO getNoticeDetail(int idx);

	public List<UserNoticeResponseDTO> searchAllNotice(SearchNoticeRequestDTO dto);

	public int updateNoticeViews(int idx);

}

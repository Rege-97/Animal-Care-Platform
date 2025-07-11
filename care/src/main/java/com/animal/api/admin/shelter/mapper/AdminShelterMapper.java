package com.animal.api.admin.shelter.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.animal.api.admin.shelter.model.response.ShelterJoinRequestListResponseDTO;

@Mapper
public interface AdminShelterMapper {

	public Integer checkShelterVolunteer(int idx);

	public int deleteVolunteer(int idx);

	public List<ShelterJoinRequestListResponseDTO> getShelterJoinRequestList(Map map);

	public int getShelterJoinRequestListTotalCnt();

	public ShelterJoinRequestListResponseDTO getShelterJoinRequestDetail(int idx);

	public Integer checkJoinStatus(int idx);

	public int updateShelterJoinRequestStatus(int idx);
}

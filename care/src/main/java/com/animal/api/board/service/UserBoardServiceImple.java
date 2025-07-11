package com.animal.api.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.animal.api.board.mapper.UserBoardMapper;
import com.animal.api.board.model.request.BoardCommentReplyRequestDTO;
import com.animal.api.board.model.request.BoardCommentRequestDTO;
import com.animal.api.board.model.request.BoardCommentUpdateRequestDTO;
import com.animal.api.board.model.request.BoardSearchRequestDTO;
import com.animal.api.board.model.request.BoardUpdateRequestDTO;
import com.animal.api.board.model.request.BoardWriteRequestDTO;
import com.animal.api.board.model.response.AllBoardCommentsResponseDTO;
import com.animal.api.board.model.response.AllBoardListResponseDTO;
import com.animal.api.board.model.response.BoardDetailResponseDTO;
import com.animal.api.common.model.PageInformationDTO;
import com.animal.api.common.util.FileManager;

@Service
@Primary
public class UserBoardServiceImple implements UserBoardService {

	@Autowired
	private UserBoardMapper mapper;

	@Autowired
	private FileManager fileManager;

	private int listSize = 10;
	private int pageSize = 5;

	@Override
	public List<AllBoardListResponseDTO> getAllBoards(int cp) {
		if (cp == 0) {
			cp = 1;
		}
		cp = (cp - 1) * listSize;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("listSize", listSize);
		map.put("cp", cp);
		List<AllBoardListResponseDTO> boardList = mapper.getAllBoards(map);

		return boardList;
	}

	@Override
	public PageInformationDTO getAllBoardsPageInfo(int cp) {
		if (cp == 0) {
			cp = 1;
		}
		int totalCnt = mapper.getAllBoardsTotalCnt();
		PageInformationDTO pageInfo = new PageInformationDTO(totalCnt, listSize, pageSize, cp);
		return pageInfo;
	}

	@Override
	public List<AllBoardListResponseDTO> searchBoards(String type, String keyword, int cp) {
		if (cp == 0) {
			cp = 1;
		}
		cp = (cp - 1) * listSize;

		BoardSearchRequestDTO request = new BoardSearchRequestDTO(type, keyword, listSize, cp);
		List<AllBoardListResponseDTO> boardList = mapper.searchBoards(request);

		return boardList;
	}

	@Override
	public PageInformationDTO searchBoardsPageInfo(String type, String keyword, int cp) {
		if (cp == 0) {
			cp = 1;
		}
		BoardSearchRequestDTO request = new BoardSearchRequestDTO(type, keyword, listSize, cp);

		int totalCnt = mapper.searchBoardsTotalCnt(request);
		PageInformationDTO pageInfo = new PageInformationDTO(totalCnt, listSize, pageSize, cp);
		return pageInfo;
	}

	@Override
	public int addBoard(BoardWriteRequestDTO dto) {
		int ref = mapper.getMaxRef();

		dto.setRef(ref + 1);

		int result = mapper.addBoard(dto);

		if (result == 1) {
			return POST_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public int uploadBoardFile(MultipartFile[] files, int idx) {
		boolean result = fileManager.uploadFiles("boards", idx, files);

		if (result) {
			return UPLOAD_SUCCESS;

		} else {

			mapper.deleteBoard(idx);
			return ERROR;

		}
	}

	@Override
	public BoardDetailResponseDTO getBoardDetail(int idx) {
		int result = mapper.updateBoardViews(idx);

		if (result > 0) {
			BoardDetailResponseDTO boardDetail = mapper.getBoardDetail(idx);
			if (boardDetail != null) {
				boardDetail.setFilePaths(fileManager.getFilePath("boards", idx));
			}
			return boardDetail;
		} else {
			return null;
		}
	}

	@Override
	public int updateBoard(BoardUpdateRequestDTO dto, int idx) {
		Integer userIdx = mapper.checkMyBoard(idx);
		if (userIdx == null) {
			return BOARD_NOT_FOUND;
		} else if (userIdx != dto.getUserIdx()) {
			return NOT_OWNED_BOARD;
		}

		int result = mapper.updateBoard(dto);

		if (result > 0) {
			return POST_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public int deleteBoard(int idx, int userIdx) {
		Integer userIdx2 = mapper.checkMyBoard(idx);// 글 작성자 userIdx 확인

		if (userIdx2 == null) { // 글 작성 여부 검증
			return BOARD_NOT_FOUND;
		} else if (userIdx != userIdx2) { // 본인 글 검증
			return NOT_OWNED_BOARD;
		}

		int result = mapper.deleteBoard(idx);

		if (result > 0) {
			fileManager.deleteFolder("boards", idx);
			return DELETE_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public int checkMyHeart(int userIdx, int boardIdx) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userIdx", userIdx);
		map.put("boardIdx", boardIdx);

		Integer result = mapper.checkMyHeart(map);

		if (result == null || result == 0) {
			return HEART_NOT_FOUND;
		} else {
			return ALREADY_HEART;
		}
	}

	@Override
	public int addBoardHeart(int userIdx, int boardIdx) {

		if (userIdx < 1) {
			return IDX_NOT_FOUND;
		} else if (boardIdx < 1) {
			return BOARD_NOT_FOUND;
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userIdx", userIdx);
		map.put("boardIdx", boardIdx);

		int result = mapper.addBoardHeart(map);

		if (result > 0) {
			return HEART_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public int deleteBoardHeart(int userIdx, int boardIdx) {
		if (userIdx < 1) {
			return IDX_NOT_FOUND;
		} else if (boardIdx < 1) {
			return BOARD_NOT_FOUND;
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userIdx", userIdx);
		map.put("boardIdx", boardIdx);

		int result = mapper.deleteBoardHeart(map);

		if (result > 0) {
			return DELETE_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public Integer checkBoardExists(int idx) {
		Integer boardIdx = mapper.checkBoardExists(idx);
		return boardIdx;
	}

	@Override
	public List<AllBoardCommentsResponseDTO> getBoardComments(int boardIdx, int cp) {
		if (cp == 0) {
			cp = 1;
		}
		cp = (cp - 1) * listSize;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardIdx", boardIdx);
		map.put("listSize", listSize);
		map.put("cp", cp);
		List<AllBoardCommentsResponseDTO> commentList = mapper.getBoardComments(map);
		return commentList;
	}

	@Override
	public PageInformationDTO getBoardCommentsPageInfo(int boardIdx, int cp) {
		if (cp == 0) {
			cp = 1;
		}
		int totalCnt = mapper.getBoardCommentsTotalCnt(boardIdx);
		PageInformationDTO pageInfo = new PageInformationDTO(totalCnt, listSize, pageSize, cp);
		return pageInfo;
	}

	@Override
	public int addBoardComment(BoardCommentRequestDTO dto, int idx) {
		Integer boardIdx = mapper.checkBoardExists(idx);
		if (boardIdx == null || boardIdx == 0) {
			return BOARD_NOT_FOUND;
		}
		int ref = mapper.getCommentMaxRef(idx);
		dto.setRef(ref + 1);
		int result = mapper.addBoardComment(dto);
		if (result == 1) {
			return POST_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public int updateBoardComment(BoardCommentUpdateRequestDTO dto, int idx, int boardCommentIdx, int userIdx) {

		Integer boardIdx = mapper.checkBoardExists(idx); // 게시글 존재 여부 검증
		if (boardIdx == null || boardIdx == 0) {
			return BOARD_NOT_FOUND;
		}

		Integer checkboardCommentIdx = mapper.checkBoardCommentExists(boardCommentIdx); // 게시판 댓글 존재 여부 검증
		if (checkboardCommentIdx == null || checkboardCommentIdx == 0) {
			return COMMENT_NOT_FOUND;
		}

		Integer userIdx2 = mapper.checkMyBoardComment(boardCommentIdx);// 해당 댓글이 나의 댓글인지 검증
		if (userIdx != userIdx2) {
			return NOT_MYCOMMENT;
		}

		int result = mapper.updateBoardComment(dto);
		if (result > 0) {
			return UPDATE_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public int deleteBoardComment(int idx, int boardCommentIdx, int userIdx) {
		Integer boardIdx = mapper.checkBoardExists(idx); // 게시글 존재 여부 검증
		if (boardIdx == null || boardIdx == 0) {
			return BOARD_NOT_FOUND;
		}

		Integer CheckboardCommentIdx = mapper.checkBoardCommentExists(boardCommentIdx); // 게시판 댓글 존재 여부 검증
		if (CheckboardCommentIdx == null || CheckboardCommentIdx == 0) {
			return COMMENT_NOT_FOUND;
		}

		Integer userIdx2 = mapper.checkMyBoardComment(boardCommentIdx);// 해당 댓글이 나의 댓글인지 검증
		if (userIdx != userIdx2) {
			return NOT_MYCOMMENT;
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("idx", boardCommentIdx);
		map.put("userIdx", userIdx);
		int result = mapper.deleteBoardComment(map);
		if (result == 1) {
			return DELETE_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public int addBoardCommentReply(BoardCommentReplyRequestDTO dto, int idx, int boardCommentIdx) {
		Integer boardIdx = mapper.checkBoardExists(idx); // 게시글 존재 여부 검증
		if (boardIdx == null || boardIdx == 0) {
			return BOARD_NOT_FOUND;
		}

		Integer CheckboardCommentIdx = mapper.checkBoardCommentExists(boardCommentIdx); // 게시판 댓글 존재 여부 검증
		if (CheckboardCommentIdx == null || CheckboardCommentIdx == 0) {
			return COMMENT_NOT_FOUND;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map = mapper.checkCommentRefTurn(boardCommentIdx);

		if (map == null || map.get("REF") == null || map.get("MAXTURN") == null) { // 댓글 REF,MAXTURN 존재 여부 검증
			return COMMENT_REF_DATA_MISSING;
		} else {
			dto.setRef(map.get("REF"));
			dto.setTurn(map.get("MAXTURN") + 1);
		}

		int result = mapper.addBoardCommentReply(dto);

		if (result == 1) {
			return POST_SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Override
	public int addBoardReply(BoardWriteRequestDTO dto, int idx) {
		Integer boardIdx = mapper.checkBoardExists(idx); // 게시글 존재 여부 검증
		if (boardIdx == null || boardIdx == 0) {
			return BOARD_NOT_FOUND;
		}

		Integer getBoardRef = mapper.getBoardRef(idx); // 게시글 REF 존재 여부 검증
		if (getBoardRef == null || getBoardRef == 0) {
			return BOARD_REF_DATA_MISSING;
		} else {
			dto.setRef(getBoardRef);
		}

		Integer checkBoardReplyExists = mapper.checkBoardReplyExists(getBoardRef); // 게시글 답글 존재 여부 확인
		if (checkBoardReplyExists == 1) {
			return REPLY_ALREADY_EXISTS;
		}

		int result = mapper.addBoardReply(dto);
		if (result == 1) {
			return POST_SUCCESS;
		} else {
			return ERROR;
		}
	}
}

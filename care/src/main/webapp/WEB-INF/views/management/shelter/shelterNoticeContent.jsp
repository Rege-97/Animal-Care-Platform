<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당신에게 다시가는 길 - 보호시설 공지사항</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css">

<style>
.board {
	font-family: 'Pretendard', sans-serif;
	background-color: #fff;
	padding: 60px 20px 40px;
}

.header-title {
	font-size: 28px;
  	font-weight: bold;
  	margin-bottom : 20px;
  	text-align: center;
  	color: #3ACDB2;
}

.title-detail {
    text-align: center;
    margin-bottom: 40px; 
    color: #666; 
    font-size: 16px; 
}

.board-container {
	max-width: 1200px;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	border: 1px solid #DBDBDB;
	border-radius: 6px;
} 

.board-content-table {
	width: 100%;
	border-collapse: collapse;
	font-size: 16px;
	margin-bottom: 30px;
	table-layout: fixed;
}

.board-content-table th {
	width: 20%;
	background-color: #f9f9f9;
	padding: 12px 10px;
	border-bottom: 1px solid #ddd;
	color: #333;
	vertical-align: top;
	text-align: center;
	width: 15%;
}

.board-content-table td {
	padding: 12px 10px;
	border-bottom: 1px solid #DBDBDB;
	color: #333;
	width: 35%;
}

#noticeContentBox {
	border-radius: 4px;
	min-height: 400px;
	padding: 20px;
	font-size: 16px;
	line-height: 1.6;
	white-space: pre-line;
	color: #444;
}

.btn-area {
	text-align: center;
}

.btn-area input[type="button"] {
	background-color: #3ACDB2;
	color: #fff;
	border: none;
	padding: 10px 20px;
	margin-bottom : 20px;
	border-radius: 4px;
	font-size: 14px;
	cursor: pointer;
	width : 90px;
	transition: background-color 0.3s;
}

.btn-area input[type="button"]:hover {
	background-color: #34b3a1;
}

@media (max-width : 600px) {
	.board-content-table th, .board-content-table td {
		font-size: 14px;
		padding: 10px;
	}
}

.action-buttons {
  text-align: right;
}

.bt2{
	  background: #ddd;
	  border: none;
	  border-radius: 6px;
	  padding: 8px 16px;
	  cursor: pointer;
	  transition: background 0.25s;
	  color: var(--text-main);
	  font-size: 14px;
	  margin-left: 8px; 
	  margin-bottom:10px;
}

.bt2:hover {
  	background: #ccc;
}


</style>
</head>

<body>
 <%@ include file="/WEB-INF/views/common/management/managementHeader.jsp" %>
	<section class="board">
		<div class="header-title">보호시설 소식</div>
		<div class="title-detail">보호시설에서 안내하는 소식입니다.</div>

		<div class="board-container">
			<div class="action-buttons">
			  <input type="button" class="bt2" value="수정하기" onclick="editNotice()" />
			  <input type="button" class="bt2" value="삭제하기" onclick="deleteNotice()" />
			</div>
			<table class="board-content-table">
				<tr>
					<th>NO</th>
					<td id="noticeIdx"></td>
					<th>작성일</th>
					<td id="noticeDate"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td id="noticeWriter">댕봉사</td>
					<th>조회수</th>
					<td id="noticeViews"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td id="noticeTitle" colspan="3"></td>
				</tr>
				<tr>
					<td id="noticeContent" colspan="4">
						<div class="content-box" id="noticeContentBox"></div>
					</td>
				</tr>
				<tr>
				  <th>첨부파일</th>
				  <td id="noticeFile" colspan="3">
				  </td>
				</tr>
			</table>

			<div class="btn-area">
				<input type="button" value="목록으로" id = "goListNotice">
			</div>
		</div>
	</section>

<script src="${pageContext.request.contextPath}/resources/web/common/commonUtils.js"></script>
<script>

//공지사항 조회
async function noticeDetail() {
		const pathParts = window.location.pathname.split('/');
		const idx = pathParts[pathParts.length - 1];

		if (!idx) {
			alert("잘못된 접근입니다.");
			window.location.href = "/notice";
			return;
		}

		const result = await API.get("/care/api/management/shelter/boards/" + idx);
		console.log(result);

		if (result.status !== 200) {
			alert("게시물을 불러올 수 없습니다.");
			window.location.href = "/notice";
			return;
		}

		const notice = result.data;

		document.getElementById("noticeIdx").innerHTML = notice.idx;
		document.getElementById("noticeTitle").innerHTML = notice.title;
		document.getElementById("noticeDate").innerHTML = notice.createdAt;
		document.getElementById("noticeWriter").innerHTML = "보호소 관리자";
		document.getElementById("noticeViews").innerHTML = notice.views;
		document.getElementById("noticeContentBox").innerHTML = notice.content;
		
		if (notice.filesPath && notice.filesPath !== "") {
			  document.getElementById("noticeFile").innerHTML =
				  '<a href="/care/resources/boards/' + notice.idx + '/files/' + notice.filesPath.split("/").pop() + '" target="_blank">' +
				    notice.filesPath.split("/").pop() +
				  '</a>';
			} else {
			  document.getElementById("noticeFile").innerHTML = "첨부파일이 없습니다.";
			}
		
		document.getElementById("goListNotice").addEventListener("click", function () {
			  const contextPath = "${pageContext.request.contextPath}";
			  window.location.href = contextPath + "/management/shelters?tab=notice";
			});
	}
		noticeDetail();
	
//수정하기 폼 이동
function editNotice() {
  const pathParts = window.location.pathname.split('/');
  const idx = pathParts[pathParts.length - 1];  // 현재 공지사항의 idx를 URL에서 가져옵니다.
  window.location.href = '/care/management/shelters/boards/update/' + idx;  // 수정 페이지로 이동
}

//삭제하기
async function deleteNotice() {
	  const pathParts = window.location.pathname.split('/');
	  const idx = pathParts[pathParts.length - 1];  // 현재 공지사항의 idx를 URL에서 가져옵니다.

	  if (!confirm("공지사항을 삭제하시겠습니까?")) {
	    return; 
	  }

	  try {
	    const result = await API.delete(`/care/api/management/shelter/boards/${idx}`);

	    if (result.status === 200) {
	      alert("공지사항이 삭제되었습니다.");
	      window.location.href = "/care/management/shelters"; 
	    } else {
	      alert("삭제에 실패했습니다.");
	    }
	  } catch (err) {
	    console.error("삭제 중 오류 발생:", err);
	    alert("삭제 중 오류가 발생했습니다.");
	  }
	}


</script>

</body>
</html>
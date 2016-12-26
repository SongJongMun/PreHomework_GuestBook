<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
</head>
<body>
	<form id="frm">
		<table class="board_view">
			<colgroup>
				<col width="15%">
				<col width="*" />
			</colgroup>
			<caption>게시글 작성</caption>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td><input type="text" id="TITLE" name="TITLE" class="wdp_90"></input></td>
				</tr>
				<tr>
					<td colspan="2" class="view_text"><textarea rows="20"
							cols="100" title="내용" id="CONTENTS" name="CONTENTS"></textarea></td>
				</tr>
				<tr>
				 	<th scope="row">E-Mail</th>
                    <td>
                    	<input type="email" id="EMAIL" name="EMAIL" class="wdp_90"/>
                    </td>
				</tr>
				<tr>
                    <th scope="row">PASSWORD</th>
                    <td>
                    	<input type="password" id="PASSWORD" name="PASSWORD" class="wdp_90"/>
                    </td>
				</tr>
			</tbody>
		</table>

		<a href="#this" class="btn" id="write">작성하기</a> 
		<a href="#this"	class="btn" id="list">목록으로</a>
	</form>

	<%@ include file="/WEB-INF/include/include-body.jspf"%>
	<script type="text/javascript">
		$(document).ready(function(){
		    $("#list").on("click", function(e){ //목록으로 버튼
		        e.preventDefault();
		        fn_openBoardList();
		    });
		     
		    $("#write").on("click", function(e){ //작성하기 버튼
		        e.preventDefault();
		    	if(fn_checkForm() == true)
		    		fn_insertBoard();
		    	else
            		alert("Input Form is Not Validate!!");
		    });
		});
		 
		function fn_openBoardList(){
		    var comSubmit = new ComSubmit();
		    comSubmit.setUrl("<c:url value='/sample/openBoardList.do'/>");
		    comSubmit.submit();
		}
		 
		function fn_insertBoard(){
		    var comSubmit = new ComSubmit("frm");
		    comSubmit.setUrl("<c:url value='/sample/insertBoard.do'/>");
		    comSubmit.submit();
		}
		 
        function fn_checkForm(){
        	if(gfn_isNull($("#TITLE").val()) == true) 		return false;
        	if(gfn_isNull($("#CONTENTS").val()) == true) 	return false;
        	if(fn_checkEmail() == false) 					return false;
        	if(gfn_isNull($("#PASSWORD").val()) == true) 	return false;
        	return true;
        }
        
        function fn_checkEmail(){
        	if(gfn_emailValidate($("#EMAIL").val())) return true;
        	return false;
        }     
    </script>
</body>
</html>
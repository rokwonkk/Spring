<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- pagination에서 필요한 js 추가 -->
<script type="text/javascript" src="./jquery.twbsPagination.min.js"></script>

</head>
<body>

<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination"></ul>
    </nav>
</div>

<script type="text/javascript">

let totalCount = 36;	//글의 총수
let pageSize = 10;		//보여줄 페이지 갯수
let pageNumber = 1;		//현재 페이지

let totalPages = totalCount / 10;
if(totalCount % 10 > 0){
	totalPages++;
}
$("#pagination").twbsPagination({
	
	startPage : 1,
	totalPages : totalPages,
	visiblePages : 10,
	first : '<span sris-hidden="true">«</span>',
	prev : "이전",
	next : "다음",
	last : '<span sris-hidden="true">»</span>',
	onPageClick : function(event, page){
		alert(page);
	}
});

</script>

</body>
</html>
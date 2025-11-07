$(document).ready(function () {
    var actionForm = $("#actionForm");

// 페이지 번호 클릭 시
    $(".pagination-container .pagination-link").on("click", function (e) {
        e.preventDefault();
        var pageNum = $(this).attr("href");

        actionForm.find("input[name='pageNum']").val(pageNum);
        actionForm.attr("action", "/notice/notice_list").submit(); // ✅ 절대 경로
    });

    $(".notice-table .notice-link").on("click", function (e) {
        e.preventDefault();
        var href = $(this).attr("href");
        var noticeNo = href.match(/notice_no=(\d+)/)?.[1]; // 숫자만 추출

        if (!noticeNo) {
            alert("잘못된 링크입니다.");
            return;
        }

        actionForm.find("input[name='notice_no']").remove();
        actionForm.append(
            $("<input>").attr({
                type: "hidden",
                name: "notice_no",
                value: noticeNo
            })
        );

        actionForm.attr("action", "/notice/notice_view").submit();
    });

    // 검색 버튼 클릭 시 유효성 검사 및 검색 요청
    $("#searchForm button").on("click", function () {
        if (searchForm.find("option:selected").val() !== "" && !searchForm.find("input[name='keyword']").val()) {
            alert("키워드를 입력하세요.");
            return false;
        }
        searchForm.attr("action", "/notice/notice_list").submit();
    });

    // 검색 조건 변경 시 키워드 초기화
    $("#searchForm select").on("change", function () {
        if (searchForm.find("option:selected").val() === "") {
            searchForm.find("input[name='keyword']").val("");
        }
    });
});
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
});
$(document).ready(function () {
    var actionForm = $("#actionForm");

    // 페이지 번호 클릭 시
    $("#notice-pagination .pagination-item a").on("click", function (e) {
        e.preventDefault();
        var pageNum = $(this).attr("href");
        actionForm.find("input[name='pageNum']").val(pageNum);
        actionForm.attr("action", "notice_list").submit();
    });

    // 공지사항 제목 클릭 시
    $("#notice-table .notice-link").on("click", function (e) {
        e.preventDefault();
        var targetNno = $(this).attr("href");

        // 기존 notice_no input 제거
        actionForm.find("input[name='notice_no']").remove();

        // 새로운 notice_no input 추가
        actionForm.append(
            $("<input>").attr({
                type: "hidden",
                name: "notice_no",
                value: targetNno
            })
        );

        actionForm.attr("action", "notice_view").submit();
    });
});
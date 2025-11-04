$(document).ready(function () {
    const actionForm = $("#actionForm");

    // [1] 페이징 처리
    $(".paginate_button a").on("click", function (e) {
        e.preventDefault();
        const href = $(this).attr("href");
        const targetPage = href.includes("?")
            ? new URLSearchParams(href).get("pageNum")
            : href;

        actionForm.find("input[name='pageNum']").val(targetPage);
        actionForm.attr("action", "/notice/notice_list").submit(); //
    });

    // [2] 상세보기 처리
    $(".view_link").on("click", function (e) {
        e.preventDefault();
        const href = $(this).attr("href");
        const noticeNo = href.includes("?")
            ? new URLSearchParams(href).get("notice_no")
            : href;

        actionForm.find("input[name='notice_no']").remove();
        actionForm.append(
            $("<input>", {
                type: "hidden",
                name: "notice_no",
                value: noticeNo
            })
        );
        actionForm.attr("action", "/notice/notice_view").submit();
    });

    // [3] 공통 AJAX 전송 함수
    function sendAjaxForm(formId, url, successMsg, failMsg) {
        const formData = $(formId).serialize();
        console.log("submit clicked");

        $.ajax({
            type: "post",
            url: url,
            data: formData,
            success: function () {
                alert(successMsg);
                location.href = "/notice/notice_list";
            },
            error: function () {
                alert(failMsg);
            }
        });
    }

    // [4] 작성 처리
    $("#notice_write_form button[type='submit']").on("click", function (e) {
        e.preventDefault();
        sendAjaxForm("#notice_write_form", "/notice/write_process", "작성 성공", "작성 실패");
    });

    // [5] 수정 처리
    $("#modifyBtn").on("click", function (e) {
        e.preventDefault();
        sendAjaxForm("#notice_modify_form", "/notice/modify_process", "수정 성공", "수정 실패");
    });
});
$(document).ready(function () {
    var actionForm = $("#actionForm");

    $(".inquiry-table .inquiry-link").on("click", function (e) {
        e.preventDefault();
        var href = $(this).attr("href");
        var inquiryNo = href.match(/inquiry_no=(\d+)/)?.[1]; // 숫자만 추출

        if (!inquiryNo) {
            alert("잘못된 링크입니다.");
            return;
        }

        actionForm.find("input[name='inquiry_no']").remove();
        actionForm.append(
            $("<input>").attr({
                type: "hidden",
                name: "inquiry_no",
                value: inquiryNo
            })
        );

        actionForm.attr("action", "/inquiry/inquiry_view").submit();
    });
});
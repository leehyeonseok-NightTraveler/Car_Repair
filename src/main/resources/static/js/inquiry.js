$(document).ready(function () {
    var actionForm = $("#actionForm");
    $(".pagination-container .pagination-link").on("click", function (e) {
        e.preventDefault();
        var pageNum = $(this).attr("href");

        actionForm.find("input[name='pageNum']").val(pageNum);

        // 현재 URL 경로 확인
        var currentPath = window.location.pathname;

        // 경로에 따라 action 설정
        if (currentPath.includes("/inquiry/inquiry_manage")) {
            actionForm.attr("action", "/inquiry/inquiry_manage");
        } else {
            actionForm.attr("action", "/inquiry/inquiry_history");
        }

        actionForm.submit();
    });


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

    // 검색 버튼 클릭 시 유효성 검사 및 검색 요청
    $("#searchForm button").on("click", function () {

        // 검색 조건 변경 시 키워드 초기화
        $("#searchForm select").on("change", function () {
            if (searchForm.find("option:selected").val() === "") {
                searchForm.find("input[name='keyword']").val("");
            }
        });
    });
});
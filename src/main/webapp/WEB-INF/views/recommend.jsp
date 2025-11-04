<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>차량 수리점 지도</title>
    <style>
        html, body { margin:0; padding:0; height:100%; width:100%; }
        #map { width:700px;height:600px; background:#f8f8f8; }
    </style>
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <div id="map">지도 로드 중...</div>

    <!-- 1. SDK 직접 로드 (callback 없이) -->
    <script type="text/javascript" 
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0b4604468a48427fe628a6489a0547be">
    </script>

    <!-- 2. 직접 initMap 실행 -->
    <script>
        // SDK 로드 후 수동 실행
        function initMap() {
            console.log("지도 초기화 시작!");

            var container = document.getElementById('map');
            if (!container) {
                alert('지도 컨테이너 없음');
                return;
            }

            var map = new kakao.maps.Map(container, {
                center: new kakao.maps.LatLng(37.51, 127.03),
                level: 9
            });

            // 테스트 마커
            var marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(37.4980, 127.0276),
                title: "강남 수리점"
            });
            marker.setMap(map);

            var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="padding:10px;font-size:13px;">강남 수리점</div>'
            });
            infowindow.open(map, marker);

            console.log("지도 로드 완료!");
        }

        // SDK 로드 완료 감지 (1초마다 체크)
        var checkKakao = setInterval(function() {
            if (window.kakao && kakao.maps) {
                clearInterval(checkKakao);
                initMap();
            }
        }, 100);

        // 10초 후 실패 알림
        setTimeout(function() {
            if (!window.kakao || !kakao.maps) {
                document.getElementById('map').innerHTML = 
                    "<h3 style='color:red;text-align:center;padding:20px;'>지도 로드 실패<br>인터넷 연결 또는 appkey 확인</h3>";
            }
        }, 10000);
    </script>
	
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
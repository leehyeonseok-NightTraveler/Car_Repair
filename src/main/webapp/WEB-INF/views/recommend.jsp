<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>MY CAR 정비소</title>
    <style>
        /* 1. 기본 스타일 */
        html, body { margin:0; padding:0; height:100%; width:100%; background-color: #f5f5f5; }
        
        /* 2. 필터 영역 */
        #filterBox {
            width: 90%; max-width:900px; margin: 20px auto; text-align: center;
        }
        select, button {
            padding: 8px 12px;
            font-size: 14px;
            margin: 0 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button { cursor: pointer; background-color: #f8f8f8; }
        button:hover { background-color: #e0e0e0; }

        /* 3. (수정) 맵과 목록을 감싸는 래퍼 */
        #map-wrapper {
            position: relative; /* 버튼 목록의 'absolute' 기준점 */
            width: 90%;
            max-width: 900px;
            height: 75vh;
            margin: 20px auto;
            border: 1px solid #ccc;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        /* 4. (수정) 맵 스타일 */
        #map {
            width: 100%;
            height: 100%;
        }

        /* 5. (추가) 버튼 목록 컨테이너 스타일 */
        #button-list-container {
            position: absolute; /* 맵 위에 띄우기 */
            top: 10px;
            left: 10px;
            width: 280px; /* 너비 조절 */
            height: calc(100% - 20px); /* 래퍼 높이에 맞춤 (상하 여백 10px) */
            background-color: white;
            border-radius: 5px;
            border: 1px solid #ddd;
            overflow-y: auto; /* 목록 길어지면 스크롤 */
            z-index: 10; /* 맵 컨트롤보다 위에 오도록 */
            box-shadow: 0 1px 3px rgba(0,0,0,0.2);
        }

        /* 6. (추가) 목록 안의 버튼 스타일 */
        .place-button {
            display: block;
            width: 100%;
            padding: 12px 15px;
            border: none;
            border-bottom: 1px solid #eee;
            background: #fff;
            text-align: left;
            cursor: pointer;
            font-size: 14px;
            line-height: 1.4;
        }
        .place-button:hover {
            background: #f9f9f9;
        }
        .place-button strong {
            font-size: 15px;
            color: #333;
        }
        .place-button .address {
            display: block;
            font-size: 12px;
            color: #777;
            margin-top: 4px;
        }
        /* 목록이 비었을 때 메시지 */
        .empty-message {
            text-align:center; 
            padding:30px 10px; 
            color:#888;
        }
		#hero-banner {
		    width: 100%;
		    height: 200px; /* 배너 높이 조절 */
		    /* 멋진 자동차 정비소 무료 이미지 예시입니다 */
		    background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), 
		                      url('https://images.unsplash.com/photo-1543363363-c69e0303f83d?q=80&w=2070');
		    background-size: cover;
		    background-position: center 40%;
		    display: flex;
		    align-items: center;
		    justify-content: center;
		    text-align: center;
		    color: white;
		    text-shadow: 0 2px 4px rgba(0,0,0,0.6); /* 글자 잘보이게 그림자 */
		    margin-bottom: 20px; /* 타이틀과의 간격 */
		}
		.hero-content h2 {
		    font-size: 2.2em;
		    font-weight: bold;
		    margin: 0;
		}
		.hero-content p {
		    font-size: 1.1em;
		    margin-top: 10px;
		}
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp" />
<div id="hero-banner">
    <div class="hero-content">
        <h2>신뢰할 수 있는 정비소 찾기</h2>
        <p>MY CAR 정비소가 검증한 전국 정비소 위치를 한눈에 확인하세요.</p>
    </div>
</div>

<div id="filterBox">
    <label for="regionSelect">지역 선택:</label>
    <select id="regionSelect">
        <option value="">전체</option>
        <option value="서울">서울특별시</option>
        <option value="부산">부산광역시</option>
        <option value="제주">제주특별자치도</option>
    </select>
    <button onclick="filterMarkers()">검색</button>
</div>

<div id="map-wrapper">
    <div id="map">지도 로드 중...</div>
    <div id="button-list-container">
        </div>
</div>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0b4604468a48427fe628a6489a0547be"></script>

<script>
let map, markers = [], allData = [], currentInfowindow;

// 📍 버튼 목록 컨테이너를 미리 찾아둡니다.
const buttonListContainer = document.getElementById('button-list-container');

// 1. 지도 초기화
function initMap() {
    const container = document.getElementById('map');
    const center = new kakao.maps.LatLng(37.5665, 126.9780); // 기본 서울 중심
    map = new kakao.maps.Map(container, { center, level: 11 });
    console.log("[INFO] 지도 초기화 완료");
    loadMarkers(); // JSON API 호출
}

// 2. 서버에서 JSON 데이터 불러오기
async function loadMarkers() {
    try {
        const response = await fetch('/api/recommend');
        if (!response.ok) throw new Error("HTTP 오류: " + response.status);

        const data = await response.json();
        allData = data; // 전체 데이터 저장 (필터링용)
        console.log("[INFO] API 데이터 수신 완료:", data.length, "개");

        drawMarkers(data); // 데이터로 마커와 버튼 그리기
    } catch (err) {
        console.error("[ERROR] 데이터 불러오기 실패:", err);
        document.getElementById('map').innerHTML = 
            "<h3 style='color:red;text-align:center;padding:20px;'>데이터를 불러오지 못했습니다.</h3>";
    }
}

// 3. (★핵심★) 지도에 마커 표시 + 버튼 목록 생성
function drawMarkers(list) {
    // 기존 마커 제거
    markers.forEach(m => m.setMap(null));
    markers = [];

    // (추가) 기존 버튼 목록 비우기
    buttonListContainer.innerHTML = ''; 

    const bounds = new kakao.maps.LatLngBounds();

    // (추가) 결과가 없는지 확인
    if (list.length === 0) {
        buttonListContainer.innerHTML = '<p class="empty-message">검색 결과가 없습니다.</p>';
        map.setCenter(new kakao.maps.LatLng(37.5665, 126.9780)); // 서울 중심으로
        map.setLevel(11);
        return; // 함수 종료
    }

    list.forEach(loc => { // <-- 리스트 순회 시작
        if (!loc.latitude || !loc.longitude) return;

        const lat = parseFloat(loc.latitude);
        const lng = parseFloat(loc.longitude);
        const position = new kakao.maps.LatLng(lat, lng);

        // 3-1. 마커 생성
        const marker = new kakao.maps.Marker({ position });
        marker.setMap(map);
        markers.push(marker);
        bounds.extend(position);

        // 3-2. 인포윈도우 생성
		const infoContent = `
		    <div style="padding:10px 10px 30px; font-size:13px; min-width:250px;">
		        <h4 style="margin:0 0 8px;">\${loc.storeId || '이름 없음'}</h4>
		        <p style="margin:0;"><strong>주소:</strong> \${(loc.address && loc.address !== 'false') ? loc.address : '정보 없음'}</p>
		        <p style="margin:0;"><strong>전화:</strong> \${(loc.phoneNumber && loc.phoneNumber !== 'false') ? loc.phoneNumber : '정보 없음'}</p>
		    </div>
		`;
		const iw = new kakao.maps.InfoWindow({ content: infoContent, removable: true });

        // 3-3. (추가) 버튼 생성
        const button = document.createElement('button');
        button.className = 'place-button';
        button.innerHTML = `
            <strong>\${loc.storeId || '이름 없음'}</strong>
            <span class="address">\${(loc.address && loc.address !== 'false') ? loc.address : ''}</span>
        `;
        buttonListContainer.appendChild(button);

		// 3-4. (추가) 마커와 버튼에 동일한 클릭 이벤트 적용
		        const onMarkerOrButtonClick = () => {
		            console.log("[DEBUG] 선택됨:", loc.storeId);
		            if (currentInfowindow) {
		                currentInfowindow.close(); // 이전에 열린 창 닫기
		            }
		            iw.open(map, marker); // 새 창 열기
		            currentInfowindow = iw; // 현재 열린 창으로 기억
		            
		            // ⭐️⭐️⭐️ 여기를 조절하세요! ⭐️⭐️⭐️
		            // 숫자가 낮을수록 더 가까이 확대됩니다. (3이나 4를 추천합니다)
		            map.setLevel(4); 
		            
		            // ⭐️⭐️⭐️ ⭐️⭐️⭐️ ⭐️⭐️⭐️ ⭐️⭐️⭐️ ⭐️⭐️⭐️

		            map.panTo(position); // 부드럽게 이동
		        };

		kakao.maps.event.addListener(marker, 'click', onMarkerOrButtonClick); // 마커 클릭
        button.addEventListener('click', onMarkerOrButtonClick); // 버튼 클릭

	}); // <-- 리스트 순회 끝

    map.setBounds(bounds); // 모든 마커가 보이도록 지도 범위 조절
    console.log(`[INFO] 지도에 ${list.length}개 표시 완료`);
}

// 4. 지역 필터 적용 함수
function filterMarkers() {
    const region = document.getElementById('regionSelect').value;
    const filtered = region ? allData.filter(d => d.address && d.address.includes(region)) : allData;
    console.log(`[INFO] 지역: ${region || '전체'} / 결과: ${filtered.length}개`);
    drawMarkers(filtered); // 필터링된 결과로 다시 그리기
}

// 5. 카카오맵 SDK 로딩 대기
const check = setInterval(() => {
    if (window.kakao && kakao.maps) {
        clearInterval(check);
        initMap(); // SDK 로드 완료되면 지도 초기화 시작
    }
}, 100);

</script>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
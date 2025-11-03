# Car_Repair
자동차 정비업소 정보 서비스 (Spring Boot 4 기반)

🧭 GitHub Desktop 팀 세팅 매뉴얼 (Car_Repair 프로젝트)

이 문서는 Car_Repair (Spring Boot 4) 팀 프로젝트를 위해
팀원이 GitHub Desktop으로 개발 환경을 처음 세팅하는 방법을 안내합니다.

모든 작업은 develop 브랜치를 기준으로 진행합니다.
main은 배포용 완성본 브랜치로, 직접 수정할 수 없습니다.

⚙️ 1️⃣ GitHub Desktop 설치 및 로그인

https://desktop.github.com
 접속

OS에 맞는 버전 다운로드 및 설치

실행 후 GitHub 계정으로 로그인
(※ 초대받은 같은 계정으로 로그인해야 함)

🧩 2️⃣ 프로젝트 클론(Clone)하기

메뉴 상단에서

File → Clone Repository

선택

URL 탭 클릭 후 아래 주소 입력:

https://github.com/leehyeonseok-NightTraveler/Car_Repair.git


Local path 선택 (예: C:\dev\work_springboot\Car_Repair)

Clone 클릭
→ 자동으로 develop 브랜치 기준으로 내려받음

🌿 3️⃣ 브랜치 확인하기

상단 왼쪽 Current branch ▼ 클릭

목록에서 develop이 선택되어 있는지 확인

만약 main으로 되어 있다면, develop 클릭하여 전환

develop이 안 보일 경우
→ 오른쪽 상단 Fetch origin 버튼 클릭 후 갱신

🔧 4️⃣ 개인 개발 브랜치 생성 (feature 브랜치)

각자 맡은 기능을 개발하기 위해 feature 브랜치를 따로 만들어야 합니다.
(직접 develop에서 작업하면 충돌이 발생합니다.)

상단 메뉴 → Branch → New Branch


브랜치 이름 규칙: feature/기능명


예시: feature/login feature/qna feature/store

Create branch based on develop 체크

Create branch 클릭

💡 새 브랜치를 만든 후 오른쪽 상단의 Push origin을 눌러
GitHub 서버에 업로드해야 다른 팀원이 볼 수 있습니다.

💾 5️⃣ 작업 후 커밋(Commit) & 푸시(Push)

수정한 파일 저장 후
GitHub Desktop 왼쪽에서 변경사항 확인

아래 입력란에 커밋 메시지 작성

[ADD] 로그인 기능 추가
[FIX] QnA 등록 오류 수정
[UPDATE] 지도 검색 UI 개선


Commit to feature/기능명 클릭

오른쪽 상단 Push origin 클릭

🔁 6️⃣ Pull Request (PR) 만들기

Push 후 상단의 “View on GitHub” 클릭

브라우저가 열리면 “Compare & Pull Request” 버튼 클릭

PR 설정 확인:

base: develop

compare: feature/기능명

제목 / 설명 입력 후 Create Pull Request

통합 담당이 코드 검토 후 병합(Merge)

🚫 7️⃣ 주의사항


❌ main 브랜치	직접 수정 금지 (보호됨)


✅ develop 브랜치	통합 테스트용


🌱 feature/* 브랜치	개인 작업용 (develop에서 분기)


🔄 PR 병합 대상	항상 develop


💬 커밋 메시지	[ADD], [FIX], [UPDATE], [REMOVE] 등 명시적으로 작성


🧠 요약


항목	브랜치	설명


main	배포/발표용	보호됨


develop	통합/기본 브랜치	모든 기능 병합용


feature/*	개인 기능 단위	개발용

체크리스트


main 보호 설정 완료	✅


default 브랜치 develop	✅


팀원 초대 완료	✅


feature 브랜치 규칙 공유	✅


PR 병합 테스트 완료	⏳

💡 Tip:


클론 후 작업 중 main 브랜치로 돌아가거나 충돌이 생기면


GitHub Desktop에서 develop으로 전환하고


“Discard changes”를 눌러 초기화할 수 있습니다.

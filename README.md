🎨 실시간 협업 그림판 시스템
📋 프로젝트 개요
Spring Boot + WebSocket을 활용한 실시간 다중 사용자 협업 그림판 웹 애플리케이션

👥 팀원
김예나: 프론트엔드 (HTML/CSS/JavaScript, WebSocket 클라이언트)
이승주: 백엔드 1 (Spring Boot, WebSocket 설정, 메시지 브로커)
정연주: 백엔드 2 (사용자 관리, 브로드캐스트, 예외처리)
🛠 기술 스택
Backend: Spring Boot 3.5.0, Spring WebSocket, Maven
Frontend: HTML5 Canvas, JavaScript, SockJS, STOMP
프로토콜: HTTP, WebSocket, TCP/IP
개발환경: Java 17+
⚡ 주요 기능
✅ 실시간 다중 사용자 그리기
✅ 색상 및 브러시 굵기 선택
✅ 캔버스 전체 초기화
✅ 접속자 수 실시간 표시
✅ 사용자 접속/퇴장 알림
✅ 모바일 터치 지원
🚀 실행 방법
1. 프로젝트 클론
   ```bash git clone https://github.com/yeonju51/25-NW.git cd 25-NW ```

2. 애플리케이션 실행
   ```bash ./mvnw spring-boot:run ```

3. 브라우저에서 접속
   ``` http://localhost:8080 ```

📁 프로젝트 구조
```
src/
├── main/
│   ├── java/com/network/drawingboard/
│   │   ├── DrawingBoardApplication.java     # 메인 애플리케이션
│   │   ├── config/
│   │   │   └── WebSocketConfig.java         # WebSocket 설정
│   │   ├── controller/
│   │   │   ├── DrawingController.java       # 그리기 컨트롤러
│   │   │   └── HomeController.java          # 홈 컨트롤러
│   │   └── model/
│   │       ├── DrawingData.java             # 그리기 데이터 모델
│   │       └── UserStatus.java              # 사용자 상태 모델
│   └── resources/
│       ├── templates/
│       │   └── index.html                   # 메인 페이지
│       └── application.properties           # 애플리케이션 설정
└── test/                                    # 테스트 파일들
```

🌐 네트워크 프로토콜 분석
WebSocket 통신 플로우
초기 연결: HTTP → WebSocket 업그레이드
실시간 데이터 전송: JSON 형태의 그리기 좌표
브로드캐스트: 서버가 모든 클라이언트에게 동시 전송
패킷 캡처 포인트
HTTP 초기 연결 요청
WebSocket 핸드셰이크
실시간 그리기 데이터 전송
사용자 접속/해제 이벤트
캔버스 초기화 명령
📊 예상 패킷 분석
Layer 2: Ethernet 프레임
Layer 3: IP 패킷
Layer 4: TCP 세그먼트
Layer 7: HTTP/WebSocket 메시지
🔧 개발 가이드
브랜치 전략
`main`: 메인 브랜치
`frontend`: 프론트엔드 개발
`backend`: 백엔드 개발
`feature/기능명`: 기능별 개발
커밋 메시지 규칙
`feat:` 새로운 기능 추가
`fix:` 버그 수정
`docs:` 문서 수정
`style:` 코드 포맷팅
`refactor:` 코드 리팩토링
`test:` 테스트 코드
🐛 알려진 이슈
대량 동시 접속 시 성능 최적화 필요
네트워크 지연 시 그림 동기화 개선
모바일 환경에서의 터치 정밀도 향상
📞 문의사항
프로젝트 관련 문의는 Issues 탭을 활용해주세요.

네트워크 프로그래밍 과제 | Spring Boot + WebSocket 실시간 협업 시스템


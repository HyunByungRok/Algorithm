# OSI 7 계층



## OSI 란?

> Open Systems Interconnection Reference Model

- 국제 표준화 기구(ISO)에서 개발한 모델로, 컴퓨터 네트워크 프로토콜 디자인과 통신을 계층으로 나누어 설명한 것
- 각 계층은 하위 계층의 기능만을 이용하고, 상위 계층에게 기능을 제공

- 일반적으로 하위 계층들은 하드웨어, 상위 계층들은 소프트웨어로 구현



## OSI 7계층

1. 물리 계층 (Physical layer)

   - 데이터를 전송하는 역할
   - 데이터를 전기적인 신호로 변환해서 주고받는 하드웨어 전송 기술
   - 데이터 전송 단위는 Bit

2. 데이터 링크 계층(Data link layer)

   - Mac 주소를 통해서 통신
   - 프레임에 Mac 주소를 부여하고 오류제어, 흐름제어를 진행한다.
   - 데이터 전송 단위는 Frame

   > 이더넷

3. 네트워크 계층(Network layer)

   - 데이터를 목적지까지 전달하는 기능을 담당
   - 라우터를 이동할 경로를 찾아주어 전달해준다. 그 과정에서 전송 계층이 요구하는 서비스 품질(QoS)를 제공하기 위한 기능적, 절차적 수단을 제공
   - 네트워크 계층은 라우팅, 흐름제어, 오류제어, 세그멘테이션 등을 수행한다.
   - 데이터 전송 단위는 Datagram(packet)

   > 라우터, IP

4. 전송 계층(Transport layer)

   - TCP, UDP 프로토콜을 통해 통신을 활성하한다. 상위 계층들이 데이터 전달의 유효성이나 효율성을 생각하지 않도록 해준다.
   - 시퀀스 넘버 기반의 오류 제어 방식을 사용
   - 전송 계층은 패킷들의 전송이 유효한지 확인하여 실패한 패킷들을 다시 전송
   - 데이터 전송 단위는 Segment

   > TCP, UDP

5. 세션 계층(Session layer)

   - TCP/IP Session을 만들고 없애는 책임을 지며 데이터가 통신하기 위한 논리적 연결을 담당
   - duplex, half-duplex, full-duplex의 통신

   > API, Socket

6. 표현 계층(Presentation layer)

   - 파일 인코딩과 명령어 포장, 압축, 암호화

   > JPEG, MPEG 등

7. 응용 계층(Application layer)

   - 응용 프로세스와 직접 관계하여 일반적인 응용 서비스를 수행

   > HTTP, FTP, DNS 등


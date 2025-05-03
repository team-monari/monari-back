-- H2 데이터베이스용 더미 데이터 (한국어 및 Enum 제약 조건 적용)

-- Location (장소) 데이터 (10건)
INSERT INTO location (service_subcategory, service_status, payment_method, location_name,
                      service_url, registration_start_date_time, registration_end_date_time,
                      cancellation_start_date_time, cancellation_end_date_time,
                      cancellation_policy_info, cancellation_deadline, x, y)
VALUES
  ('공공시설', '운영중', '신용카드', '커뮤니티 센터 A', 'http://example.com/loc1',
   '2025-04-01 09:00:00', '2025-06-30 18:00:00', '2025-04-01 09:00:00', '2025-06-28 18:00:00',
   '취소는 2일 전까지 가능', 2, '37.5665', '126.9780'),

  ('스포츠 시설', '운영중', '계좌이체', '시립 체육관', 'http://example.com/loc2',
   '2025-05-01 10:00:00', '2025-07-31 17:00:00', '2025-05-01 10:00:00', '2025-07-29 17:00:00',
   '취소는 2일 전까지 가능', 2, '37.5796', '126.9770'),

  ('교육 기관', '운영중', '현장결제', '배움누리 학습관', 'http://example.com/loc3',
   '2025-04-15 08:00:00', '2025-08-15 20:00:00', '2025-04-15 08:00:00', '2025-08-10 20:00:00',
   '취소는 5일 전까지 가능', 5, '37.5800', '126.9820'),

  ('도서관', '점검중', '무료', '중앙 도서관', 'http://example.com/loc4',
   '2025-01-01 00:00:00', '2025-12-31 23:59:59', null, null,
   '해당 없음', null, '37.5700', '126.9760'),

  ('예술 스튜디오', '운영중', '신용카드', '창작의 공간', 'http://example.com/loc5',
   '2025-06-01 11:00:00', '2025-09-30 19:00:00', '2025-06-01 11:00:00', '2025-09-27 19:00:00',
   '취소는 3일 전까지 가능', 3, '37.5610', '126.9950'),

  ('공연장', '운영중', '계좌이체', '하모니 홀', 'http://example.com/loc6',
   '2025-07-01 14:00:00', '2025-10-31 22:00:00', '2025-07-01 14:00:00', '2025-10-24 22:00:00',
   '취소는 7일 전까지 가능', 7, '37.5670', '126.9800'),

  ('과학 실험실', '운영중', '현장결제', '디스커버리 랩', 'http://example.com/loc7',
   '2025-08-01 09:30:00', '2025-11-30 17:30:00', '2025-08-01 09:30:00', '2025-11-20 17:30:00',
   '취소는 10일 전까지 가능', 10, '37.5650', '126.9870'),

  ('회의실', '비활성', '신용카드', '비즈니스 스위트 101', 'http://example.com/loc8',
   null, null, null, null,
   '해당 없음', null, '37.5580', '126.9750'),

  ('온라인 플랫폼', '운영중', '구독', '가상 교실', 'http://example.com/loc9',
   '2025-01-01 00:00:00', '2025-12-31 23:59:59', '2025-01-01 00:00:00', '2025-12-30 23:59:59',
   '다음 결제 주기 전까지 언제든 취소 가능', 1, '0.0000', '0.0000'),

  ('커뮤니티 정원', '운영중', '기부', '초록 텃밭', 'http://example.com/loc10',
   '2025-03-01 07:00:00', '2025-11-30 18:00:00', null, null,
   '계절별 이용', null, '37.5720', '126.9900');


INSERT INTO general_location(id,location_name,region,service_url, x, y)
VALUES
    (0,'토즈 강남역토즈타워점', 'GANGNAM_GU', 'https://map.naver.com/p/search/%ED%86%A0%EC%A6%88%20%EA%B0%95%EB%82%A8%EC%97%AD%ED%86%A0%EC%A6%88%ED%83%80%EC%9B%8C%EC%A0%90', 1270306659, 374967416),
                                                        (1,'토즈 강남역토즈타워점', 'GANGNAM_GU', 'https://map.naver.com/p/search/%ED%86%A0%EC%A6%88%20%EA%B0%95%EB%82%A8%EC%97%AD%ED%86%A0%EC%A6%88%ED%83%80%EC%9B%8C%EC%A0%90', 1270306659, 374967416),
                                                        (2,'랭스터디카페 대치점', 'GANGNAM_GU', 'https://map.naver.com/p/search/%EB%9E%AD%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8C%80%EC%B9%98%EC%A0%90', 1270593120, 374980923),
                                                        (3,'토즈 압구정센터', 'GANGNAM_GU', 'https://map.naver.com/p/search/%ED%86%A0%EC%A6%88%20%EC%95%95%EA%B5%AC%EC%A0%95%EC%84%BC%ED%84%B0', 1270305097, 375260243),
                                                        (4,'토즈 역삼점', 'GANGNAM_GU', 'https://map.naver.com/p/search/%ED%86%A0%EC%A6%88%20%EC%97%AD%EC%82%BC%EC%A0%90', 1270362717, 375034622),
                                                        (5,'에스랩 스터디카페 강남 3호점', 'GANGNAM_GU', 'https://map.naver.com/p/search/%EC%97%90%EC%8A%A4%EB%9E%A9%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B0%95%EB%82%A8%203%ED%98%B8%EC%A0%90', 1270305469, 374949438),
                                                        (6,'랭스터디카페 천호점', 'GANGDONG_GU', 'https://map.naver.com/p/search/%EB%9E%AD%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%B2%9C%ED%98%B8%EC%A0%90', 1271270415, 375387946),
                                                        (7,'토즈스터디센터 천호역독서실', 'GANGDONG_GU', 'https://map.naver.com/p/search/%ED%86%A0%EC%A6%88%EC%8A%A4%ED%84%B0%EB%94%94%EC%84%BC%ED%84%B0%20%EC%B2%9C%ED%98%B8%EC%97%AD%EB%8F%85%EC%84%9C%EC%8B%A4', 1271232767, 375372307),
                                                        (8,'내공관리형스터디카페 둔촌본점', 'GANGDONG_GU', 'https://map.naver.com/p/search/%EB%82%B4%EA%B3%B5%EA%B4%80%EB%A6%AC%ED%98%95%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%91%94%EC%B4%8C%EB%B3%B8%EC%A0%90', 1271343733, 375239971),
                                                        (9,'토즈멤버십라운지 천호역센터', 'GANGDONG_GU', 'https://map.naver.com/p/search/%ED%86%A0%EC%A6%88%EB%A9%A4%EB%B2%84%EC%8B%AD%EB%9D%BC%EC%9A%B4%EC%A7%80%20%EC%B2%9C%ED%98%B8%EC%97%AD%EC%84%BC%ED%84%B0', 1271232715, 375372338),
                                                        (10,'카공족 암사역점', 'GANGDONG_GU', 'https://map.naver.com/p/search/%EC%B9%B4%EA%B3%B5%EC%A1%B1%20%EC%95%94%EC%82%AC%EC%97%AD%EC%A0%90', 1271271483, 375504840),
                                                        (11,'타임유스터디카페  솔샘점', 'GANGBUK_GU', 'https://map.naver.com/p/search/%ED%83%80%EC%9E%84%EC%9C%A0%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%20%EC%86%94%EC%83%98%EC%A0%90', 1270177805, 376201707);

-- Student (학생) 데이터 (10건)
INSERT INTO student (public_id, email, name, social_provider, social_id, school_level, grade,
                     created_at, updated_at)
VALUES (RANDOM_UUID(), 'jeongmechanic99@naver.com', '김민지', 'KAKAO', 'kakao123', 'HIGH', 'FIRST',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '박서준', 'NAVER', 'naver456', 'MIDDLE', 'SECOND',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '이하은', 'KAKAO', 'kakao789', 'HIGH', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '최우진', 'KAKAO', 'kakao788', 'HIGH', 'SECOND',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '정다현', 'NAVER', 'naver101', 'MIDDLE', 'FIRST',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '강지훈', 'KAKAO', 'kakao202', 'HIGH', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '윤채원', 'KAKAO', 'kakao205', 'MIDDLE', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '한동민', 'NAVER', 'naver303', 'MIDDLE', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '배수아', 'KAKAO', 'kakao404', 'HIGH', 'FIRST',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '송현우', NULL, NULL, 'MIDDLE', 'SECOND',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '서지훈', 'KAKAO', 'kakao505', 'HIGH', 'SECOND',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '문채영', 'NAVER', 'naver606', 'MIDDLE', 'FIRST',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '임태현', 'KAKAO', 'kakao707', 'HIGH', 'FIRST',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '조예린', 'KAKAO', 'kakao808', 'MIDDLE', 'SECOND',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'jeongmechanic99@naver.com', '홍도윤', 'NAVER', 'naver909', 'HIGH', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Teacher (선생님) 데이터 (10건)

INSERT INTO teacher (public_id,
                     email,
                     name,
                     social_provider,
                     social_id,
                     university,
                     major,
                     career,
                     created_at,
                     updated_at,
                     bank_name,
                     account_number,
                     account_holder)
VALUES (RANDOM_UUID(), 'teacher1@example.com', '김철수 교수', 'KAKAO', 't_kakao1', '한국대학교', '수학과',
        '10년 강의 경력, 미적분학 전문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '국민은행', '1111111111', '김철수 교수'),

       (RANDOM_UUID(), 'teacher2@example.com', '이영희 박사', 'NAVER', 't_naver2', '서울대학교', '물리학과',
        '5년 연구 및 3년 강의, 양자역학', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '신한은행', '2222222222',
        '이영희 박사'),

       (RANDOM_UUID(), 'teacher3@example.com', '박민준 선생님', 'NAVER', 't_naver3', '연세대학교', '화학과',
        '고등학교 화학 교사 7년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '하나은행', '3333333333', '박민준 선생님'),

       (RANDOM_UUID(), 'teacher4@example.com', '최지우 교사', 'KAKAO', 't_kakao4', '고려대학교', '생명과학과',
        '중학교 과학 교사 5년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '우리은행', '4444444444', '최지우 교사'),

       (RANDOM_UUID(), 'teacher5@example.com', '정다빈 연구원', 'NAVER', 't_naver5', 'KAIST', '수학과',
        '응용수학 연구 8년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '농협은행', '5555555555', '정다빈 연구원'),

       (RANDOM_UUID(), 'teacher6@example.com', '강태현 선생님', 'NAVER', 't_naver6', '성균관대학교', '물리학과',
        '물리 올림피아드 지도 경험 다수', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '국민은행', '6666666666', '강태현 선생님'),

       (RANDOM_UUID(), 'teacher7@example.com', '윤서아 박사', 'KAKAO', 't_kakao7', '포항공과대학교', '화학과',
        '유기화학 연구 및 특강 전문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '신한은행', '7777777777', '윤서아 박사'),

       (RANDOM_UUID(), 'teacher8@example.com', '한지민 교사', 'NAVER', 't_naver8', '한양대학교', '생명과학과',
        '과학 탐구반 지도 6년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '하나은행', '8888888888', '한지민 교사'),

       (RANDOM_UUID(), 'teacher9@example.com', '배준호 교수', 'NAVER', 't_naver9', '경희대학교', '수학과',
        '확률 및 통계 강의 12년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '우리은행', '9999999999', '배준호 교수'),

       (RANDOM_UUID(), 'teacher10@example.com', '송지효 연구원', 'KAKAO', 't_kakao10', '중앙대학교', '물리학과',
        '천체물리학 연구 4년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '농협은행', '1010101010', '송지효 연구원');

-- Lesson (수업) 데이터 (10건) - location_id 고유하게 수정됨
-- Location ID 1-10 및 Teacher ID 1-10 사용 가정
INSERT INTO lesson (general_location_id, teacher_id, title, description, amount, min_student, max_student,
                    start_date, end_date, deadline, status, school_level, subject, created_at,
                    updated_at, region, type)
VALUES (1, 1, '고등수학 미적분 심화 과정', '미적분의 개념부터 심화 문제까지 단계적으로 학습하는 고등학생 대상의 집중 수학 과정입니다.', 120000, 1, 4,
        CURRENT_DATE, DATE '2025-12-15', DATE '2025-08-25', 'ACTIVE', 'HIGH', 'MATH',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'EUNPYEONG_GU', 'OFFLINE'),

       (2, 2, '고등물리 역학 마스터 클래스', '운동의 법칙과 에너지 보존을 중심으로 역학 전반을 깊이 있게 다루는 고등 물리 심화 수업입니다.', 110000, 2,
        5,
        CURRENT_DATE, DATE '2025-11-28', DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JONGNO_GU', 'OFFLINE'),

       (3, 3, '중학화학 기초 다지기', '중학생을 위한 화학 기초 개념 수업으로 원소, 분자, 화학반응 등 과학의 기초를 쉽게 학습합니다.', 90000, 1, 4,
        CURRENT_DATE, DATE '2025-07-10', DATE '2025-05-01', 'ACTIVE', 'MIDDLE', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JONGNO_GU', 'ONLINE'),

       (4, 4, '중학 생명과학 탐구 실험', '생물의 기본 구조와 기능을 중심으로 다양한 실험 활동을 통해 탐구 능력을 기르는 수업입니다.', 95000, 1, 18,
        CURRENT_DATE, DATE '2025-08-31', DATE '2025-05-20', 'CANCELED', 'MIDDLE', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JONGNO_GU', 'OFFLINE'),

       (5, 5, '코딩으로 배우는 수학 원리', '파이썬 코딩을 활용해 수학 원리를 시각화하고 직접 구현하며 수학적 사고력을 기르는 융합 수업입니다.', 150000,
        1, 10,
        CURRENT_DATE, DATE '2026-01-31', DATE '2025-09-15', 'ACTIVE', 'HIGH', 'MATH',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JONGNO_GU', 'ONLINE'),

       (6, 6, '물리 올림피아드 대비반', '물리 경시대회 및 올림피아드 참가자를 위한 이론 정리와 문제풀이 중심의 고급 집중 과정입니다.', 130000, 7, 15,
        CURRENT_DATE, DATE '2025-12-20', DATE '2025-08-30', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SONGPA_GU', 'OFFLINE'),

       (7, 7, '유기화학 입문 특강', '유기화학의 기본 개념과 작용기, 반응 메커니즘을 쉽고 명확하게 소개하는 입문자 대상의 특강입니다.', 100000, 10,
        20,
        CURRENT_DATE, DATE '2025-12-16', DATE '2025-08-26', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SEOCHO_GU', 'OFFLINE'),

       (8, 8, '중학 과학 내신 완성반', '중학교 과학 내신 대비를 위한 개념 정리와 핵심 문제 풀이로 성적 향상을 목표로 한 강의입니다.', 85000, 12,
        30,
        CURRENT_DATE, DATE '2025-09-07', DATE '2025-07-01', 'CANCELED', 'MIDDLE', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU', 'OFFLINE'),

       (9, 9, '확률과 통계 개념 및 문제풀이', '확률과 통계 전반의 개념을 다지고 수능 및 내신을 대비한 실전 문제풀이 중심으로 구성된 강의입니다.', 115000,
        8, 18,
        CURRENT_DATE, DATE '2026-02-28', DATE '2025-10-20', 'ACTIVE', 'HIGH', 'MATH',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU', 'ONLINE'),

       (3, 8, '천체물리학 맛보기 강좌', '별, 행성, 우주에 대한 흥미로운 주제를 중심으로 천체물리학의 기초 개념을 배워보는 체험형 수업입니다.', 140000,
        5, 12,
        CURRENT_DATE, DATE '2025-12-18', DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU', 'ONLINE'),

        (3, 8, '천체물리학 맛보기 강좌', '별, 행성, 우주에 대한 흥미로운 주제를 중심으로 천체물리학의 기초 개념을 배워보는 체험형 수업입니다.', 140000,
        5, 12,
        CURRENT_DATE, DATE '2025-12-18', DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU', 'ONLINE'),

        (3, 8, '천체물리학 맛보기 강좌', '별, 행성, 우주에 대한 흥미로운 주제를 중심으로 천체물리학의 기초 개념을 배워보는 체험형 수업입니다.', 140000,
        5, 12,
        CURRENT_DATE, DATE '2025-12-18', DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU', 'ONLINE');


-- Study (스터디) 데이터 (10건) - status 'INACTIVE' -> 'CLOSED' 수정됨
-- Location ID 1-10 사용 가정
INSERT INTO study (title, description, subject, school_level, status, location_id, student_id,
                   created_at,
                   updated_at,
                   region)
VALUES ('고등수학 스터디 - 미적분 문제풀이', '매주 모여 미적분 심화 문제를 함께 풀이합니다.', 'MATH', 'HIGH', 'ACTIVE', 1, 2,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU'),
       ('물리 스터디 - 역학 집중 탐구', '뉴턴 역학 중심으로 개념 이해 및 문제 해결 능력 향상.', 'SCIENCE', 'HIGH', 'ACTIVE', 2, 3,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU'),
       ('중학수학 내신 대비 스터디', '학교 시험 범위 맞춰 개념 정리 및 문제 풀이 진행.', 'MATH', 'MIDDLE', 'CLOSED', 3, 4,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GANGBUK_GU'),
       ('화학 반응식 완전 정복', '다양한 화학 반응식을 이해하고 암기하는 스터디.', 'SCIENCE', 'HIGH', 'ACTIVE', 7, 1,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU'),
       ('생명과학 토론 스터디', '최신 생명과학 이슈에 대해 토론하고 발표합니다.', 'SCIENCE', 'HIGH', 'ACTIVE', 9, 6,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU'),
       ('확률과 통계 개념 완성', '확률과 통계 기본 개념부터 응용까지 함께 공부합니다.', 'MATH', 'HIGH', 'ACTIVE', 5, 8,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SEOCHO_GU'),
       ('과학 실험 보고서 작성법', '실험 설계부터 결과 분석, 보고서 작성까지 연습합니다.', 'SCIENCE', 'MIDDLE', 'ACTIVE', 7, 8,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU'),
       ('수학 경시대회 준비 모임', '고난도 수학 문제 풀이 및 전략 공유.', 'MATH', 'HIGH', 'CLOSED', 1, 10,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU'),
       ('중학 과학 개념 노트 정리', '과학 교과서 핵심 내용을 함께 정리하고 복습합니다.', 'SCIENCE', 'MIDDLE', 'ACTIVE', 3, 5,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU'),
       ('물리 심화 문제 도전', '물리 올림피아드 수준의 문제 풀이에 도전합니다.', 'SCIENCE', 'HIGH', 'ACTIVE', 2, 1,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GANGBUK_GU');


-- Enrollment 테이블 더미 데이터 삽입
INSERT INTO enrollment (created_at, updated_at, student_id, lesson_id,status)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 'REQUESTED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3, 'COMPLETED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1, 'REFUND_REQUESTED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2, 'PENDING'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 2, 'CANCELLED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 4, 'COMPLETED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 3, 'REFUNDED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 5, 'CANCELLED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 4, 'REQUESTED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 'PENDING'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 1, 'COMPLETED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 3, 'REFUND_REQUESTED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 2, 'PENDING'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 4, 'REFUNDED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 3, 'CANCELLED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 5, 'COMPLETED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9, 1, 'REQUESTED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9, 2, 'PENDING'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 4, 'REFUND_REQUESTED'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 5, 'COMPLETED');
CREATE SCHEMA IF NOT EXISTS batch;

DROP TABLE IF EXISTS batch.BATCH_STEP_EXECUTION_CONTEXT;
DROP TABLE IF EXISTS batch.BATCH_JOB_EXECUTION_CONTEXT;
DROP TABLE IF EXISTS batch.BATCH_STEP_EXECUTION;
DROP TABLE IF EXISTS batch.BATCH_JOB_EXECUTION_PARAMS;
DROP TABLE IF EXISTS batch.BATCH_JOB_EXECUTION;
DROP TABLE IF EXISTS batch.BATCH_JOB_INSTANCE;

DROP SEQUENCE IF EXISTS batch.BATCH_STEP_EXECUTION_SEQ;
DROP SEQUENCE IF EXISTS batch.BATCH_JOB_EXECUTION_SEQ;
DROP SEQUENCE IF EXISTS batch.BATCH_JOB_SEQ;


-- Autogenerated: do not edit this file

CREATE TABLE batch.BATCH_JOB_INSTANCE  (
	JOB_INSTANCE_ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
	VERSION BIGINT ,
	JOB_NAME VARCHAR(100) NOT NULL,
	JOB_KEY VARCHAR(32) NOT NULL,
	constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ;

CREATE TABLE batch.BATCH_JOB_EXECUTION  (
	JOB_EXECUTION_ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
	VERSION BIGINT  ,
	JOB_INSTANCE_ID BIGINT NOT NULL,
	CREATE_TIME TIMESTAMP(9) NOT NULL,
	START_TIME TIMESTAMP(9) DEFAULT NULL ,
	END_TIME TIMESTAMP(9) DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED TIMESTAMP(9),
	constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
	references batch.BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ;

CREATE TABLE batch.BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL ,
	PARAMETER_NAME VARCHAR(100) NOT NULL ,
	PARAMETER_TYPE VARCHAR(100) NOT NULL ,
	PARAMETER_VALUE VARCHAR(2500) ,
	IDENTIFYING CHAR(1) NOT NULL ,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references batch.BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

CREATE TABLE batch.BATCH_STEP_EXECUTION  (
	STEP_EXECUTION_ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
	VERSION BIGINT NOT NULL,
	STEP_NAME VARCHAR(100) NOT NULL,
	JOB_EXECUTION_ID BIGINT NOT NULL,
	CREATE_TIME TIMESTAMP(9) NOT NULL,
	START_TIME TIMESTAMP(9) DEFAULT NULL ,
	END_TIME TIMESTAMP(9) DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	COMMIT_COUNT BIGINT ,
	READ_COUNT BIGINT ,
	FILTER_COUNT BIGINT ,
	WRITE_COUNT BIGINT ,
	READ_SKIP_COUNT BIGINT ,
	WRITE_SKIP_COUNT BIGINT ,
	PROCESS_SKIP_COUNT BIGINT ,
	ROLLBACK_COUNT BIGINT ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED TIMESTAMP(9),
	constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
	references batch.BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

CREATE TABLE batch.BATCH_STEP_EXECUTION_CONTEXT  (
	STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT LONGVARCHAR ,
	constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
	references batch.BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ;

CREATE TABLE batch.BATCH_JOB_EXECUTION_CONTEXT  (
	JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT LONGVARCHAR ,
	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
	references batch.BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

CREATE SEQUENCE batch.BATCH_STEP_EXECUTION_SEQ;
CREATE SEQUENCE batch.BATCH_JOB_EXECUTION_SEQ;
CREATE SEQUENCE batch.BATCH_JOB_SEQ;



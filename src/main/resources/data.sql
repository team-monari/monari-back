-- H2 데이터베이스용 더미 데이터 (한국어 및 Enum 제약 조건 적용)

-- Location (장소) 데이터 (10건)
INSERT INTO location (service_subcategory, service_status, payment_method, location_name,
                      service_url, registration_start_date_time, registration_end_date_time,
                      cancellation_start_date_time, cancellation_end_date_time,
                      cancellation_policy_info, cancellation_deadline)
VALUES ('공공시설', '운영중', '신용카드', '커뮤니티 센터 A', 'http://example.com/loc1',
        TIMESTAMP '2025-04-01 09:00:00', TIMESTAMP '2025-06-30 18:00:00',
        TIMESTAMP '2025-04-01 09:00:00', TIMESTAMP '2025-06-28 18:00:00', '취소는 2일 전까지 가능', 2),
       ('스포츠 시설', '운영중', '계좌이체', '시립 체육관', 'http://example.com/loc2',
        TIMESTAMP '2025-05-01 10:00:00', TIMESTAMP '2025-07-31 17:00:00',
        TIMESTAMP '2025-05-01 10:00:00', TIMESTAMP '2025-07-29 17:00:00', '취소는 2일 전까지 가능', 2),
       ('교육 기관', '운영중', '현장결제', '배움누리 학습관', 'http://example.com/loc3',
        TIMESTAMP '2025-04-15 08:00:00', TIMESTAMP '2025-08-15 20:00:00',
        TIMESTAMP '2025-04-15 08:00:00', TIMESTAMP '2025-08-10 20:00:00', '취소는 5일 전까지 가능', 5),
       ('도서관', '점검중', '무료', '중앙 도서관', 'http://example.com/loc4', TIMESTAMP '2025-01-01 00:00:00',
        TIMESTAMP '2025-12-31 23:59:59', null, null, '해당 없음', null),
       ('예술 스튜디오', '운영중', '신용카드', '창작의 공간', 'http://example.com/loc5',
        TIMESTAMP '2025-06-01 11:00:00', TIMESTAMP '2025-09-30 19:00:00',
        TIMESTAMP '2025-06-01 11:00:00', TIMESTAMP '2025-09-27 19:00:00', '취소는 3일 전까지 가능', 3),
       ('공연장', '운영중', '계좌이체', '하모니 홀', 'http://example.com/loc6', TIMESTAMP '2025-07-01 14:00:00',
        TIMESTAMP '2025-10-31 22:00:00', TIMESTAMP '2025-07-01 14:00:00',
        TIMESTAMP '2025-10-24 22:00:00', '취소는 7일 전까지 가능', 7),
       ('과학 실험실', '운영중', '현장결제', '디스커버리 랩', 'http://example.com/loc7',
        TIMESTAMP '2025-08-01 09:30:00', TIMESTAMP '2025-11-30 17:30:00',
        TIMESTAMP '2025-08-01 09:30:00', TIMESTAMP '2025-11-20 17:30:00', '취소는 10일 전까지 가능', 10),
       ('회의실', '비활성', '신용카드', '비즈니스 스위트 101', 'http://example.com/loc8', null, null, null, null,
        '해당 없음', null),
       ('온라인 플랫폼', '운영중', '구독', '가상 교실', 'http://example.com/loc9', TIMESTAMP '2025-01-01 00:00:00',
        TIMESTAMP '2025-12-31 23:59:59', TIMESTAMP '2025-01-01 00:00:00',
        TIMESTAMP '2025-12-30 23:59:59', '다음 결제 주기 전까지 언제든 취소 가능', 1),
       ('커뮤니티 정원', '운영중', '기부', '초록 텃밭', 'http://example.com/loc10',
        TIMESTAMP '2025-03-01 07:00:00', TIMESTAMP '2025-11-30 18:00:00', null, null, '계절별 이용',
        null);

-- Student (학생) 데이터 (10건)
INSERT INTO student (public_id, email, name, social_provider, social_id, school_level, grade,
                     created_at, updated_at)
VALUES (RANDOM_UUID(), 'student1@example.com', '김민지', 'KAKAO', 'kakao123', 'HIGH', 'FIRST',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student2@example.com', '박서준', 'NAVER', 'naver456', 'MIDDLE', 'SECOND',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student3@example.com', '이하은', 'KAKAO', 'kakao789', 'HIGH', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student4@example.com', '최우진', 'KAKAO', 'kakao788', 'HIGH', 'SECOND',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student5@example.com', '정다현', 'NAVER', 'naver101', 'MIDDLE', 'FIRST',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student6@example.com', '강지훈', 'KAKAO', 'kakao202', 'HIGH', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student7@example.com', '윤채원', 'KAKAO', 'kakao205', 'MIDDLE', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student8@example.com', '한동민', 'NAVER', 'naver303', 'MIDDLE', 'THIRD',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student9@example.com', '배수아', 'KAKAO', 'kakao404', 'HIGH', 'FIRST',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'student10@example.com', '송현우', null, null, 'MIDDLE', 'SECOND',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Teacher (선생님) 데이터 (10건)
INSERT INTO teacher (public_id, email, name, social_provider, social_id, university, major, career,
                     created_at, updated_at)
VALUES (RANDOM_UUID(), 'teacher1@example.com', '김철수 교수', 'KAKAO', 't_kakao1', '한국대학교', '수학과',
        '10년 강의 경력, 미적분학 전문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher2@example.com', '이영희 박사', 'NAVER', 't_naver2', '서울대학교', '물리학과',
        '5년 연구 및 3년 강의, 양자역학', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher3@example.com', '박민준 선생님', 'NAVER', 't_naver3', '연세대학교', '화학과',
        '고등학교 화학 교사 7년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher4@example.com', '최지우 교사', 'KAKAO', 't_kakao4', '고려대학교', '생명과학과',
        '중학교 과학 교사 5년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher5@example.com', '정다빈 연구원', 'NAVER', 't_naver5', 'KAIST', '수학과',
        '응용수학 연구 8년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher6@example.com', '강태현 선생님', 'NAVER', 't_naver6', '성균관대학교', '물리학과',
        '물리 올림피아드 지도 경험 다수', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher7@example.com', '윤서아 박사', 'KAKAO', 't_kakao7', '포항공과대학교', '화학과',
        '유기화학 연구 및 특강 전문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher8@example.com', '한지민 교사', 'NAVER', 't_naver8', '한양대학교', '생명과학과',
        '과학 탐구반 지도 6년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher9@example.com', '배준호 교수', 'NAVER', 't_naver9', '경희대학교', '수학과',
        '확률 및 통계 강의 12년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (RANDOM_UUID(), 'teacher10@example.com', '송지효 연구원', 'KAKAO', 't_kakao10', '중앙대학교', '물리학과',
        '천체물리학 연구 4년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Lesson (수업) 데이터 (10건) - location_id 고유하게 수정됨
-- Location ID 1-10 및 Teacher ID 1-10 사용 가정
INSERT INTO lesson (location_id, teacher_id, title, description, amount, min_student, max_student,
                    start_date, end_date, deadline, status, school_level, subject, created_at,
                    updated_at)
VALUES (1, 1, '고등수학 미적분 심화 과정', '미적분의 개념부터 심화 문제까지 단계적으로 학습하는 고등학생 대상의 집중 수학 과정입니다.', 120000, 1, 2,
        DATE '2025-09-01', DATE '2025-12-15',
        DATE '2025-08-25', 'ACTIVE', 'HIGH', 'MATH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (2, 2, '고등물리 역학 마스터 클래스', '운동의 법칙과 에너지 보존을 중심으로 역학 전반을 깊이 있게 다루는 고등 물리 심화 수업입니다.', 110000, 2,
        5, DATE '2025-09-08', DATE '2025-11-28',
        DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (3, 3, '중학화학 기초 다지기', '중학생을 위한 화학 기초 개념 수업으로 원소, 분자, 화학반응 등 과학의 기초를 쉽게 학습합니다.', 90000, 1,
        4, DATE '2025-05-10', DATE '2025-07-10',
        DATE '2025-05-01', 'ACTIVE', 'MIDDLE', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (4, 4, '중학 생명과학 탐구 실험', '생물의 기본 구조와 기능을 중심으로 다양한 실험 활동을 통해 탐구 능력을 기르는 수업입니다.', 95000, 6, 18,
        DATE '2025-06-01', DATE '2025-08-31',
        DATE '2025-05-20', 'CANCELED', 'MIDDLE', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (5, 5, '코딩으로 배우는 수학 원리', '파이썬 코딩을 활용해 수학 원리를 시각화하고 직접 구현하며 수학적 사고력을 기르는 융합 수업입니다.', 150000,
        5, 10, DATE '2025-10-01', DATE '2026-01-31',
        DATE '2025-09-15', 'ACTIVE', 'HIGH', 'MATH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (6, 6, '물리 올림피아드 대비반', '물리 경시대회 및 올림피아드 참가자를 위한 이론 정리와 문제풀이 중심의 고급 집중 과정입니다.', 130000, 7, 15,
        DATE '2025-09-05', DATE '2025-12-20',
        DATE '2025-08-30', 'ACTIVE', 'HIGH', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (7, 7, '유기화학 입문 특강', '유기화학의 기본 개념과 작용기, 반응 메커니즘을 쉽고 명확하게 소개하는 입문자 대상의 특강입니다.', 100000, 10,
        20, DATE '2025-09-02', DATE '2025-12-16',
        DATE '2025-08-26', 'ACTIVE', 'HIGH', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (8, 8, '중학 과학 내신 완성반', '중학교 과학 내신 대비를 위한 개념 정리와 핵심 문제 풀이로 성적 향상을 목표로 한 강의입니다.', 85000, 12,
        30, DATE '2025-07-07', DATE '2025-09-07',
        DATE '2025-07-01', 'CANCELED', 'MIDDLE', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (9, 9, '확률과 통계 개념 및 문제풀이', '확률과 통계 전반의 개념을 다지고 수능 및 내신을 대비한 실전 문제풀이 중심으로 구성된 강의입니다.', 115000,
        8, 18, DATE '2025-11-01', DATE '2026-02-28',
        DATE '2025-10-20', 'ACTIVE', 'HIGH', 'MATH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

       (10, 8, '천체물리학 맛보기 강좌', '별, 행성, 우주에 대한 흥미로운 주제를 중심으로 천체물리학의 기초 개념을 배워보는 체험형 수업입니다.', 140000,
        5, 12, DATE '2025-09-15', DATE '2025-12-18',
        DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 3, '고등수학 II 집중반', '함수와 그래프, 지수와 로그 등 고등수학 II 전범위를 다루는 과정입니다.', 110000, 3, 1,
        DATE '2025-09-01', DATE '2025-12-01', DATE '2025-08-20', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 1, '중등 수학 심화과정', '중학교 수학의 전 단원을 빠짐없이 심화 학습하며 수학적 사고력을 기릅니다.', 95000, 1, 2,
        DATE '2025-09-05', DATE '2025-11-30', DATE '2025-08-15', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 5, '기초 연산과 수 감각', '초등학생을 위한 덧셈, 뺄셈, 곱셈, 나눗셈 집중 훈련 과정입니다.', 70000, 5, 3,
        DATE '2025-09-03', DATE '2025-11-20', DATE '2025-08-10', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 4, '수능 대비 확률과 통계', '수능 특화 확률과 통계 집중 훈련 과정으로, 실전 문제 위주로 진행됩니다.', 125000, 2, 4,
        DATE '2025-09-01', DATE '2025-12-10', DATE '2025-08-25', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (6, 6, '함수 마스터 클래스', '중고등학생을 대상으로 한 함수 심화 과정. 다양한 함수의 성질과 그래프 분석 중심.', 115000, 4, 5,
        DATE '2025-09-02', DATE '2025-12-05', DATE '2025-08-22', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (7, 2, '초등 고학년 수학 도약반', '6학년 수준의 수학을 중학교 과정을 염두에 두고 미리 학습하는 과정입니다.', 80000, 2, 6,
        DATE '2025-09-07', DATE '2025-11-29', DATE '2025-08-18', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (8, 7, '중등 함수와 방정식 집중반', '중학교 수학에서 핵심인 함수와 방정식 단원을 심도 깊게 학습합니다.', 98000, 7, 7,
        DATE '2025-09-04', DATE '2025-12-01', DATE '2025-08-19', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (9, 8, '수학 올림피아드 대비반', '수학적 창의력과 사고력을 바탕으로 한 수학 경시대회 대비 심화 과정입니다.', 135000, 6, 8,
        DATE '2025-09-06', DATE '2025-12-12', DATE '2025-08-20', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (10, 10, '중3 내신 만점반', '중3 수학 내신 대비를 위한 전 범위 문제 풀이 중심의 집중 수업입니다.', 102000, 9, 9,
        DATE '2025-09-01', DATE '2025-11-25', DATE '2025-08-17', 'ACTIVE', 'HIGH', 'MATH',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 9, '수학 개념 탄탄 기초반', '수학의 기초 개념을 처음부터 체계적으로 정리하고 이해하는 초급자 대상 과정입니다.', 65000, 10, 10,
        DATE '2025-09-08', DATE '2025-11-15', DATE '2025-08-10', 'ACTIVE', 'HIGH', 'SCIENCE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- Study (스터디) 데이터 (10건) - status 'INACTIVE' -> 'CLOSED' 수정됨
-- Location ID 1-10 사용 가정
INSERT INTO study (title, description, subject, school_level, status, location_id, student_id, created_at,
                   updated_at)
VALUES ('고등수학 스터디 - 미적분 문제풀이', '매주 모여 미적분 심화 문제를 함께 풀이합니다.', 'MATH', 'HIGH', 'ACTIVE', 1, 2,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('물리 스터디 - 역학 집중 탐구', '뉴턴 역학 중심으로 개념 이해 및 문제 해결 능력 향상.', 'SCIENCE', 'HIGH', 'ACTIVE', 2, 3,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('중학수학 내신 대비 스터디', '학교 시험 범위 맞춰 개념 정리 및 문제 풀이 진행.', 'MATH', 'MIDDLE', 'CLOSED', 3, 4,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('화학 반응식 완전 정복', '다양한 화학 반응식을 이해하고 암기하는 스터디.', 'SCIENCE', 'HIGH', 'ACTIVE', 7, 1,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('생명과학 토론 스터디', '최신 생명과학 이슈에 대해 토론하고 발표합니다.', 'SCIENCE', 'HIGH', 'ACTIVE', 9, 6,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('확률과 통계 개념 완성', '확률과 통계 기본 개념부터 응용까지 함께 공부합니다.', 'MATH', 'HIGH', 'ACTIVE', 5, 8,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('과학 실험 보고서 작성법', '실험 설계부터 결과 분석, 보고서 작성까지 연습합니다.', 'SCIENCE', 'MIDDLE', 'ACTIVE', 7, 8,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('수학 경시대회 준비 모임', '고난도 수학 문제 풀이 및 전략 공유.', 'MATH', 'HIGH', 'CLOSED', 1, 10,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('중학 과학 개념 노트 정리', '과학 교과서 핵심 내용을 함께 정리하고 복습합니다.', 'SCIENCE', 'MIDDLE', 'ACTIVE', 3, 5,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('물리 심화 문제 도전', '물리 올림피아드 수준의 문제 풀이에 도전합니다.', 'SCIENCE', 'HIGH', 'ACTIVE', 2, 1,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- Enrollment 테이블 더미 데이터 삽입
INSERT INTO enrollment (id, created_at, updated_at, student_id, lesson_id)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
       (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
       (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1),
       (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2),
       (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 2),
       (6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 4),
       (7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 3),
       (8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 5),
       (9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 4),
       (10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5);
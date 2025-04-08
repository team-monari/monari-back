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
INSERT INTO lesson (location_id, teacher_id, description, amount, min_student, max_student,
                    start_date, end_date, deadline, status, school_level, subject, created_at,
                    updated_at)
VALUES (1, 1, '고등수학 미적분 심화 과정', 120000, 5, 15, DATE '2025-09-01', DATE '2025-12-15',
        DATE '2025-08-25', 'ACTIVE', 'HIGH', 'MATH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, '고등물리 역학 마스터 클래스', 110000, 8, 20, DATE '2025-09-08', DATE '2025-11-28',
        DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, '중학화학 기초 다지기', 90000, 10, 25, DATE '2025-05-10', DATE '2025-07-10', DATE '2025-05-01',
        'ACTIVE', 'MIDDLE', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 4, '중학 생명과학 탐구 실험', 95000, 6, 18, DATE '2025-06-01', DATE '2025-08-31',
        DATE '2025-05-20', 'CANCELED', 'MIDDLE', 'SCIENCE', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP), -- location_id 수정됨
       (5, 5, '코딩으로 배우는 수학 원리', 150000, 5, 10, DATE '2025-10-01', DATE '2026-01-31',
        DATE '2025-09-15', 'ACTIVE', 'HIGH', 'MATH', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP), -- location_id 수정됨
       (6, 6, '물리 올림피아드 대비반', 130000, 7, 15, DATE '2025-09-05', DATE '2025-12-20',
        DATE '2025-08-30', 'ACTIVE', 'HIGH', 'SCIENCE', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP), -- location_id 수정됨
       (7, 7, '유기화학 입문 특강', 100000, 10, 20, DATE '2025-09-02', DATE '2025-12-16', DATE '2025-08-26',
        'ACTIVE', 'HIGH', 'SCIENCE', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP), -- location_id 수정됨 (값은 7)
       (8, 8, '중학 과학 내신 완성반', 85000, 12, 30, DATE '2025-07-07', DATE '2025-09-07',
        DATE '2025-07-01', 'CANCELED', 'MIDDLE', 'SCIENCE', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP), -- location_id 수정됨
       (9, 9, '확률과 통계 개념 및 문제풀이', 115000, 8, 18, DATE '2025-11-01', DATE '2026-02-28',
        DATE '2025-10-20', 'ACTIVE', 'HIGH', 'MATH', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP), -- location_id 수정됨
       (10, 10, '천체물리학 맛보기 강좌', 140000, 5, 12, DATE '2025-09-15', DATE '2025-12-18',
        DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- Study (스터디) 데이터 (10건) - status 'INACTIVE' -> 'CLOSED' 수정됨
-- Location ID 1-10 사용 가정
INSERT INTO study (title, description, subject, school_level, status, location_id, created_at,
                   updated_at)
VALUES ('고등수학 스터디 - 미적분 문제풀이', '매주 모여 미적분 심화 문제를 함께 풀이합니다.', 'MATH', 'HIGH', 'ACTIVE', 1,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('물리 스터디 - 역학 집중 탐구', '뉴턴 역학 중심으로 개념 이해 및 문제 해결 능력 향상.', 'SCIENCE', 'HIGH', 'ACTIVE', 2,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('중학수학 내신 대비 스터디', '학교 시험 범위 맞춰 개념 정리 및 문제 풀이 진행.', 'MATH', 'MIDDLE', 'CLOSED', 3,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- 'INACTIVE' -> 'CLOSED' 수정
       ('화학 반응식 완전 정복', '다양한 화학 반응식을 이해하고 암기하는 스터디.', 'SCIENCE', 'HIGH', 'ACTIVE', 7,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('생명과학 토론 스터디', '최신 생명과학 이슈에 대해 토론하고 발표합니다.', 'SCIENCE', 'HIGH', 'ACTIVE', 9,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('확률과 통계 개념 완성', '확률과 통계 기본 개념부터 응용까지 함께 공부합니다.', 'MATH', 'HIGH', 'ACTIVE', 5,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('과학 실험 보고서 작성법', '실험 설계부터 결과 분석, 보고서 작성까지 연습합니다.', 'SCIENCE', 'MIDDLE', 'ACTIVE', 7,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('수학 경시대회 준비 모임', '고난도 수학 문제 풀이 및 전략 공유.', 'MATH', 'HIGH', 'CLOSED', 1, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),                    -- 'INACTIVE' -> 'CLOSED' 수정
       ('중학 과학 개념 노트 정리', '과학 교과서 핵심 내용을 함께 정리하고 복습합니다.', 'SCIENCE', 'MIDDLE', 'ACTIVE', 3,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('물리 심화 문제 도전', '물리 올림피아드 수준의 문제 풀이에 도전합니다.', 'SCIENCE', 'HIGH', 'ACTIVE', 2,
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
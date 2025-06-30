-- MySQL

INSERT INTO location (service_subcategory, service_status, payment_method, location_name,
                      service_url, registration_start_date_time, registration_end_date_time,
                      cancellation_start_date_time, cancellation_end_date_time,
                      cancellation_policy_info, cancellation_deadline, x, y)
VALUES ('공공시설', '운영중', '신용카드', '커뮤니티 센터 A', 'http://example.com/loc1',
        '2025-04-01 09:00:00', '2025-06-30 18:00:00', '2025-04-01 09:00:00', '2025-06-28 18:00:00',
        '취소는 2일 전까지 가능', 2, null, null),

       ('스포츠 시설', '운영중', '계좌이체', '시립 체육관', 'http://example.com/loc2',
        '2025-05-01 10:00:00', '2025-07-31 17:00:00', '2025-05-01 10:00:00', '2025-07-29 17:00:00',
        '취소는 2일 전까지 가능', 2, null, null),

       ('교육 기관', '운영중', '현장결제', '배움누리 학습관', 'http://example.com/loc3',
        '2025-04-15 08:00:00', '2025-08-15 20:00:00', '2025-04-15 08:00:00', '2025-08-10 20:00:00',
        '취소는 5일 전까지 가능', 5, null, null),

       ('도서관', '점검중', '무료', '중앙 도서관', 'http://example.com/loc4',
        '2025-01-01 00:00:00', '2025-12-31 23:59:59', null, null,
        '해당 없음', null, null, null),

       ('예술 스튜디오', '운영중', '신용카드', '창작의 공간', 'http://example.com/loc5',
        '2025-06-01 11:00:00', '2025-09-30 19:00:00', '2025-06-01 11:00:00', '2025-09-27 19:00:00',
        '취소는 3일 전까지 가능', 3, null, null),

       ('공연장', '운영중', '계좌이체', '하모니 홀', 'http://example.com/loc6',
        '2025-07-01 14:00:00', '2025-10-31 22:00:00', '2025-07-01 14:00:00', '2025-10-24 22:00:00',
        '취소는 7일 전까지 가능', 7, null, null),

       ('과학 실험실', '운영중', '현장결제', '디스커버리 랩', 'http://example.com/loc7',
        '2025-08-01 09:30:00', '2025-11-30 17:30:00', '2025-08-01 09:30:00', '2025-11-20 17:30:00',
        '취소는 10일 전까지 가능', 10, null, null),

       ('회의실', '비활성', '신용카드', '비즈니스 스위트 101', 'http://example.com/loc8',
        null, null, null, null,
        '해당 없음', null, null, null),

       ('온라인 플랫폼', '운영중', '구독', '가상 교실', 'http://example.com/loc9',
        '2025-01-01 00:00:00', '2025-12-31 23:59:59', '2025-01-01 00:00:00', '2025-12-30 23:59:59',
        '다음 결제 주기 전까지 언제든 취소 가능', 1, null, null),

       ('커뮤니티 정원', '운영중', '기부', '초록 텃밭', 'http://example.com/loc10',
        '2025-03-01 07:00:00', '2025-11-30 18:00:00', null, null,
        '계절별 이용', null, null, null);


--INSERT INTO student (public_id, email, name, social_provider, social_id, school_level, grade,
--                     created_at, updated_at)
--VALUES (UUID_TO_BIN(UUID()), 'student1@example.com', '김민지', 'KAKAO', 'kakao123', 'HIGH', 'FIRST',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student2@example.com', '박서준', 'NAVER', 'naver456', 'MIDDLE', 'SECOND',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student3@example.com', '이하은', 'KAKAO', 'kakao789', 'HIGH', 'THIRD',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student4@example.com', '최우진', 'KAKAO', 'kakao788', 'HIGH', 'SECOND',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student5@example.com', '정다현', 'NAVER', 'naver101', 'MIDDLE', 'FIRST',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student6@example.com', '강지훈', 'KAKAO', 'kakao202', 'HIGH', 'THIRD',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student7@example.com', '윤채원', 'KAKAO', 'kakao205', 'MIDDLE', 'THIRD',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student8@example.com', '한동민', 'NAVER', 'naver303', 'MIDDLE', 'THIRD',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student9@example.com', '배수아', 'KAKAO', 'kakao404', 'HIGH', 'FIRST',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student10@example.com', '송현우', NULL, NULL, 'MIDDLE', 'SECOND',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student11@example.com', '서지훈', 'KAKAO', 'kakao505', 'HIGH', 'SECOND',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student12@example.com', '문채영', 'NAVER', 'naver606', 'MIDDLE', 'FIRST',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student13@example.com', '임태현', 'KAKAO', 'kakao707', 'HIGH', 'FIRST',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student14@example.com', '조예린', 'KAKAO', 'kakao808', 'MIDDLE', 'SECOND',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
--       (UUID_TO_BIN(UUID()), 'student15@example.com', '홍도윤', 'NAVER', 'naver909', 'HIGH', 'THIRD',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Teacher (선생님) 데이터 (10건)
--
--INSERT INTO teacher (public_id,
--                     email,
--                     name,
--                     social_provider,
--                     social_id,
--                     university,
--                     major,
--                     career,
--                     created_at,
--                     updated_at,
--                     bank_name,
--                     account_number,
--                     account_holder)
--VALUES (UUID_TO_BIN(UUID()), 'teacher1@example.com', '김철수 교수', 'KAKAO', 't_kakao1', '한국대학교', '수학과',
--        '10년 강의 경력, 미적분학 전문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '국민은행', '1111111111', '김철수 교수'),
--
--       (UUID_TO_BIN(UUID()), 'teacher2@example.com', '이영희 박사', 'NAVER', 't_naver2', '서울대학교', '물리학과',
--        '5년 연구 및 3년 강의, 양자역학', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '신한은행', '2222222222',
--        '이영희 박사'),
--
--       (UUID_TO_BIN(UUID()), 'teacher3@example.com', '박민준 선생님', 'NAVER', 't_naver3', '연세대학교', '화학과',
--        '고등학교 화학 교사 7년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '하나은행', '3333333333', '박민준 선생님'),
--
--       (UUID_TO_BIN(UUID()), 'teacher4@example.com', '최지우 교사', 'KAKAO', 't_kakao4', '고려대학교', '생명과학과',
--        '중학교 과학 교사 5년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '우리은행', '4444444444', '최지우 교사'),
--
--       (UUID_TO_BIN(UUID()), 'teacher5@example.com', '정다빈 연구원', 'NAVER', 't_naver5', 'KAIST', '수학과',
--        '응용수학 연구 8년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '농협은행', '5555555555', '정다빈 연구원'),
--
--       (UUID_TO_BIN(UUID()), 'teacher6@example.com', '강태현 선생님', 'NAVER', 't_naver6', '성균관대학교', '물리학과',
--        '물리 올림피아드 지도 경험 다수', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '국민은행', '6666666666', '강태현 선생님'),
--
--       (UUID_TO_BIN(UUID()), 'teacher7@example.com', '윤서아 박사', 'KAKAO', 't_kakao7', '포항공과대학교', '화학과',
--        '유기화학 연구 및 특강 전문', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '신한은행', '7777777777', '윤서아 박사'),
--
--       (UUID_TO_BIN(UUID()), 'teacher8@example.com', '한지민 교사', 'NAVER', 't_naver8', '한양대학교', '생명과학과',
--        '과학 탐구반 지도 6년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '하나은행', '8888888888', '한지민 교사'),
--
--       (UUID_TO_BIN(UUID()), 'teacher9@example.com', '배준호 교수', 'NAVER', 't_naver9', '경희대학교', '수학과',
--        '확률 및 통계 강의 12년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '우리은행', '9999999999', '배준호 교수'),
--
--       (UUID_TO_BIN(UUID()), 'teacher10@example.com', '송지효 연구원', 'KAKAO', 't_kakao10', '중앙대학교', '물리학과',
--        '천체물리학 연구 4년', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '농협은행', '1010101010', '송지효 연구원');

INSERT INTO general_location (location_name, x, y, region, service_url)
VALUES ('작심스터디카페 강남역점', '1270292371', '374992896', 'GANGNAM_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B0%95%EB%82%A8%EC%97%AD%EC%A0%90'),
       ('라곰스터디카페 대치점', '1270556315', '375019113', 'GANGNAM_GU',
        'https://map.naver.com/p/search/%EB%9D%BC%EA%B3%B0%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8C%80%EC%B9%98%EC%A0%90'),
       ('작심스터디카페 대치914점', '1270571352', '375017878', 'GANGNAM_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8C%80%EC%B9%98914%EC%A0%90'),
       ('ENI스터디룸', '1270280759', '374994898', 'GANGNAM_GU',
        'https://map.naver.com/p/search/ENI%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8'),
       ('룸룸스터디 도곡역점', '1270555480', '374914520', 'GANGNAM_GU',
        'https://map.naver.com/p/search/%EB%A3%B8%EB%A3%B8%EC%8A%A4%ED%84%B0%EB%94%94%20%EB%8F%84%EA%B3%A1%EC%97%AD%EC%A0%90'),
       ('플랜트스터디카페 강일미사라운지', '1271751791', '375722862', 'GANGDONG_GU',
        'https://map.naver.com/p/search/%ED%94%8C%EB%9E%9C%ED%8A%B8%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B0%95%EC%9D%BC%EB%AF%B8%EC%82%AC%EB%9D%BC%EC%9A%B4%EC%A7%80'),
       ('르하임스터디카페 굽은다리역점', '1271434661', '375461805', 'GANGDONG_GU',
        'https://map.naver.com/p/search/%EB%A5%B4%ED%95%98%EC%9E%84%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B5%BD%EC%9D%80%EB%8B%A4%EB%A6%AC%EC%97%AD%EC%A0%90'),
       ('어라운드스터디카페 강일점', '1271738307', '375656316', 'GANGDONG_GU',
        'https://map.naver.com/p/search/%EC%96%B4%EB%9D%BC%EC%9A%B4%EB%93%9C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B0%95%EC%9D%BC%EC%A0%90'),
       ('초심스터디카페 고덕점', '1271555201', '375536301', 'GANGDONG_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B3%A0%EB%8D%95%EC%A0%90'),
       ('어라운드스터디카페 상일점', '1271689083', '375503527', 'GANGDONG_GU',
        'https://map.naver.com/p/search/%EC%96%B4%EB%9D%BC%EC%9A%B4%EB%93%9C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%83%81%EC%9D%BC%EC%A0%90'),
       ('멘토즈스터디카페 강북번동점', '1270393665', '376289496', 'GANGBUK_GU',
        'https://map.naver.com/p/search/%EB%A9%98%ED%86%A0%EC%A6%88%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B0%95%EB%B6%81%EB%B2%88%EB%8F%99%EC%A0%90'),
       ('온리스터디카페 수유역점', '1270238795', '376419126', 'GANGBUK_GU',
        'https://map.naver.com/p/search/%EC%98%A8%EB%A6%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%88%98%EC%9C%A0%EC%97%AD%EC%A0%90'),
       ('위넌스터디카페 가오리역점', '1270163197', '376423954', 'GANGBUK_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EB%84%8C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B0%80%EC%98%A4%EB%A6%AC%EC%97%AD%EC%A0%90'),
       ('시너지플레이스 스터디카페 미아점', '1270251108', '376280577', 'GANGBUK_GU',
        'https://map.naver.com/p/search/%EC%8B%9C%EB%84%88%EC%A7%80%ED%94%8C%EB%A0%88%EC%9D%B4%EC%8A%A4%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%AF%B8%EC%95%84%EC%A0%90'),
       ('리게인스터디카페 수유점', '1270256203', '376350672', 'GANGBUK_GU',
        'https://map.naver.com/p/search/%EB%A6%AC%EA%B2%8C%EC%9D%B8%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%88%98%EC%9C%A0%EC%A0%90'),
       ('어라운드스터디카페 방화점', '1268178646', '375731978', 'GANGSEO_GU',
        'https://map.naver.com/p/search/%EC%96%B4%EB%9D%BC%EC%9A%B4%EB%93%9C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%B0%A9%ED%99%94%EC%A0%90'),
       ('컨센터블 스터디룸&카페 화곡역점', '1268368391', '375449045', 'GANGSEO_GU',
        'https://map.naver.com/p/search/%EC%BB%A8%EC%84%BC%ED%84%B0%EB%B8%94%20%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%26%EC%B9%B4%ED%8E%98%20%ED%99%94%EA%B3%A1%EC%97%AD%EC%A0%90'),
       ('초심스터디카페 등촌점', '1268449359', '375582135', 'GANGSEO_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%93%B1%EC%B4%8C%EC%A0%90'),
       ('플랜트 스터디카페 내발산라운지', '1268360096', '375525491', 'GANGSEO_GU',
        'https://map.naver.com/p/search/%ED%94%8C%EB%9E%9C%ED%8A%B8%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%82%B4%EB%B0%9C%EC%82%B0%EB%9D%BC%EC%9A%B4%EC%A7%80'),
       ('어라운드스터디카페 화곡점', '1268391877', '375425628', 'GANGSEO_GU',
        'https://map.naver.com/p/search/%EC%96%B4%EB%9D%BC%EC%9A%B4%EB%93%9C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%ED%99%94%EA%B3%A1%EC%A0%90'),
       ('책상숲 스터디카페 낙성대점', '1269670056', '374721951', 'GWANAK_GU',
        'https://map.naver.com/p/search/%EC%B1%85%EC%83%81%EC%88%B2%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%82%99%EC%84%B1%EB%8C%80%EC%A0%90'),
       ('모어댄 스터디카페 낙성대점', '1269651052', '374738602', 'GWANAK_GU',
        'https://map.naver.com/p/search/%EB%AA%A8%EC%96%B4%EB%8C%84%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%82%99%EC%84%B1%EB%8C%80%EC%A0%90'),
       ('스페이스액트 스터디카페', '1269285435', '374826395', 'GWANAK_GU',
        'https://map.naver.com/p/search/%EC%8A%A4%ED%8E%98%EC%9D%B4%EC%8A%A4%EC%95%A1%ED%8A%B8%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98'),
       ('시작스터디카페 서울대입구역직영점', '1269568811', '374800163', 'GWANAK_GU',
        'https://map.naver.com/p/search/%EC%8B%9C%EC%9E%91%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%EC%97%AD%EC%A7%81%EC%98%81%EC%A0%90'),
       ('플랜에이스터디카페 서울대입구역센터', '1269499234', '374818956', 'GWANAK_GU',
        'https://map.naver.com/p/search/%ED%94%8C%EB%9E%9C%EC%97%90%EC%9D%B4%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC%EC%97%AD%EC%84%BC%ED%84%B0'),
       ('초심스터디카페 중곡점', '1270867311', '375631459', 'GWANGJIN_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%A4%91%EA%B3%A1%EC%A0%90'),
       ('초심스터디카페 구의점', '1270938461', '375426904', 'GWANGJIN_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B5%AC%EC%9D%98%EC%A0%90'),
       ('작심스터디카페 화양점', '1270702412', '375475762', 'GWANGJIN_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%ED%99%94%EC%96%91%EC%A0%90'),
       ('작심스터디카페 동서울터미널점', '1270920590', '375359246', 'GWANGJIN_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8F%99%EC%84%9C%EC%9A%B8%ED%84%B0%EB%AF%B8%EB%84%90%EC%A0%90'),
       ('초심스터디카페 군자역점', '1270816957', '375567082', 'GWANGJIN_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B5%B0%EC%9E%90%EC%97%AD%EC%A0%90'),
       ('파티룸파티앤스터디 신도림역점', '1268846468', '375070742', 'GURO_GU',
        'https://map.naver.com/p/search/%ED%8C%8C%ED%8B%B0%EB%A3%B8%ED%8C%8C%ED%8B%B0%EC%95%A4%EC%8A%A4%ED%84%B0%EB%94%94%20%EC%8B%A0%EB%8F%84%EB%A6%BC%EC%97%AD%EC%A0%90'),
       ('골든스터디카페 신도림점', '1268852068', '375095917', 'GURO_GU',
        'https://map.naver.com/p/search/%EA%B3%A8%EB%93%A0%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8B%A0%EB%8F%84%EB%A6%BC%EC%A0%90'),
       ('더작심 스터디카페 구로거리공원점', '1268902616', '374997688', 'GURO_GU',
        'https://map.naver.com/p/search/%EB%8D%94%EC%9E%91%EC%8B%AC%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B5%AC%EB%A1%9C%EA%B1%B0%EB%A6%AC%EA%B3%B5%EC%9B%90%EC%A0%90'),
       ('멘토즈스터디카페 신도림점', '1268833761', '375064034', 'GURO_GU',
        'https://map.naver.com/p/search/%EB%A9%98%ED%86%A0%EC%A6%88%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8B%A0%EB%8F%84%EB%A6%BC%EC%A0%90'),
       ('디딤스터디카페 구로역센터', '1268829182', '375023130', 'GURO_GU',
        'https://map.naver.com/p/search/%EB%94%94%EB%94%A4%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B5%AC%EB%A1%9C%EC%97%AD%EC%84%BC%ED%84%B0'),
       ('그루스터디카페 독산역센터', '1268907228', '374646317', 'GEUMCHEON_GU',
        'https://map.naver.com/p/search/%EA%B7%B8%EB%A3%A8%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8F%85%EC%82%B0%EC%97%AD%EC%84%BC%ED%84%B0'),
       ('초심스터디카페 시흥힐스테이트점', '1269023979', '374471852', 'GEUMCHEON_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8B%9C%ED%9D%A5%ED%9E%90%EC%8A%A4%ED%85%8C%EC%9D%B4%ED%8A%B8%EC%A0%90'),
       ('시작스터디카페 금천구청역점', '1268940287', '374585128', 'GEUMCHEON_GU',
        'https://map.naver.com/p/search/%EC%8B%9C%EC%9E%91%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B8%88%EC%B2%9C%EA%B5%AC%EC%B2%AD%EC%97%AD%EC%A0%90'),
       ('파티앤스터디 가산디지털단지역', '1268794522', '374829855', 'GEUMCHEON_GU',
        'https://map.naver.com/p/search/%ED%8C%8C%ED%8B%B0%EC%95%A4%EC%8A%A4%ED%84%B0%EB%94%94%20%EA%B0%80%EC%82%B0%EB%94%94%EC%A7%80%ED%84%B8%EB%8B%A8%EC%A7%80%EC%97%AD'),
       ('비에이블스터디카페 시흥센터', '1269022415', '374514395', 'GEUMCHEON_GU',
        'https://map.naver.com/p/search/%EB%B9%84%EC%97%90%EC%9D%B4%EB%B8%94%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8B%9C%ED%9D%A5%EC%84%BC%ED%84%B0'),
       ('멘토즈스터디카페 공릉점', '1270792363', '376271246', 'NOWON_GU',
        'https://map.naver.com/p/search/%EB%A9%98%ED%86%A0%EC%A6%88%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B3%B5%EB%A6%89%EC%A0%90'),
       ('노원역스터디룸 스터디카페 더골스터디', '1270638803', '376568041', 'NOWON_GU',
        'https://map.naver.com/p/search/%EB%85%B8%EC%9B%90%EC%97%AD%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8D%94%EA%B3%A8%EC%8A%A4%ED%84%B0%EB%94%94'),
       ('위넌스터디카페 중계점', '1270778366', '376510845', 'NOWON_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EB%84%8C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%A4%91%EA%B3%84%EC%A0%90'),
       ('노원 공릉 파티룸 스터디룸 룸178', '1270750780', '376210286', 'NOWON_GU',
        'https://map.naver.com/p/search/%EB%85%B8%EC%9B%90%20%EA%B3%B5%EB%A6%89%20%ED%8C%8C%ED%8B%B0%EB%A3%B8%20%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%EB%A3%B8178'),
       ('청담스터디카페 노원역점', '1270632061', '376541856', 'NOWON_GU',
        'https://map.naver.com/p/search/%EC%B2%AD%EB%8B%B4%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%85%B8%EC%9B%90%EC%97%AD%EC%A0%90'),
       ('작심스터디카페 창동점', '1270417558', '376554413', 'DOBONG_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%B0%BD%EB%8F%99%EC%A0%90'),
       ('르하임스터디카페 쌍문점', '1270347950', '376587474', 'DOBONG_GU',
        'https://map.naver.com/p/search/%EB%A5%B4%ED%95%98%EC%9E%84%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8C%8D%EB%AC%B8%EC%A0%90'),
       ('작심스터디카페 쌍문노해로점', '1270306448', '376510896', 'DOBONG_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8C%8D%EB%AC%B8%EB%85%B8%ED%95%B4%EB%A1%9C%EC%A0%90'),
       ('위스터디카페', '1270455222', '376517065', 'DOBONG_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98'),
       ('시작스터디카페 창동점', '1270462088', '376518117', 'DOBONG_GU',
        'https://map.naver.com/p/search/%EC%8B%9C%EC%9E%91%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%B0%BD%EB%8F%99%EC%A0%90'),
       ('초심스터디카페 경희대점', '1270531225', '375910962', 'DONGDAEMUN_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B2%BD%ED%9D%AC%EB%8C%80%EC%A0%90'),
       ('멘토즈스터디카페 경희대점', '1270532415', '375913917', 'DONGDAEMUN_GU',
        'https://map.naver.com/p/search/%EB%A9%98%ED%86%A0%EC%A6%88%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B2%BD%ED%9D%AC%EB%8C%80%EC%A0%90'),
       ('공본스터디카페 전농센터', '1270481290', '375762936', 'DONGDAEMUN_GU',
        'https://map.naver.com/p/search/%EA%B3%B5%EB%B3%B8%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%A0%84%EB%86%8D%EC%84%BC%ED%84%B0'),
       ('초심스터디카페 장안사거리점', '1270721691', '375728575', 'DONGDAEMUN_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9E%A5%EC%95%88%EC%82%AC%EA%B1%B0%EB%A6%AC%EC%A0%90'),
       ('어라운드스터디카페 답십리점', '1270619337', '375673660', 'DONGDAEMUN_GU',
        'https://map.naver.com/p/search/%EC%96%B4%EB%9D%BC%EC%9A%B4%EB%93%9C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8B%B5%EC%8B%AD%EB%A6%AC%EC%A0%90'),
       ('컨센터블 스터디룸&카페 수림당', '1269471689', '375124248', 'DONGJAK_GU',
        'https://map.naver.com/p/search/%EC%BB%A8%EC%84%BC%ED%84%B0%EB%B8%94%20%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%26%EC%B9%B4%ED%8E%98%20%EC%88%98%EB%A6%BC%EB%8B%B9'),
       ('마이스터디룸 &카페 노량진점', '1269450875', '375125880', 'DONGJAK_GU',
        'https://map.naver.com/p/search/%EB%A7%88%EC%9D%B4%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%26%EC%B9%B4%ED%8E%98%20%EB%85%B8%EB%9F%89%EC%A7%84%EC%A0%90'),
       ('작심스터디카페 숭실대점', '1269541698', '374947197', 'DONGJAK_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%88%AD%EC%8B%A4%EB%8C%80%EC%A0%90'),
       ('컨센터블 스터디룸&카페 혜림재', '1269468231', '375113624', 'DONGJAK_GU',
        'https://map.naver.com/p/search/%EC%BB%A8%EC%84%BC%ED%84%B0%EB%B8%94%20%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%26%EC%B9%B4%ED%8E%98%20%ED%98%9C%EB%A6%BC%EC%9E%AC'),
       ('셀스스터디룸 사당점', '1269815529', '374810627', 'DONGJAK_GU',
        'https://map.naver.com/p/search/%EC%85%80%EC%8A%A4%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%EC%82%AC%EB%8B%B9%EC%A0%90'),
       ('작심스터디카페 신촌숲길점', '1269339652', '375539694', 'MAPO_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8B%A0%EC%B4%8C%EC%88%B2%EA%B8%B8%EC%A0%90'),
       ('두드림 스터디룸', '1269376538', '375530485', 'MAPO_GU',
        'https://map.naver.com/p/search/%EB%91%90%EB%93%9C%EB%A6%BC%20%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8'),
       ('셀스온에어스터디룸 홍대1번출구점', '1269220285', '375564429', 'MAPO_GU',
        'https://map.naver.com/p/search/%EC%85%80%EC%8A%A4%EC%98%A8%EC%97%90%EC%96%B4%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%ED%99%8D%EB%8C%801%EB%B2%88%EC%B6%9C%EA%B5%AC%EC%A0%90'),
       ('신촌 채움 모임공간 스터디룸 회의실', '1269420297', '375562975', 'MAPO_GU',
        'https://map.naver.com/p/search/%EC%8B%A0%EC%B4%8C%20%EC%B1%84%EC%9B%80%20%EB%AA%A8%EC%9E%84%EA%B3%B5%EA%B0%84%20%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%ED%9A%8C%EC%9D%98%EC%8B%A4'),
       ('공본 스터디카페 서강대센터', '1269370953', '375496567', 'MAPO_GU',
        'https://map.naver.com/p/search/%EA%B3%B5%EB%B3%B8%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%84%9C%EA%B0%95%EB%8C%80%EC%84%BC%ED%84%B0'),
       ('플랜에이 이화스터디카페', '1269443043', '375590132', 'SEODAEMUN_GU',
        'https://map.naver.com/p/search/%ED%94%8C%EB%9E%9C%EC%97%90%EC%9D%B4%20%EC%9D%B4%ED%99%94%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98'),
       ('윙스터디 신촌스터디룸', '1269347643', '375566196', 'SEODAEMUN_GU',
        'https://map.naver.com/p/search/%EC%9C%99%EC%8A%A4%ED%84%B0%EB%94%94%20%EC%8B%A0%EC%B4%8C%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8'),
       ('플랜에이 이화스터디카페 2센터', '1269443101', '375590570', 'SEODAEMUN_GU',
        'https://map.naver.com/p/search/%ED%94%8C%EB%9E%9C%EC%97%90%EC%9D%B4%20%EC%9D%B4%ED%99%94%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%202%EC%84%BC%ED%84%B0'),
       ('르하임스터디카페 연희점', '1269298000', '375658152', 'SEODAEMUN_GU',
        'https://map.naver.com/p/search/%EB%A5%B4%ED%95%98%EC%9E%84%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%97%B0%ED%9D%AC%EC%A0%90'),
       ('플랜에이 이화스터디룸&카페', '1269443101', '375590570', 'SEODAEMUN_GU',
        'https://map.naver.com/p/search/%ED%94%8C%EB%9E%9C%EC%97%90%EC%9D%B4%20%EC%9D%B4%ED%99%94%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%26%EC%B9%B4%ED%8E%98'),
       ('강남역 스터디룸 스터디카페 윙스터디', '1270252957', '374981366', 'SEOCHO_GU',
        'https://map.naver.com/p/search/%EA%B0%95%EB%82%A8%EC%97%AD%20%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9C%99%EC%8A%A4%ED%84%B0%EB%94%94'),
       ('더작심스터디카페 교대역점', '1270147172', '374937277', 'SEOCHO_GU',
        'https://map.naver.com/p/search/%EB%8D%94%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B5%90%EB%8C%80%EC%97%AD%EC%A0%90'),
       ('셀스스터디룸 사당3번출구점', '1269820789', '374751479', 'SEOCHO_GU',
        'https://map.naver.com/p/search/%EC%85%80%EC%8A%A4%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%EC%82%AC%EB%8B%B93%EB%B2%88%EC%B6%9C%EA%B5%AC%EC%A0%90'),
       ('디딤스터디카페 서초센터', '1270097425', '374851899', 'SEOCHO_GU',
        'https://map.naver.com/p/search/%EB%94%94%EB%94%A4%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%84%9C%EC%B4%88%EC%84%BC%ED%84%B0'),
       ('비온탑스터디카페 신반포점', '1270011225', '375059326', 'SEOCHO_GU',
        'https://map.naver.com/p/search/%EB%B9%84%EC%98%A8%ED%83%91%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8B%A0%EB%B0%98%ED%8F%AC%EC%A0%90'),
       ('위넌스터디카페 뚝섬역점', '1270492652', '375442422', 'SEONGDONG_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EB%84%8C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%9A%9D%EC%84%AC%EC%97%AD%EC%A0%90'),
       ('초심스터디카페 한양대점', '1270414383', '375617256', 'SEONGDONG_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%ED%95%9C%EC%96%91%EB%8C%80%EC%A0%90'),
       ('모어댄 스터디카페 금호점', '1270185008', '375484427', 'SEONGDONG_GU',
        'https://map.naver.com/p/search/%EB%AA%A8%EC%96%B4%EB%8C%84%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B8%88%ED%98%B8%EC%A0%90'),
       ('위넌스터디카페 한양대점', '1270396389', '375584601', 'SEONGDONG_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EB%84%8C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%ED%95%9C%EC%96%91%EB%8C%80%EC%A0%90'),
       ('위넌스터디카페 성수점', '1270480990', '375494326', 'SEONGDONG_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EB%84%8C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%84%B1%EC%88%98%EC%A0%90'),
       ('튜터케이랑 성신여대점', '1270150772', '375921667', 'SEONGBUK_GU',
        'https://map.naver.com/p/search/%ED%8A%9C%ED%84%B0%EC%BC%80%EC%9D%B4%EB%9E%91%20%EC%84%B1%EC%8B%A0%EC%97%AC%EB%8C%80%EC%A0%90'),
       ('초심스터디카페 길음점', '1270276785', '376067431', 'SEONGBUK_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B8%B8%EC%9D%8C%EC%A0%90'),
       ('더스터디 레이디스', '1270291686', '375866342', 'SEONGBUK_GU',
        'https://map.naver.com/p/search/%EB%8D%94%EC%8A%A4%ED%84%B0%EB%94%94%20%EB%A0%88%EC%9D%B4%EB%94%94%EC%8A%A4'),
       ('초심스터디카페 고려대점', '1270293311', '375851980', 'SEONGBUK_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EA%B3%A0%EB%A0%A4%EB%8C%80%EC%A0%90'),
       ('더스터디카페 안암고려대점', '1270291753', '375866670', 'SEONGBUK_GU',
        'https://map.naver.com/p/search/%EB%8D%94%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%95%88%EC%95%94%EA%B3%A0%EB%A0%A4%EB%8C%80%EC%A0%90'),
       ('멘토즈스터디카페 잠실푸르지오점', '1271036085', '375175180', 'SONGPA_GU',
        'https://map.naver.com/p/search/%EB%A9%98%ED%86%A0%EC%A6%88%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9E%A0%EC%8B%A4%ED%91%B8%EB%A5%B4%EC%A7%80%EC%98%A4%EC%A0%90'),
       ('초심스터디카페 방이점', '1271252703', '375091405', 'SONGPA_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%B0%A9%EC%9D%B4%EC%A0%90'),
       ('얼리버드 스터디카페 잠실점', '1270874326', '375082575', 'SONGPA_GU',
        'https://map.naver.com/p/search/%EC%96%BC%EB%A6%AC%EB%B2%84%EB%93%9C%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9E%A0%EC%8B%A4%EC%A0%90'),
       ('스터디하다 스터디카페 송파점', '1271136629', '375033770', 'SONGPA_GU',
        'https://map.naver.com/p/search/%EC%8A%A4%ED%84%B0%EB%94%94%ED%95%98%EB%8B%A4%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%86%A1%ED%8C%8C%EC%A0%90'),
       ('초심스터디카페 잠실새내역점', '1270834983', '375113865', 'SONGPA_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9E%A0%EC%8B%A4%EC%83%88%EB%82%B4%EC%97%AD%EC%A0%90'),
       ('멘토즈스터디카페 목동역점', '1268631112', '375280078', 'YANGCHEON_GU',
        'https://map.naver.com/p/search/%EB%A9%98%ED%86%A0%EC%A6%88%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%AA%A9%EB%8F%99%EC%97%AD%EC%A0%90'),
       ('작심스터디카페 목5동3호점', '1268783756', '375363298', 'YANGCHEON_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%AA%A95%EB%8F%993%ED%98%B8%EC%A0%90'),
       ('공본스터디카페 목동센터', '1268812041', '375356003', 'YANGCHEON_GU',
        'https://map.naver.com/p/search/%EA%B3%B5%EB%B3%B8%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%AA%A9%EB%8F%99%EC%84%BC%ED%84%B0'),
       ('작심스터디카페 오목교역점', '1268731083', '375252685', 'YANGCHEON_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%98%A4%EB%AA%A9%EA%B5%90%EC%97%AD%EC%A0%90'),
       ('파피스터디카페 목동센터', '1268717083', '375164537', 'YANGCHEON_GU',
        'https://map.naver.com/p/search/%ED%8C%8C%ED%94%BC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%AA%A9%EB%8F%99%EC%84%BC%ED%84%B0'),
       ('스터디룸 가치', '1268902307', '375217487', 'YEONGDEUNGPO_GU',
        'https://map.naver.com/p/search/%EC%8A%A4%ED%84%B0%EB%94%94%EB%A3%B8%20%EA%B0%80%EC%B9%98'),
       ('르하임스터디카페 여의도점', '1269307891', '375189066', 'YEONGDEUNGPO_GU',
        'https://map.naver.com/p/search/%EB%A5%B4%ED%95%98%EC%9E%84%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%97%AC%EC%9D%98%EB%8F%84%EC%A0%90'),
       ('작심스터디카페 서울문래점', '1268910116', '375197789', 'YEONGDEUNGPO_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%84%9C%EC%9A%B8%EB%AC%B8%EB%9E%98%EC%A0%90'),
       ('플랜에이스터디카페 당산센터', '1269036816', '375327460', 'YEONGDEUNGPO_GU',
        'https://map.naver.com/p/search/%ED%94%8C%EB%9E%9C%EC%97%90%EC%9D%B4%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8B%B9%EC%82%B0%EC%84%BC%ED%84%B0'),
       ('멘토즈스터디카페 당산푸르지오점', '1269061268', '375283070', 'YEONGDEUNGPO_GU',
        'https://map.naver.com/p/search/%EB%A9%98%ED%86%A0%EC%A6%88%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8B%B9%EC%82%B0%ED%91%B8%EB%A5%B4%EC%A7%80%EC%98%A4%EC%A0%90'),
       ('작심스터디카페 숙대눈송이점', '1269700528', '375447573', 'YONGSAN_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%88%99%EB%8C%80%EB%88%88%EC%86%A1%EC%9D%B4%EC%A0%90'),
       ('작심스터디카페 더작심 한남순천향점', '1270055111', '375336275', 'YONGSAN_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8D%94%EC%9E%91%EC%8B%AC%20%ED%95%9C%EB%82%A8%EC%88%9C%EC%B2%9C%ED%96%A5%EC%A0%90'),
       ('숙대 블라썸 스터디카페', '1269667866', '375450384', 'YONGSAN_GU',
        'https://map.naver.com/p/search/%EC%88%99%EB%8C%80%20%EB%B8%94%EB%9D%BC%EC%8D%B8%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98'),
       ('리얼스터디카페 이촌1호점', '1269780062', '375183989', 'YONGSAN_GU',
        'https://map.naver.com/p/search/%EB%A6%AC%EC%96%BC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9D%B4%EC%B4%8C1%ED%98%B8%EC%A0%90'),
       ('미라클스터디카페 원효센터', '1269672894', '375379929', 'YONGSAN_GU',
        'https://map.naver.com/p/search/%EB%AF%B8%EB%9D%BC%ED%81%B4%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9B%90%ED%9A%A8%EC%84%BC%ED%84%B0'),
       ('다짐스터디카페 응암점', '1269191117', '375995356', 'EUNPYEONG_GU',
        'https://map.naver.com/p/search/%EB%8B%A4%EC%A7%90%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9D%91%EC%95%94%EC%A0%90'),
       ('노블 24시 스터디카페', '1269202439', '376241245', 'EUNPYEONG_GU',
        'https://map.naver.com/p/search/%EB%85%B8%EB%B8%94%2024%EC%8B%9C%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98'),
       ('에피소드스터디카페', '1269184727', '376163934', 'EUNPYEONG_GU',
        'https://map.naver.com/p/search/%EC%97%90%ED%94%BC%EC%86%8C%EB%93%9C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98'),
       ('공본스터디카페 응암센터', '1269217772', '376000545', 'EUNPYEONG_GU',
        'https://map.naver.com/p/search/%EA%B3%B5%EB%B3%B8%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%9D%91%EC%95%94%EC%84%BC%ED%84%B0'),
       ('씨앤씨 스터디카페', '1269197781', '376205832', 'EUNPYEONG_GU',
        'https://map.naver.com/p/search/%EC%94%A8%EC%95%A4%EC%94%A8%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98'),
       ('nuguna', '1269842915', '375697105', 'JONGNO_GU', 'https://map.naver.com/p/search/nuguna'),
       ('종로스터디카페 피앤티스퀘어', '1269891559', '375706064', 'JONGNO_GU',
        'https://map.naver.com/p/search/%EC%A2%85%EB%A1%9C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%ED%94%BC%EC%95%A4%ED%8B%B0%EC%8A%A4%ED%80%98%EC%96%B4'),
       ('작심스터디카페 서울혜화점', '1269996380', '375841873', 'JONGNO_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%84%9C%EC%9A%B8%ED%98%9C%ED%99%94%EC%A0%90'),
       ('작심스터디카페 대학로점', '1270028255', '375816161', 'JONGNO_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%8C%80%ED%95%99%EB%A1%9C%EC%A0%90'),
       ('프레쉬스터디카페 종로본점', '1269873981', '375694896', 'JONGNO_GU',
        'https://map.naver.com/p/search/%ED%94%84%EB%A0%88%EC%89%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%A2%85%EB%A1%9C%EB%B3%B8%EC%A0%90'),
       ('작심스터디카페 충무로역점', '1269922436', '375608373', 'JUNG_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%B6%A9%EB%AC%B4%EB%A1%9C%EC%97%AD%EC%A0%90'),
       ('어라운드스터디카페 왕십리점', '1270233671', '375656712', 'JUNG_GU',
        'https://map.naver.com/p/search/%EC%96%B4%EB%9D%BC%EC%9A%B4%EB%93%9C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%99%95%EC%8B%AD%EB%A6%AC%EC%A0%90'),
       ('위넌스터디카페 약수역점', '1270124211', '375570392', 'JUNG_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EB%84%8C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%95%BD%EC%88%98%EC%97%AD%EC%A0%90'),
       ('위넌스터디카페 상왕십리점', '1270232603', '375681907', 'JUNG_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EB%84%8C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%83%81%EC%99%95%EC%8B%AD%EB%A6%AC%EC%A0%90'),
       ('작심라운지 시청역점', '1269761399', '375634816', 'JUNG_GU',
        'https://map.naver.com/p/search/%EC%9E%91%EC%8B%AC%EB%9D%BC%EC%9A%B4%EC%A7%80%20%EC%8B%9C%EC%B2%AD%EC%97%AD%EC%A0%90'),
       ('플랜트 스터디카페 사가정라운지', '1270935871', '375810611', 'JUNGRANG_GU',
        'https://map.naver.com/p/search/%ED%94%8C%EB%9E%9C%ED%8A%B8%20%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%82%AC%EA%B0%80%EC%A0%95%EB%9D%BC%EC%9A%B4%EC%A7%80'),
       ('파티룸 파티앤스터디 망우별채', '1270909141', '376002747', 'JUNGRANG_GU',
        'https://map.naver.com/p/search/%ED%8C%8C%ED%8B%B0%EB%A3%B8%20%ED%8C%8C%ED%8B%B0%EC%95%A4%EC%8A%A4%ED%84%B0%EB%94%94%20%EB%A7%9D%EC%9A%B0%EB%B3%84%EC%B1%84'),
       ('초심스터디카페 신내동점', '1270958423', '376056959', 'JUNGRANG_GU',
        'https://map.naver.com/p/search/%EC%B4%88%EC%8B%AC%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8B%A0%EB%82%B4%EB%8F%99%EC%A0%90'),
       ('노벨스터디카페 신내점', '1270957624', '376047672', 'JUNGRANG_GU',
        'https://map.naver.com/p/search/%EB%85%B8%EB%B2%A8%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EC%8B%A0%EB%82%B4%EC%A0%90'),
       ('위넌스터디카페 먹골역점', '1270769637', '376099686', 'JUNGRANG_GU',
        'https://map.naver.com/p/search/%EC%9C%84%EB%84%8C%EC%8A%A4%ED%84%B0%EB%94%94%EC%B9%B4%ED%8E%98%20%EB%A8%B9%EA%B3%A8%EC%97%AD%EC%A0%90');

--
--INSERT INTO lesson (general_location_id, teacher_id, title, description, amount, min_student, max_student,
--                    start_date, end_date, deadline, status, school_level, subject, created_at,
--                    updated_at, region, type)
--VALUES (1, 1, '고등수학 미적분 심화 과정', '미적분의 개념부터 심화 문제까지 단계적으로 학습하는 고등학생 대상의 집중 수학 과정입니다.', 120000, 1, 4,
--        DATE '2025-09-01', DATE '2025-12-15', DATE '2025-08-25', 'ACTIVE', 'HIGH', 'MATH',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'EUNPYEONG_GU', 'OFFLINE'),
--
--       (2, 2, '고등물리 역학 마스터 클래스', '운동의 법칙과 에너지 보존을 중심으로 역학 전반을 깊이 있게 다루는 고등 물리 심화 수업입니다.', 110000, 2,
--        5,
--        DATE '2025-09-08', DATE '2025-11-28', DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JONGNO_GU', 'OFFLINE'),
--
--       (3, 3, '중학화학 기초 다지기', '중학생을 위한 화학 기초 개념 수업으로 원소, 분자, 화학반응 등 과학의 기초를 쉽게 학습합니다.', 90000, 1, 4,
--        DATE '2025-05-10', DATE '2025-07-10', DATE '2025-05-01', 'ACTIVE', 'MIDDLE', 'SCIENCE',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JONGNO_GU', 'ONLINE'),
--
--       (4, 4, '중학 생명과학 탐구 실험', '생물의 기본 구조와 기능을 중심으로 다양한 실험 활동을 통해 탐구 능력을 기르는 수업입니다.', 95000, 6, 18,
--        DATE '2025-06-01', DATE '2025-08-31', DATE '2025-05-20', 'CANCELED', 'MIDDLE', 'SCIENCE',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JONGNO_GU', 'OFFLINE'),
--
--       (5, 5, '코딩으로 배우는 수학 원리', '파이썬 코딩을 활용해 수학 원리를 시각화하고 직접 구현하며 수학적 사고력을 기르는 융합 수업입니다.', 150000,
--        5, 10,
--        DATE '2025-10-01', DATE '2026-01-31', DATE '2025-09-15', 'ACTIVE', 'HIGH', 'MATH',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JONGNO_GU', 'ONLINE'),
--
--       (6, 6, '물리 올림피아드 대비반', '물리 경시대회 및 올림피아드 참가자를 위한 이론 정리와 문제풀이 중심의 고급 집중 과정입니다.', 130000, 7, 15,
--        DATE '2025-09-05', DATE '2025-12-20', DATE '2025-08-30', 'ACTIVE', 'HIGH', 'SCIENCE',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SONGPA_GU', 'OFFLINE'),
--
--       (7, 7, '유기화학 입문 특강', '유기화학의 기본 개념과 작용기, 반응 메커니즘을 쉽고 명확하게 소개하는 입문자 대상의 특강입니다.', 100000, 10,
--        20,
--        DATE '2025-09-02', DATE '2025-12-16', DATE '2025-08-26', 'ACTIVE', 'HIGH', 'SCIENCE',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SEOCHO_GU', 'OFFLINE'),
--
--       (8, 8, '중학 과학 내신 완성반', '중학교 과학 내신 대비를 위한 개념 정리와 핵심 문제 풀이로 성적 향상을 목표로 한 강의입니다.', 85000, 12,
--        30,
--        DATE '2025-07-07', DATE '2025-09-07', DATE '2025-07-01', 'CANCELED', 'MIDDLE', 'SCIENCE',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU', 'OFFLINE'),
--
--       (9, 9, '확률과 통계 개념 및 문제풀이', '확률과 통계 전반의 개념을 다지고 수능 및 내신을 대비한 실전 문제풀이 중심으로 구성된 강의입니다.', 115000,
--        8, 18,
--        DATE '2025-11-01', DATE '2026-02-28', DATE '2025-10-20', 'ACTIVE', 'HIGH', 'MATH',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU', 'ONLINE'),
--
--       (3, 8, '천체물리학 맛보기 강좌', '별, 행성, 우주에 대한 흥미로운 주제를 중심으로 천체물리학의 기초 개념을 배워보는 체험형 수업입니다.', 140000,
--        5, 12,
--        DATE '2025-09-15', DATE '2025-12-18', DATE '2025-09-01', 'ACTIVE', 'HIGH', 'SCIENCE',
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU', 'ONLINE');
--
--
---- Study (스터디) 데이터 (10건) - status 'INACTIVE' -> 'CLOSED' 수정됨
---- Location ID 1-10 사용 가정
--INSERT INTO study (title, description, subject, school_level, status, student_id, location_id, general_location_id,
--                   created_at,
--                   updated_at,
--                   region,
--                   study_type)
--VALUES ('고등수학 스터디 - 미적분 문제풀이', '매주 모여 미적분 심화 문제를 함께 풀이합니다.', 'MATH', 'HIGH', 'ACTIVE', 1, 2, null,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU', 'OFFLINE'),
--       ('물리 스터디 - 역학 집중 탐구', '뉴턴 역학 중심으로 개념 이해 및 문제 해결 능력 향상.', 'SCIENCE', 'HIGH', 'ACTIVE', 2, null, 1,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU', 'OFFLINE'),
--       ('중학수학 내신 대비 스터디', '학교 시험 범위 맞춰 개념 정리 및 문제 풀이 진행.', 'MATH', 'MIDDLE', 'CLOSED', 3, null, 3,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GANGBUK_GU', 'OFFLINE'),
--       ('화학 반응식 완전 정복', '다양한 화학 반응식을 이해하고 암기하는 스터디.', 'SCIENCE', 'HIGH', 'ACTIVE', 7, 1, null,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU', 'OFFLINE'),
--       ('생명과학 토론 스터디', '최신 생명과학 이슈에 대해 토론하고 발표합니다.', 'SCIENCE', 'HIGH', 'ACTIVE', 9, 6, null,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU', 'OFFLINE'),
--       ('확률과 통계 개념 완성', '확률과 통계 기본 개념부터 응용까지 함께 공부합니다.', 'MATH', 'HIGH', 'ACTIVE', 5, 8, null,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SEOCHO_GU', 'OFFLINE'),
--       ('과학 실험 보고서 작성법', '실험 설계부터 결과 분석, 보고서 작성까지 연습합니다.', 'SCIENCE', 'MIDDLE', 'ACTIVE', 7, null, null,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAPO_GU', 'ONLINE'),
--       ('수학 경시대회 준비 모임', '고난도 수학 문제 풀이 및 전략 공유.', 'MATH', 'HIGH', 'CLOSED', 1, null, null,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU', 'ONLINE'),
--       ('중학 과학 개념 노트 정리', '과학 교과서 핵심 내용을 함께 정리하고 복습합니다.', 'SCIENCE', 'MIDDLE', 'ACTIVE', 3, null, null,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GEUMCHEON_GU', 'ONLINE'),
--       ('물리 심화 문제 도전', '물리 올림피아드 수준의 문제 풀이에 도전합니다.', 'SCIENCE', 'HIGH', 'ACTIVE', 2, null, null,
--        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GANGBUK_GU', 'ONLINE');
--
--INSERT INTO enrollment (created_at, updated_at, student_id, lesson_id)
--VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 2),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 4),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 3),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 5),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 4),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 1),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 3),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 2),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 4),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 3),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 5),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9, 1),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9, 2),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 4),
--       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 5);

-- Autogenerated: do not edit this file
-- Autogenerated: do not edit this file
DROP TABLE IF EXISTS batch.BATCH_STEP_EXECUTION_CONTEXT;
DROP TABLE IF EXISTS batch.BATCH_JOB_EXECUTION_CONTEXT;
DROP TABLE IF EXISTS batch.BATCH_STEP_EXECUTION;
DROP TABLE IF EXISTS batch.BATCH_JOB_EXECUTION_PARAMS;
DROP TABLE IF EXISTS batch.BATCH_JOB_EXECUTION;
DROP TABLE IF EXISTS batch.BATCH_JOB_INSTANCE;

DROP TABLE IF EXISTS batch.BATCH_STEP_EXECUTION_SEQ;
DROP TABLE IF EXISTS batch.BATCH_JOB_EXECUTION_SEQ;
DROP TABLE IF EXISTS batch.BATCH_JOB_SEQ;

CREATE DATABASE IF NOT EXISTS batch;

CREATE TABLE batch.BATCH_JOB_INSTANCE
(
    JOB_INSTANCE_ID BIGINT       NOT NULL PRIMARY KEY,
    VERSION         BIGINT,
    JOB_NAME        VARCHAR(100) NOT NULL,
    JOB_KEY         VARCHAR(32)  NOT NULL,
    constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE = InnoDB;

CREATE TABLE batch.BATCH_JOB_EXECUTION
(
    JOB_EXECUTION_ID BIGINT      NOT NULL PRIMARY KEY,
    VERSION          BIGINT,
    JOB_INSTANCE_ID  BIGINT      NOT NULL,
    CREATE_TIME      DATETIME(6) NOT NULL,
    START_TIME       DATETIME(6) DEFAULT NULL,
    END_TIME         DATETIME(6) DEFAULT NULL,
    STATUS           VARCHAR(10),
    EXIT_CODE        VARCHAR(2500),
    EXIT_MESSAGE     VARCHAR(2500),
    LAST_UPDATED     DATETIME(6),
    constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
        references batch.BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
) ENGINE = InnoDB;

CREATE TABLE batch.BATCH_JOB_EXECUTION_PARAMS
(
    JOB_EXECUTION_ID BIGINT       NOT NULL,
    PARAMETER_NAME   VARCHAR(100) NOT NULL,
    PARAMETER_TYPE   VARCHAR(100) NOT NULL,
    PARAMETER_VALUE  VARCHAR(2500),
    IDENTIFYING      CHAR(1)      NOT NULL,
    constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
        references batch.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
) ENGINE = InnoDB;

CREATE TABLE batch.BATCH_STEP_EXECUTION
(
    STEP_EXECUTION_ID  BIGINT       NOT NULL PRIMARY KEY,
    VERSION            BIGINT       NOT NULL,
    STEP_NAME          VARCHAR(100) NOT NULL,
    JOB_EXECUTION_ID   BIGINT       NOT NULL,
    CREATE_TIME        DATETIME(6)  NOT NULL,
    START_TIME         DATETIME(6) DEFAULT NULL,
    END_TIME           DATETIME(6) DEFAULT NULL,
    STATUS             VARCHAR(10),
    COMMIT_COUNT       BIGINT,
    READ_COUNT         BIGINT,
    FILTER_COUNT       BIGINT,
    WRITE_COUNT        BIGINT,
    READ_SKIP_COUNT    BIGINT,
    WRITE_SKIP_COUNT   BIGINT,
    PROCESS_SKIP_COUNT BIGINT,
    ROLLBACK_COUNT     BIGINT,
    EXIT_CODE          VARCHAR(2500),
    EXIT_MESSAGE       VARCHAR(2500),
    LAST_UPDATED       DATETIME(6),
    constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
        references batch.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
) ENGINE = InnoDB;

CREATE TABLE batch.BATCH_STEP_EXECUTION_CONTEXT
(
    STEP_EXECUTION_ID  BIGINT        NOT NULL PRIMARY KEY,
    SHORT_CONTEXT      VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT,
    constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
        references batch.BATCH_STEP_EXECUTION (STEP_EXECUTION_ID)
) ENGINE = InnoDB;

CREATE TABLE batch.BATCH_JOB_EXECUTION_CONTEXT
(
    JOB_EXECUTION_ID   BIGINT        NOT NULL PRIMARY KEY,
    SHORT_CONTEXT      VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT,
    constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
        references batch.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
) ENGINE = InnoDB;

CREATE TABLE batch.BATCH_STEP_EXECUTION_SEQ
(
    ID         BIGINT  NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE = InnoDB;

INSERT INTO batch.BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY)
select *
from (select 0 as ID, '0' as UNIQUE_KEY) as tmp
where not exists(select * from batch.BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE batch.BATCH_JOB_EXECUTION_SEQ
(
    ID         BIGINT  NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE = InnoDB;

INSERT INTO batch.BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY)
select *
from (select 0 as ID, '0' as UNIQUE_KEY) as tmp
where not exists(select * from batch.BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE batch.BATCH_JOB_SEQ
(
    ID         BIGINT  NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE = InnoDB;

INSERT INTO batch.BATCH_JOB_SEQ (ID, UNIQUE_KEY)
select *
from (select 0 as ID, '0' as UNIQUE_KEY) as tmp
where not exists(select * from batch.BATCH_JOB_SEQ);

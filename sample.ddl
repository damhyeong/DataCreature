DROP TABLE IF EXISTS user, Example, Example_Source;

-- 유저 테이블 생성
create TABLE user(
                     id INT NOT NULL AUTO_INCREMENT UNIQUE COMMENT '사용자 ID 인덱스',
                     email VARCHAR(50) NOT NULL UNIQUE COMMENT '사용자 이메일',
                     password VARCHAR(100) NOT NULL COMMENT '사용자 비밀번호',
                     nickname VARCHAR(20) NOT NULL UNIQUE COMMENT '사용자 별명',
                     tel_number VARCHAR(20) NOT NULL COMMENT '사용자 전화번호',
                     address TEXT NOT NULL COMMENT '사용자 주소',
                     address_detail TEXT NULL COMMENT '사용자 상세 주소',
                     profile_image TEXT NULL COMMENT '사용자 프로필 주소',
                     PRIMARY KEY (email)
) COMMENT '유저 테이블';

INSERT INTO user (email, password, nickname, tel_number, address, address_detail, profile_image) VALUES (
    'rhdwhdals8@naver.com',
    'pass1234', '담순쨩',
    '010-2570-9448',
    '경기도 화성시 탄요1길 25-8',
    '301호',
    null
);
INSERT INTO user (email, password, nickname, tel_number, address, address_detail, profile_image) VALUES (
    'user1@gmail.com',
    'pass1234', 'user1',
    '010-1234-5678',
    '경기도 화성시 탄요1길 25-8',
    '301호',
    null
);

-- 유저 정보 검색
-- SELECT * FROM user;

-- 문제 테이블 생성
create TABLE Example(
                        example_number INT NOT NULL AUTO_INCREMENT UNIQUE COMMENT '문제 번호',
                        title TEXT NOT NULL COMMENT '문제 제목',
                        level INT NOT NULL COMMENT '문제 레벨',
                        favorite_count INT NOT NULL COMMENT '좋아요 수',
                        view_count INT NOT NULL COMMENT '조회 수',
                        write_datetime DATETIME NOT NULL COMMENT '작성 날짜 및 시간',
                        write_nickname VARCHAR(50) NOT NULL COMMENT '작성자 닉네임',
                        PRIMARY KEY (example_number)
) COMMENT '문제 대표 정보';

INSERT INTO Example (title, level, favorite_count, view_count, write_datetime, write_nickname) VALUES
    ('10까지 출력하기', 0, 2, 4, '2023-11-01 00:00:00', '담순쨩');
INSERT INTO Example (title, level, favorite_count, view_count, write_datetime, write_nickname) VALUES
    ('10부터 거꾸로 출력하기',0, 3, 5, '2023-11-02 00:00:00', 'user1');

-- SELECT * FROM Example;

-- 문제 상세 정보 테이블 생성
create TABLE Example_Source(
                               exam_number INT NOT NULL UNIQUE COMMENT '문제 번호',
                               introduce text NOT NULL COMMENT '문제 설명',
                               exam_constraints text NOT NULL COMMENT '제한 조건',
                               exam_input text NOT NULL COMMENT '입력 예시',
                               exam_output text NOT NULL COMMENT '출력 예시',
                               PRIMARY KEY (exam_number)
) COMMENT '문제 상세 정보';

INSERT INTO Example_Source (exam_number, introduce, exam_constraints, exam_input, exam_output) VALUES (
(SELECT example_number FROM Example WHERE title='10까지 출력하기' AND write_nickname='담순쨩'),
    '1부터 10까지 출력하세요',
    '변수가 없기에 제한 조건은 없습니다.',
    '',
    '1 2 3 4 5 6 7 8 9 10'
);
INSERT INTO Example_Source (exam_number, introduce, exam_constraints, exam_input, exam_output) VALUES (
(SELECT example_number FROM Example WHERE title='10부터 거꾸로 출력하기' AND write_nickname='user1'),
    '10부터 1까지 출력하세요',
    '변수가 없기에 제한 조건은 없습니다.',
    '',
    '10 9 8 7 6 5 4 3 2 1'
);

-- Example 테이블에 유저 정보 외부키 생성

ALTER TABLE Example
    ADD CONSTRAINT FK_user_TO_Example
        FOREIGN KEY (write_nickname)
            REFERENCES user (nickname)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- Example_Source 테이블에 Example 고유 아이디 외부키 생성

ALTER TABLE Example_Source
    ADD CONSTRAINT FK_Example_TO_Example_Source
        FOREIGN KEY (exam_number)
            REFERENCES Example (example_number)
            ON DELETE CASCADE ON UPDATE CASCADE;

-- 회원 가입
-- INSERT INTO user VALUES ('rhdwhdals8@naver.com', '@pass1234', 'Damsoon', '01025709448', '경기도 화성시 탄요1길 25-8', '301호', null);
-- 유저 전체 검색
-- SELECT * FROM user;

-- 게시물 작성
-- INSERT INTO Example VALUES (1, '제목 예시1', 12, 100, '2023-11-10', 'Damhyeong');
-- 게시물 전체 검색
-- SELECT * FROM Example;

-- 레벨 별 게시물 정보 가져오기 - id로 정렬
SELECT example_number, title, level
FROM Example
WHERE level = 0
ORDER BY example_number;

-- 클릭 시 게시물 상세 정보 가져오기
SELECT exam.level, exam.example_number, exam.title, src.introduce, src.exam_constraints, src.exam_input, src.exam_output
FROM Example exam
         NATURAL JOIN Example_Source src
WHERE exam.example_number = 1 AND exam.example_number = src.exam_number;

-- 최신 게시물 정보 가져오기 - id 역정렬
SELECT example_number, title, level
FROM Example
ORDER BY example_number DESC
    LIMIT 10;

-- 해당 example_number의 게시물 수정하기
UPDATE Example
SET
    title = '수정된 제목',
    level = 1,
    write_datetime = '2023-11-11 08:30:00'
WHERE example_number = 1;
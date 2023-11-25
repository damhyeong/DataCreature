DROP TABLE IF EXISTS user, Example, Example_Source;


create TABLE user(
                     email VARCHAR(50) NOT NULL COMMENT '사용자 이메일',
                     password VARCHAR(100) NOT NULL COMMENT '사용자 비밀번호',
                     nickname VARCHAR(20) NOT NULL COMMENT '사용자 별명',
                     tel_number VARCHAR(20) NOT NULL COMMENT '사용자 전화번호',
                     address TEXT NOT NULL COMMENT '사용자 주소',
                     address_detail TEXT NULL COMMENT '사용자 상세 주소',
                     profile_image TEXT NULL COMMENT '사용자 프로필 주소',
                     PRIMARY KEY (email)
) COMMENT '유저 테이블';

INSERT INTO user VALUES (
                            'rhdwhdals8@naver.com',
                            'pass1234', '담순쨩',
                            '010-2570-9448',
                            '경기도 화성시 탄요1길 25-8',
                            '301호',
                            null
                        );
INSERT INTO user VALUES (
                            'user1@gmail.com',
                            'pass1234', 'user1',
                            '010-1234-5678',
                            '경기도 화성시 탄요1길 25-8',
                            '301호',
                            null
                        );

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

INSERT INTO Example VALUES (0, '10까지 출력하기', 0, 2, 4, '2023-11-01', '담순쨩');
INSERT INTO Example VALUES (1, '10부터 거꾸로 출력하기',0, 3, 5, '2023-11-02', 'user1');

create TABLE Example_Source(
                               exam_number INT NOT NULL UNIQUE COMMENT '문제 번호',
                               introduce text NOT NULL COMMENT '문제 설명',
                               exam_constraints text NOT NULL COMMENT '제한 조건',
                               exam_input text NOT NULL COMMENT '입력 예시',
                               exam_output text NOT NULL COMMENT '출력 예시',
                               PRIMARY KEY (exam_number)
) COMMENT '문제 상세 정보';

INSERT INTO Example_Source VALUES (
                                      0,
                                      '1부터 10까지 출력하세요',
                                      '변수가 없기에 제한 조건은 없습니다.',
                                      '',
                                      '1 2 3 4 5 6 7 8 9 10'
                                  );
INSERT INTO Example_Source VALUES (
                                      1,
                                      '10부터 1까지 출력하세요',
                                      '변수가 없기에 제한 조건은 없습니다.',
                                      '',
                                      '10 9 8 7 6 5 4 3 2 1'
                                  );

ALTER TABLE Example
    ADD CONSTRAINT FK_user_TO_Example
        FOREIGN KEY (write_nickname)
            REFERENCES user (nickname);

ALTER TABLE Example_Source
    ADD CONSTRAINT FK_Example_TO_Example_Source
        FOREIGN KEY (exam_number)
            REFERENCES Example (example_number);

-- 회원 가입
-- INSERT INTO user VALUES ('rhdwhdals8@naver.com', '@pass1234', 'Damsoon', '01025709448', '경기도 화성시 탄요1길 25-8', '301호', null);
-- SELECT * FROM user;

-- 게시물 작성
-- INSERT INTO Example VALUES (1, '제목 예시1', 12, 100, '2023-11-10', 'Damhyeong');
-- SELECT * FROM Example;

SELECT * FROM Example;

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
INSERT INTO Example (title, level, favorite_count, view_count, write_datetime, write_nickname)
VALUES ('DFS와 BFS', '2', '1', '2', '2023-11-00 00:00:00', 'user1');

INSERT INTO Example_Source (exam_number, introduce, exam_constraints, exam_input, exam_output) VALUES (
                                                                                                          (SELECT example_number FROM Example WHERE title='DFS와 BFS' AND write_nickname='user1'),
                                                                                                          '그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.',
                                                                                                          '첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.',
                                                                                                          '4 5 1
                                                                                                      1 2
                                                                                                      1 3
                                                                                                      1 4
                                                                                                      2 4
                                                                                                      3 4',
                                                                                                          '1 2 4 3
                                                                                                      1 2 3 4'
                                                                                                      );

INSERT INTO Example (title, level, favorite_count, view_count, write_datetime, write_nickname)
VALUES ('바이러스', '3', '1', '2', '2023-11-00 00:00:00', 'user1');

INSERT INTO Example_Source (exam_number, introduce, exam_constraints, exam_input, exam_output) VALUES (
                                                                                                          (SELECT example_number FROM Example WHERE title='바이러스' AND write_nickname='user1'),
                                                                                                          '신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다. 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.

                                                                                                          예를 들어 7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자. 1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다. 하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.



                                                                                                          어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.',
                                                                                                          '첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하인 양의 정수이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다. 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.',
                                                                                                          '7
                                                                                                          6
                                                                                                          1 2
                                                                                                          2 3
                                                                                                          1 5
                                                                                                          5 2
                                                                                                          5 6
                                                                                                          4 7',
                                                                                                          '4'
                                                                                                      );

INSERT INTO Example (title, level, favorite_count, view_count, write_datetime, write_nickname)
VALUES ('유기농 배추', '4', '1', '2', '2023-11-00 00:00:00', 'user1');

INSERT INTO Example_Source (exam_number, introduce, exam_constraints, exam_input, exam_output) VALUES (
                                                                                                          (SELECT example_number FROM Example WHERE title='유기농 배추' AND write_nickname='user1'),
                                                                                                          '차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다. 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다. 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다. 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.

한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다. 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
1	1	0	0	0	0	0	0	0	0
0	1	0	0	0	0	0	0	0	0
0	0	0	0	1	0	0	0	0	0
0	0	0	0	1	0	0	0	0	0
0	0	1	1	0	0	0	1	1	1
0	0	0	0	1	0	0	1	1	1',
                                                                                                          '첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하인 양의 정수이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다. 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.',
                                                                                                          '7
                                                                                                          6
                                                                                                          1 2
                                                                                                          2 3
                                                                                                          1 5
                                                                                                          5 2
                                                                                                          5 6
                                                                                                          4 7',
                                                                                                          '4'
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
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (1, null, 'ABC 회사', null, 'Company');

INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (2, 1, '경영지원본부', 'A', 'Division');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (3, 2, '인사팀', 'A1', 'Department');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (4, 2, '총무팀', 'A2', 'Department');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (5, 2, '법무팀', 'A3', 'Department');


INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (6, 1, 'SW 개발본부', 'B', 'Division');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (7, 6, '플랫폼개발부', 'B1', 'Department');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (8, 6, '비즈서비스개발부', 'B2', 'Department');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (9, 7, '비즈플랫폼팀', 'B1B1', 'Department');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (10, 7, '비즈서비스팀', 'B1B2', 'Department');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (11, 7, '그룹웨어개발팀', 'B1B3', 'Department');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (12, 8, '플랫폼서비스팀', 'B2B1', 'Department');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (13, 8, '모바일개발팀', 'B2B2', 'Department');

INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (15, 1, '사장1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (16, 2, '경영1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (17, 3, '인사1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (18, 3, '인사2', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (19, 3, '인사3', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (20, 4, '총무1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (21, 4, '총무2', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (22, 5, '법무1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (23, 5, '법무2', null, 'Member');

INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (24, 6, 'SW1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (25, 7, '플랫폼1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (26, 8, '서비스1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (27, 9, '개발1', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (28, 9, '개발2', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (29, 10, '개발3', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (30, 10, '개발4', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (31, 11, '개발5', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (32, 11, '개발6', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (33, 12, '개발7', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (34, 12, '개발8', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (35, 13, '개발9', null, 'Member');
INSERT INTO groups(group_id, parent_id, name, dept_code, type )
VALUES (36, 13, '개발10', null, 'Member');

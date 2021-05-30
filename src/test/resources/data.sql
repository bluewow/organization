INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (1, null, 'ABC 회사', null, 'Company');

INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (2, 1, '경영지원본부', 'A', 'Division');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (3, 2, '인사팀', 'A1', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (4, 2, '총무팀', 'A2', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (5, 2, '법무팀', 'A3', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (15, 5, '법무팀 인원1', null, 'Member');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (16, 5, '법무팀 인원2', null, 'Member');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (17, 4, '총무팀 인원1', null, 'Member');

INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (6, 1, 'SW 개발본부', 'B', 'Division');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (7, 6, '플랫폼개발부', 'B1', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (8, 6, '비즈서비스개발부', 'B2', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (9, 7, '비즈플랫폼팀', 'B1B1', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (10, 7, '비즈서비스팀', 'B1B2', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (11, 7, '그룹웨어개발팀', 'B1B3', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (12, 8, '플랫폼서비스팀', 'B2B1', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (13, 8, '모바일개발팀', 'B2B2', 'Department');
INSERT INTO public.groups(group_id, parent_id, name, dept_code, type )
VALUES (14, 13, '모바일개발 인원1', null, 'Member');
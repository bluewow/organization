drop table if exists groups cascade;

CREATE TABLE groups
(
    group_id bigint NOT NULL,
    parent_id bigint,
    name character varying(255) COLLATE pg_catalog."default",
    dept_code character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT groups_pkey PRIMARY KEY (group_id),
    CONSTRAINT fkfvqfb4l9r8hbfhfm515o77esh FOREIGN KEY (parent_id)
        REFERENCES groups (group_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.groups
    OWNER to postgres;
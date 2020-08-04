create table comment
(
    id           int          not null
        primary key,
    changed_by   varchar(100) null,
    changed_on   datetime(6)  null,
    comment_text text         null,
    created_by   varchar(100) null,
    created_on   datetime(6)  null,
    article_id   int          null,
    constraint FK5yx0uphgjc6ik6hb82kkw501y
        foreign key (article_id) references article (id)
);

INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (71, null, null, 'hey', null, null, 1);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (72, null, null, 'hello', null, null, 1);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (73, null, null, 'great', null, null, 1);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (74, null, null, 'test', null, null, 2);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (75, null, null, 'Lorem ipsum dolor sit amet, consectetu', null, null, 2);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (76, null, null, 'awesome', null, null, 3);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (77, null, null, 'Lorem ipsum dolor sit amet, consectetu
', null, null, 3);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (78, null, null, 'hello', null, null, 3);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (79, null, null, 'Lorem ipsum dolor sit amet, consectetu', null, null, 4);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (80, null, null, 'cms', null, null, 4);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (81, null, null, 'hello', null, null, 5);
INSERT INTO cms.comment (id, changed_by, changed_on, comment_text, created_by, created_on, article_id) VALUES (82, null, null, 'text', null, null, 5);
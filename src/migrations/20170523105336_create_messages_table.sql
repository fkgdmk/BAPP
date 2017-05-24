CREATE TABLE IF NOT EXISTS `jasonkel_bapp`.messages
(
    id int(10) unsigned auto_increment
        primary key,
    message varchar(500) null,
    created_at timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    media_textmessage tinyint(1) null,
    media_email tinyint(1) null,
    media_facebook tinyint(1) null,
    message_subject varchar(50) not null
);
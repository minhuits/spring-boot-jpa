-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values ( 1, 'martin', 'martin@fastcmpus.com', now(), now());

-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values ( 2, 'dennis', 'dennis@fastcmpus.com', now(), now());

-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values ( 3, 'sopthia', 'sopthia@slowcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values ( 4, 'james', 'james@slowcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into users (`id`, `name`, `email`, `created_at`, `updated_at`) values ( 5, 'martin', 'martin@naver.com', now(), now());

insert into publisher(`id`, `name`) values ( 1, '패스트캠퍼스');

insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values ( 1, 'JPA 패키지', 1, false, 100 );

insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values ( 2, 'Security 패키지', 1, false, 200 );

insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values ( 3, 'Security 패키지', 1, true, 100 );

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values ( 1, '내 인생을 바꾼 책', '좋아요', 5.0, 1, 1 );

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values ( 2, '무난한 책', '보통이요', 3.0, 2, 2 );

insert into comment(`id`, `comment`, `review_id`) values ( 1, '좋아요', 1);

insert into comment(`id`, `comment`, `review_id`) values ( 2, '보통요', 1);

insert into comment(`id`, `comment`, `review_id`) values ( 3, '싫어요', 2);
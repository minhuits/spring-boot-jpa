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

insert into book(`id`, `name`, `publisher_id`, `deleted`) values ( 1, 'Spring JPA 패키지', 1, false );

insert into book(`id`, `name`, `publisher_id`, `deleted`) values ( 2, 'Spring Security 패키지', 1, false );

insert into book(`id`, `name`, `publisher_id`, `deleted`) values ( 3, 'Spring Security 패키지', 1, true );
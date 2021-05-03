create table cities
(
    id        bigserial primary key,
    name      varchar(255) not null,
    latitude  double precision not null,
    longitude double precision not null
);

create table distances
(
    id bigserial primary key,
    from_id  bigint references cities,
    to_id    bigint references cities,
    distance double precision
);
create table public.order (
    id bigserial primary key,
    order_id varchar(128),
    item_id varchar(128),
    create_time timestamp with time zone
);

create table public.item (
    id bigserial primary key,
    item_id varchar(128),
    item_count int
);
CREATE TABLE IF NOT EXISTS public.users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    session varchar(255),
    chat_id serial,
    seconds serial
);

create table if not exists public.notifications (
    notification_id serial primary key,
    "text" varchar(255),
    datetime timestamp,
    user_chat_id serial
);
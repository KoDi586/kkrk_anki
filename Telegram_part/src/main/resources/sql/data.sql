INSERT INTO public.users (user_id, username, session, chat_id, seconds) VALUES
    (1235, 'user1', 'null', 1, 20),
    (1236, 'user2', 'null', 2, 20),
    (1237, 'user3', 'null', 3, 20)
ON CONFLICT (user_id) DO NOTHING;

SELECT n FROM notifications n
WHERE n.datetime >= '2025-02-27 14:00:01'
AND n.datetime < '2025-02-28 14:00:00';


INSERT INTO public.users (user_id, username, chat_id) VALUES
    (1235, 'user1', 1),
    (1236, 'user2', 2),
    (1237, 'user3', 3)
ON CONFLICT (user_id) DO NOTHING;


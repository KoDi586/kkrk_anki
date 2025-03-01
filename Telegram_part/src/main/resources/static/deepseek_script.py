import asyncio
import sys
import io
import os
import pickle

# Принудительно устанавливаем UTF-8
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

from DeeperSeek import DeepSeek

TOKEN_FILE = "deepseek_token.pkl"

async def run_deepseek(message):
    api = None
    token = None

    # Проверяем, есть ли сохранённый токен
    if os.path.exists(TOKEN_FILE):
        try:
            with open(TOKEN_FILE, 'rb') as f:
                token = pickle.load(f)
            api = DeepSeek(
                token=token,
                chat_id=None,
                verbose=False,
                headless=True,
                chrome_args=["--no-sandbox", "--disable-gpu", "--remote-debugging-port=9333"],
                attempt_cf_bypass=True
            )
            await api.initialize()  # Инициализируем даже с токеном
            response = await api.send_message(message, deepthink=True, timeout=60)
            print(response.text)
        except Exception as e:
            print(f"Ошибка с токеном: {e}", file=sys.stderr)
            # Если токен не работает, удаляем его и пробуем авторизацию заново
            os.remove(TOKEN_FILE)
            return await run_deepseek_with_login(message)
    else:
        api = await run_deepseek_with_login(message)

async def run_deepseek_with_login(message):
    api = DeepSeek(
        email="kim.danil.586@gmail.com",           # Замените на ваш email
        password="q1111111",     # Замените на ваш пароль
        chat_id=None,
        verbose=False,
        headless=True,
        chrome_args=["--no-sandbox", "--disable-gpu", "--remote-debugging-port=9333"],
        attempt_cf_bypass=True
    )
    await api.initialize()
    response = await api.send_message(message, deepthink=True, timeout=60)
    print(response.text)
    # Сохраняем токен после успешного входа
    token = await api.retrieve_token()
    with open(TOKEN_FILE, 'wb') as f:
        pickle.dump(token, f)
    return api

if __name__ == "__main__":
    message = sys.argv[1] if len(sys.argv) > 1 else "Hey DeepSeek, how are you?"
    asyncio.run(run_deepseek(message))
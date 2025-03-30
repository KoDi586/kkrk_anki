import asyncio
import sys
import io
import os
import pickle

# Принудительно устанавливаем UTF-8
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

from DeeperSeek import DeepSeek

TOKEN_FILE = "deepseek_token.pkl"

async def initialize_api():
    if os.path.exists(TOKEN_FILE):
        try:
            with open(TOKEN_FILE, 'rb') as f:
                token = pickle.load(f)
            api = DeepSeek(
                token=token,
                chat_id=None,
                verbose=False,
                headless=False,
                chrome_args=["--no-sandbox", "--disable-gpu", "--remote-debugging-port=9333"],
                attempt_cf_bypass=True
            )
            await api.initialize()
            await api.send_message("Test", deepthink=False, timeout=60)
            return api
        except Exception as e:
            print(f"Ошибка с токеном: {e}", file=sys.stderr)
            os.remove(TOKEN_FILE)

    api = DeepSeek(
        email="kim.danil.586@gmail.com",           # Замените на ваш email
        password="q1111111",     # Замените на ваш пароль

        chat_id=None,
        verbose=False,
        headless=False,
        chrome_args=["--no-sandbox", "--disable-gpu", "--remote-debugging-port=9333"],
        attempt_cf_bypass=True
    )
    await api.initialize()
    token = await api.retrieve_token()
    with open(TOKEN_FILE, 'wb') as f:
        pickle.dump(token, f)
    return api

async def run_deepseek_server():
    api = await initialize_api()
    print("Ready", file=sys.stderr)
    sys.stderr.flush()

    while True:
        try:
            message = input().strip()
            if message.lower() == "exit":
                break
            response = await api.send_message(message, deepthink=False, timeout=60)
            print(response.text)  # Выводим ответ
            print("END_RESPONSE")  # Явный маркер конца ответа
            sys.stdout.flush()
        except EOFError:
            break
        except Exception as e:
            print(f"Ошибка: {e}", file=sys.stderr)
            print("END_RESPONSE", file=sys.stderr)
            sys.stdout.flush()

    await api.logout()
    print("Shutdown", file=sys.stderr)

if __name__ == "__main__":
    asyncio.run(run_deepseek_server())
#include <windows.h>
#include <shellapi.h>
#include <iostream>

LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam);
void RegisterAppBar(HWND hWnd);
void SetAppBarPosition(HWND hWnd, UINT edge);
void RemoveAppBar(HWND hWnd);

// Глобальные переменные
HINSTANCE hInst;

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow)
{
    hInst = hInstance;
    const char CLASS_NAME[] = "AppBarExample";

    // Регистрируем класс окна
    WNDCLASS wc = {};
    wc.lpfnWndProc = WndProc;
    wc.hInstance = hInstance;
    wc.lpszClassName = CLASS_NAME;
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW + 9);

    if (!RegisterClass(&wc))
    {
        MessageBox(NULL, "Window class registration failed!", "Error", MB_ICONERROR);
        return 0;
    }

    // Создаем окно
    HWND hWnd = CreateWindowEx(
        0,
        CLASS_NAME,
        "My App Bar",
        WS_POPUP | WS_VISIBLE,
        0, 0, 0, 0,
        NULL, NULL, hInstance, NULL);

    if (!hWnd)
    {
        MessageBox(NULL, "Window creation failed!", "Error", MB_ICONERROR);
        return 0;
    }

    // Регистрируем панель приложений
    RegisterAppBar(hWnd);

    // Устанавливаем её на левую сторону экрана
    SetAppBarPosition(hWnd, ABE_BOTTOM);

    // Запуск цикла обработки сообщений
    MSG msg = {};
    while (GetMessage(&msg, NULL, 0, 0))
    {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    // Удаляем панель при завершении работы
    RemoveAppBar(hWnd);
    return 0;
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
    switch (message)
    {
    case WM_DESTROY:
        PostQuitMessage(0);
        return 0;
    default:
        return DefWindowProc(hWnd, message, wParam, lParam);
    }
}

void RegisterAppBar(HWND hWnd)
{
    APPBARDATA abd = {0};
    abd.cbSize = sizeof(APPBARDATA);
    abd.hWnd = hWnd;

    // Регистрируем приложение как панель
    SHAppBarMessage(ABM_NEW, &abd);
}

void SetAppBarPosition(HWND hWnd, UINT edge)
{
    APPBARDATA abd = {0};
    abd.cbSize = sizeof(APPBARDATA);
    abd.hWnd = hWnd;
    abd.uEdge = edge;

    // Получаем размеры экрана
    RECT screenRect;
    SystemParametersInfo(SPI_GETWORKAREA, 0, &screenRect, 0);

    // Устанавливаем размер панели в зависимости от стороны
    switch (edge)
    {
    case ABE_LEFT:
        abd.rc = {0, 0, 60, screenRect.bottom};
        break;
    case ABE_TOP:
        abd.rc = {0, 0, screenRect.right, 60};
        break;
    case ABE_RIGHT:
        abd.rc = {screenRect.right - 60, 0, screenRect.right, screenRect.bottom};
        break;
    case ABE_BOTTOM:
        abd.rc = {500, screenRect.bottom - 40, screenRect.right - 500, screenRect.bottom};
        break;
    }

    // Устанавливаем позицию панели
    SHAppBarMessage(ABM_SETPOS, &abd);

    // Перемещаем окно в эту позицию
    SetWindowPos(hWnd, NULL, abd.rc.left, abd.rc.top,
                 abd.rc.right - abd.rc.left, abd.rc.bottom - abd.rc.top,
                 SWP_NOZORDER | SWP_NOACTIVATE);
}

void RemoveAppBar(HWND hWnd)
{
    APPBARDATA abd = {0};
    abd.cbSize = sizeof(APPBARDATA);
    abd.hWnd = hWnd;

    // Удаляем приложение как панель
    SHAppBarMessage(ABM_REMOVE, &abd);
}

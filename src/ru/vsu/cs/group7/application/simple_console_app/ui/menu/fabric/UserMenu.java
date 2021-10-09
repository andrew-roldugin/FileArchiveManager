package ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric;

import ru.vsu.cs.group7.application.simple_console_app.Controller;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.FirstPage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user.AllUsersPage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user.EditUserPage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user.LogoutPage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user.RemoveUserPage;
import ru.vsu.cs.group7.service.Service;

public class UserMenu extends BaseMenu {
    private final String content = String.format(
            """
                    Вход выполнен: %s
                    ---------------------------------------------------------------------------------
                    1) Найти всех пользователей
                    2) Изменить данные пользователя
                    3) Удалить учетную запись
                    4) Выйти
                    ---------------------------------------------------------------------------------
                    0) Завершение работы
                    """, getController().getUser().getLogin());

    public UserMenu(Controller controller, Service userService) {
        super(controller, userService);
        setCurrentPage(new FirstPage(this, content));
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice) {
            case 1 -> setCurrentPage(new AllUsersPage(this));
            case 2 -> setCurrentPage(new EditUserPage(this));
            case 3 -> setCurrentPage(new RemoveUserPage(this));
            case 4 -> setCurrentPage(new LogoutPage(this));
            case 0 -> exit();
        }
    }
}
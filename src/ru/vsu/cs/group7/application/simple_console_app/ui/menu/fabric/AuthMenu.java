package ru.vsu.cs.group7.application.simple_console_app.ui.menu.fabric;

import ru.vsu.cs.group7.application.simple_console_app.Controller;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.FirstPage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user.CreateUserPage;
import ru.vsu.cs.group7.application.simple_console_app.ui.menu.pages.user.LoginPage;
import ru.vsu.cs.group7.service.Service;

public class AuthMenu extends BaseMenu {
    private final String content =
            """
            |---------------------------------------------------------------------------------|
            |Вход не выполнен                                                                 |
            |---------------------------------------------------------------------------------|
            |1) Создать пользователя;                                                         |
            |2) Войти;                                                                        |
            |               ___________________________________________________               |
            |0) Завершение работы;                                                            |
            |_________________________________________________________________________________|
            """;

    public AuthMenu(Controller controller, Service userService) {
        super(controller, userService);
        setCurrentPage(new FirstPage(this, content));
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice) {
                case 1 -> setCurrentPage(new CreateUserPage(this));
                case 2 -> setCurrentPage(new LoginPage(this));
                case 0 -> exit();
        }
    }
}

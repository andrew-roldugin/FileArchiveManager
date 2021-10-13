package ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.user.CreateUserPage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.user.LoginPage;
import ru.vsu.cs.group7.service.Service;

public class AuthMenu extends BaseMenu {

    public AuthMenu(Controller controller, Service userService) {
        super(controller, userService);
        this.content =
                        """
                         _________________________________________________________________________________
                        /								   Меню авторизации    							  \\
                        |---------------------------------------------------------------------------------|
                        |Вход не выполнен                                                                 |
                        |---------------------------------------------------------------------------------|
                        |1) Создать пользователя;                                                         |
                        |2) Войти;                                                                        |
                        |               ___________________________________________________               |
                        |0) Завершение работы;                                                            |
                        |_________________________________________________________________________________|
                        """;
    }

    @Override
    protected void onSelect(Integer choice) {
        switch (choice) {
            case 0 -> exit();

            case 1 -> setCurrentPage(new CreateUserPage(this));
            case 2 -> setCurrentPage(new LoginPage(this));
        }
    }
}

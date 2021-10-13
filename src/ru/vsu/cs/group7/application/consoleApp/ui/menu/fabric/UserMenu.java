package ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric;

import ru.vsu.cs.group7.application.consoleApp.Controller;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.user.AllUsersPage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.user.EditUserPage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.user.LogoutPage;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.user.RemoveUserPage;
import ru.vsu.cs.group7.service.Service;

public class UserMenu extends BaseMenu {

    public UserMenu(Controller controller, Service userService) {
        super(controller, userService);
        String login = getController().getUser().getLogin();
        this.content = String.format(
                """
                         _________________________________________________________________________________
                        /								 Меню пользователей 							  \\
                        |---------------------------------------------------------------------------------|
                        |Вход выполнен: %s|
                        |---------------------------------------------------------------------------------|
                        | 1) Найти всех пользователей;													  |
                        | 2) Изменить данные пользователя;												  |
                        | 3) Удалить учетную запись; 													  |
                        | 4) Выйти;																		  |
                        | 5) -> Перейти в меню архивы;												      |
                        |				___________________________________________________               |
                        | 0) Завершение работы															  |
                        |_________________________________________________________________________________|
                        """, login + " ".repeat(66 - login.length()));
    }

    @Override
    public void onSelect(Integer choice) {
        switch (choice) {
            case 0 -> exit();

            case 1 -> setCurrentPage(new AllUsersPage(this));
            case 2 -> setCurrentPage(new EditUserPage(this));
            case 3 -> setCurrentPage(new RemoveUserPage(this));
            case 4 -> setCurrentPage(new LogoutPage(this));

            case 5 -> switchMenu(MenusEnum.FileArchiveMenu);
        }
    }
}
package ru.vsu.cs.group7.application.consoleApp;

import ru.vsu.cs.group7.application.Application;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.BaseMenu;

public class ConsoleApp implements Application {
    private final Controller controller = new Controller();

    @Override
    public void start(String[] args) {

        while (true) {
            getCurrentMenu().printMenu();
        }
//            while (true) {
//            Integer choice = null;
//            try {
//                getCurrentMenu().printMenu();
//            } catch (ApplicationException e) {
//                System.out.println(e.getMessage() + '\n');
//                System.out.println("Для продолжения введите любой символ...");
//                controller.getScanner().next();
//                controller.getMenuManager().getCurrentMenu().setCurrentPage(null);
//            }
//            try {
//                choice = getCurrentMenu().getChoice();
//            } catch (InputMismatchException ex) {
//                System.out.println("Некорректный ввод при выборе пункта. Ожидалось число");
//                controller.setScanner(new Scanner(System.in));
////                choice = null;
//            }
//            if (choice != null) {
//                getCurrentMenu().onSelect(choice);
//
////                getCurrentMenu().printMenu();
//            }
//        }
    }

    public BaseMenu getCurrentMenu() {
        return controller.getMenuManager().getCurrentMenu();
    }

}














/*
public class ConsoleApp implements Application {

    //    private final static Map<Integer, String> pages = new HashMap<>(Map.of(
//            1, UserService.isLoggedIn() ? "1) Найти все архивы;\n2) Изменить данные пользователя\n3) Выйти\n" : "1) Создать пользователя;\n2) Войти\n"
//    ));
//    private List<Page> pages = Arrays.asList(new Page[]{
//        new MainPage(),
//            new LoginPage(), new
//    });
    private static final Scanner scanner = new Scanner(System.in);
    private final Controller controller = new Controller(new MenuManager());
//    private static Page currentBaseMenu = MainPage.getINSTANCE();

    @Override
    public void start(String[] args) {
        Integer choice;
        while (true) {
            getCurrentMenu().printMenu();
            choice = getCurrentMenu().getChoice();
            getCurrentMenu().onSelect(choice);
        }
    }

//    public static void start() {
//        Integer choice;
//        while (true) {
//            getCurrentMenu().printMenu();
//            choice = getCurrentMenu().getChoice();
//            getCurrentMenu().onSelect(choice);
////            if (key != null)
////                currentBaseMenu.getAction().accept(key);
//        }
////        do {
////            key = currentPage.printMenu();
////            if (key != null)
////                currentPage.getAction().accept(key);
////
//////            System.out.print("Введите номер меню: ");
//////            key = scanner.nextInt();
////            currentPage.getAction().accept(key);
////        } while (key.intValue() != 4);
//    }

    public BaseMenu getCurrentMenu() {
        return this.controller.getMenuManager().getCurrentMenu();
    }

    public Controller getController() {
        return controller;
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
 */
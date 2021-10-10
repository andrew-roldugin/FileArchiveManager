package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.application.consoleApp.ui.pages.Page;
import ru.vsu.cs.group7.exception.ApplicationException;

public class RemoveArchivePage extends Page {
    public RemoveArchivePage(FileArchiveMenu fileArchiveMenu) {
        super(fileArchiveMenu, "");
    }

    @Override
    public void openPage() throws ApplicationException {

    }
}

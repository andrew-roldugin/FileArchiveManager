package ru.vsu.cs.group7.application.consoleApp.ui.pages.archive;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.common.MenusEnum;
import ru.vsu.cs.group7.application.consoleApp.ui.menu.fabric.FileArchiveMenu;
import ru.vsu.cs.group7.exception.ActionCancelled;
import ru.vsu.cs.group7.exception.ApplicationException;

public class AllArchivesPage extends ArchivesPages {

    public AllArchivesPage(FileArchiveMenu parentMenu) {
        super(parentMenu, "=========================== Информация обо всех архивах ===========================");
        this.isWait = true;
    }

    @Override
    public void openPage() throws ApplicationException, ActionCancelled {
        renderTable(new String[]{"ID", "Название", "Дата создания", "Дата обновления"}, getFileArchiveService().getAllArchives());
        backToMenu(getParentMenu(), MenusEnum.FileArchiveMenu, getIsWait());
    }
}

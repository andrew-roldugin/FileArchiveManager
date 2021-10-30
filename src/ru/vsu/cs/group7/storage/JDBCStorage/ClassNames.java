package ru.vsu.cs.group7.storage.JDBCStorage;

public enum ClassNames {

    File(ru.vsu.cs.group7.model.File.class, ru.vsu.cs.group7.model.File.class.getSimpleName()),
    User(ru.vsu.cs.group7.model.User.class, ru.vsu.cs.group7.model.User.class.getSimpleName()),
    FileArchive(ru.vsu.cs.group7.model.FileArchive.class, ru.vsu.cs.group7.model.FileArchive.class.getSimpleName());

    private Class typeName;
    private String simpleName;

    ClassNames(Class typeName, String simpleName) {
        this.typeName = typeName;
        this.simpleName = simpleName;
    }
}

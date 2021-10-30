package ru.vsu.cs.group7.storage.JDBCStorage;

import ru.vsu.cs.group7.model.Entity;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.storage.interfaces.FileStorage;

import java.lang.reflect.TypeVariable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FileRepository extends BaseRepository<File, Long> implements FileStorage<Long> {


    public static void main(String[] args) {
        new FileRepository().getAll();
    }
    public FileRepository() {
//        extractor = rs -> {
//            List<File> items = new ArrayList<>();
//            while (rs.next()) {
//                File file = File.getMapper(rs);
//                items.add(file);
////            final Item item = new Item(
////                    rs.getString("name"),
////                    rs.getDate("created_at").toLocalDate()
////            );
////            item.setId(rs.getLong("id"));
////            items.add(item);
//            }
//            return items;
//        };
    }

    @Override
    public List<File> getAllFilesInFileArchive(Long fileArchiveId) {
        return null;
    }

    @Override
    public void removeAllByArchiveId(Long archiveId) {

    }

    @Override
    public File save(File item) {
        return null;
    }

    @Override
    public Optional<File> getOneById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public File updateById(Long aLong, File newData) {
        return null;
    }

    @Override
    public File removeById(Long aLong) {
        return null;
    }
}

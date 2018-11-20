package com.wx.movie.manage.factory;

import com.wx.movie.manage.utlis.Hib;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class FileFactory {
    private static FileOutputStream fos;

    public static String saveFile(InputStream inputStream, String fileName, String root) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String newName = UUID.randomUUID().toString().replace("-", "") + "." + suffix;
        String dirName = "/movie/" + LocalDate.now() + "/";
        File rootDir = new File(root, dirName);
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }
        File outFile = new File(rootDir, newName);
        try {
            fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[512];
            int length = -1;
            while ((length = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dirName + newName;
    }

    public static com.wx.movie.manage.bean.db.File saveFile(String fileName, String path, String shortUrl) {
        com.wx.movie.manage.bean.db.File file = new com.wx.movie.manage.bean.db.File();
        file.setName(fileName);
        file.setPath(path);
        file.setShortPath(shortUrl);
        return Hib.query(session -> {
            session.save(file);
            return file;
        });
    }

    public static List<com.wx.movie.manage.bean.db.File> getAll() {
        return Hib.query(session -> session.createQuery("from File order by createAt DESC").list());
    }

    public static boolean delete(String id) {
        com.wx.movie.manage.bean.db.File file = Hib.query(session -> (com.wx.movie.manage.bean.db.File) session
                .createQuery("from File where id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .uniqueResult());
        String path = file.getPath();
        Hib.queryOnly(session -> session.delete(file));
        return deleteFile(path);
    }

    private static boolean deleteFile(String path) {
        File file = new File("~/static/"+path);
        if (file.exists()) {
            return file.delete();
        } else {
            return true;
        }
    }
}

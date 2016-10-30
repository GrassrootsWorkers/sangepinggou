package com.farmer.fruit.file;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
/**
 * Created by liuzhi on 2016/7/13.
 */
public class CompressFile {
    /**
     * 压缩文件
     *
     * @param srcFile
     * File[] 需要压缩的文件列表
     * @param zipFile
     * File 压缩后的文件
     */
    public static void ZipFiles(java.io.File[] srcFile, java.io.File zipFile) {
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < srcFile.length; i++) {
                FileInputStream in = new FileInputStream(srcFile[i]);
                out.putNextEntry(new ZipEntry(srcFile[i].getName()));
                String str = srcFile[i].getName();
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            System.out.println("压缩完成.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * zip解压缩
     *
     * @param zipFile
     * File 需要解压缩的文件
     * @param descDir
     * String 解压后的目标目录
     */
    public static boolean unZipFiles(java.io.File zipFile, String descDir) {
        try {
            ZipFile zf = new ZipFile(zipFile);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String zipEntryName = entry.getName();
                InputStream in = zf.getInputStream(entry);
                File descFile = new File(descDir);
                if(!descFile.exists()){
                    descFile.mkdirs();
                }
                OutputStream out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            zf.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 根据原始rar路径，解压到指定文件夹下.
     * @param srcRarPath 原始rar路径
     * @param dstDirectoryPath 解压到的文件夹
     */
    public static boolean unRarFile(String srcRarPath, String dstDirectoryPath) {
        if (!srcRarPath.toLowerCase().endsWith(".rar")) {
            System.out.println("非rar文件！");
            return false;
        }
        File dstDirectory = new File(dstDirectoryPath);
        if (!dstDirectory.exists()) {// 目标目录不存在时，创建该文件夹
            dstDirectory.mkdirs();
        }
        Archive archive = null;
        try {
            archive = new Archive(new File(srcRarPath));
            if (archive != null) {
                //a.getMainHeader().print(); // 打印文件信息.
                FileHeader fh = archive.nextFileHeader();
                while (fh != null) {
                    if (fh.isDirectory()) {
                        File fol = new File(dstDirectoryPath + File.separator + fh.getFileNameString());
                        fol.mkdirs();
                    } else {
                        File out = new File(dstDirectoryPath + File.separator+ fh.getFileNameString().trim());
                        try {
                            if (!out.exists()) {
                                if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
                                    out.getParentFile().mkdirs();
                                }
                                out.createNewFile();
                            }
                            FileOutputStream os = new FileOutputStream(out);
                            archive.extractFile(fh, os);
                            os.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    fh = archive.nextFileHeader();
                }
                archive.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
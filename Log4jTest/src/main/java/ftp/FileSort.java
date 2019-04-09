package ftp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileSort {

    /**
     * 将文件数组排序，目录放在上面，文件在下面
     * @param file
     * @return
     */
    public static File[] noSort(File[] file)
    {
        ArrayList<File> list = new ArrayList<File>();
        //放入所有目录
        for (File f : file)
        {
            if (f.isDirectory())
            {
                list.add(f);
            }
        }
        //放入所有文件
        for (File f : file)
        {
            if (f.isFile())
            {
                list.add(f);
            }
        }

        return list.toArray(new File[file.length]);
    }

    /**
     * 将文件数组排序，目录放在上面，文件在下面 。最主要的目的是将文件倒序
     *
     * @param file
     * @return
     */
    public static File[] sort(File[] file) {
        ArrayList<File> list = new ArrayList<File>();
        for (File f : file) {
            if (f.isDirectory()) {
                list.add(f);
            }
        }
        for (File f : file) {
            if (f.isFile()) {
                list.add(f);
            }
        }
        // 将文件倒序
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o2.getName().compareTo(o1.getName());
            }
        });
        return list.toArray(new File[file.length]);
    }
}

package cn.sfcoder.count;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/17 18:17
 * @Version: 1.0
 */

public class CountRow {

    private static long emptyRows = 0;
    private static long noteRows = 0;
    private static long codeRows = 0;
    private static long totalRows = 0;

    public static void main(String[] args) {
        // 这里只统计 \src\main 文件夹里的文件，其他文件像 .idea、test 这些文件夹里的就不算进去了
        loopFolder("C:\\Users\\Lenovo\\Desktop\\project\\crowd-sourcing\\backend", "\\src\\main\\");
        System.out.println("空行:" + emptyRows);
        System.out.println("注释行:" + noteRows);
        System.out.println("代码行：" + codeRows);
        System.out.println("总行：" + totalRows);
    }

    private static void loopFolder(String projectPath, String specifiedFolders) {
        File f = new File(projectPath);

        if (!f.exists()) {

            System.out.println("项目路径不存在！");
            return;
        }
        File[] childs = f.listFiles(); // 获取这个项目下的文件、文件夹
        for (int i = 0; i < childs.length; i++) {
            File child = childs[i];
            if (!child.isDirectory()) { // 当前文件不是文件夹，就读取
                if (child.getParent().contains(specifiedFolders)) { // java文件、xml文件、配置文件 只统计main文件夹下的
                    if (child.getName().matches(".*\\.java$") || child.getName().endsWith(".yml") ||
                            child.getName().endsWith(".properties") || child.getName().endsWith(".xml")) {
                        long cnt1 = countCodeRows(child);
                        System.out.println(cnt1 + "\t\t" + child.getName());
                    }
                } else if ("pom.xml".equals(child.getName())) {
                    long fileRows = countCodeRows(child);
                    System.out.println(fileRows + "\t\t" + child.getName());
                }
            } else { // 当前文件是文件夹，继续递归
                loopFolder(child.getPath(), specifiedFolders);
            }
        }
    }

    private static long countCodeRows(File file) {
        long cnt = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            boolean flag = false; // 用于标记xml多行注释，为true表示当前行在多行注释中，一直到最后一行注释
            while ((line = br.readLine()) != null) {

                totalRows++;
                String lines = line.trim(); // 每一行的内容，去掉空格
                if (flag) {
                    noteRows++;
                    // 当多行注释结尾是 --> 说明多行注释结束，重新标记为false，表示当前不在统计多行注释
                    if (lines.endsWith("-->")) flag = false;
                } else {
                    if (lines.length() == 0) {
                        emptyRows++;
                    } else if (lines.startsWith("//") || lines.startsWith("/**") || lines.startsWith("*") ||
                            lines.startsWith("*/") || lines.startsWith("/*") || lines.startsWith("#")) {
                        noteRows++;
                    } else if (lines.startsWith("<!--")) {
                        noteRows++;
                        // 当前行属于xml的注释，且结尾不是 --> 时，表示是多行注释，设置标记为true
                        if (!lines.endsWith("-->")) flag = true;
                    } else {
                        if (!lines.startsWith("@") && !lines.startsWith("import")) {
                            codeRows++;
                            cnt++;
                        }
//                        if (true) {
//                            codeRows++;
//                            cnt++;
//                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cnt;
    }
}


package cn.kpy.FileIO;

import java.io.File;

public class FileBase {
    public static void main(String[] args) {
        String path=GetAbsolutePath();
        GetPath(path);
        CreateFile(path);
    }

    public static String GetAbsolutePath(){
        /**创建文件对象,路径为空时，获取当前路径*/
        File file = new File("");
        String path = file.getAbsolutePath();
        System.out.println("AbsolutePath:"+path);
        return path;
    }

    public static void GetPath(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    System.out.println("Directory：" + file.getPath());
                    //递归调用
                    GetPath(file1.getPath());
                } else {
                    System.out.println("File：" + file1.getAbsolutePath());
                }
            }
        }
    }

    public static void CreateFile(String path) {
        String filepath = path + File.separatorChar + "kpy";
        File file = new File(filepath);
        if (file.exists()) {
            try {
                file.delete();
                System.out.println("file directory delete completed!");
            } catch (Exception e) {
                try {
                    throw new Exception("delete file directory failed!");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (!file.exists()) {
            try {
                /**
                 * mkdirs()可以建立多级文件夹， mkdir()只会建立一级的文件夹， 如下：
                 *
                 * new File("/tmp/one/two/three").mkdirs();
                 * 执行后， 会建立tmp/one/two/three四级目录
                 *
                 * new File("/tmp/one/two/three").mkdir();
                 * 则不会建立任何目录， 因为找不到/tmp/one/two目录， 结果返回false
                 *
                 * */
                file.mkdirs();
            } catch (Exception e) {
                try {
                    /**
                     * 1.1   throw是语句抛出一个异常。
                     * 语法：throw (异常对象);
                     *          throw e;
                     *          throw new Exception("");
                     *
                     * 1.2   throws是方法可能抛出异常的声明。
                     * 语法：public static void method() throws Exception{
                     *
                     * }
                     * */
                    throw new Exception("create directory failed!");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            System.out.println("file directory is created!");
        }
        System.out.println(file.getPath());
    }
}

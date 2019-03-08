package cn.kpy.FileIO;

import java.io.*;

/**
 * 1．字节流：
 * 从InputStream和OutputStream派生出来的一系列类。这类流以字节(byte)为基本处理单位。
 * ◇ InputStream、OutputStream(1)
 * ◇ FileInputStream、FileOutputStream(0)
 * ◇ PipedInputStream、PipedOutputStream(0)
 * ◇ ByteArrayInputStream、ByteArrayOutputStream(0)
 * ◇ FilterInputStream、FilterOutputStream(0)
 * ◇ DataInputStream、DataOutputStream(0)
 * ◇ BufferedInputStream、BufferedOutputStream(1)
 * 2．字符流：
 * 从Reader和Writer派生出的一系列类，这类流以16位的Unicode码表示的字符为基本处理单位。
 * ◇ Reader、Writer
 * ◇ InputStreamReader、OutputStreamWriter
 * ◇ FileReader、FileWriter
 * ◇ CharArrayReader、CharArrayWriter
 * ◇ PipedReader、PipedWriter
 * ◇ FilterReader、FilterWriter
 * ◇ BufferedReader、BufferedWriter
 * ◇ StringReader、StringWriter
 **/
public class IoBase {

    public static void main(String[] args) {
        /*字节流读取*/
        //InputSteamReader();
        //Input_OutputStream();
        //BufferedInput_OutputStream();
        //InputStreamReader_OutputStreamWriter();
        //PrintStream();

        /*字符流读取*/
        //BufferedReader_Writer();
        //Byte_To_Buffer();
        PrintWriter();
    }

    /**
     *  字节流文件读写
     **/

    /**
     * 1、InputStream、OutputStream（字节流）
     */
    public static void Input_OutputStream() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {

            /**调用FileBase类的GetAbsolutePath（）方法*/
            String filepath = FileBase.GetAbsolutePath();
            System.out.println("filepath:" + filepath);
            File file = new File(filepath + File.separator + "kpy" + File.separator + "FileBase.java");
            /**每次重新运行时，删除之前已经存在的旧文件*/
            if (file.exists()) {
                file.delete();
            }

            /**读文件，将文件读到内存当中（字节流对象）*/
            inputStream = new FileInputStream("H:\\CodeTools\\IdeaProject\\FileIO\\src\\cn\\kpy\\FileIO\\FileBase.java");
            //写文件
            outputStream = new FileOutputStream(file);
            //读取数据,一次读取2KB大小的数据
            byte[] bytes = new byte[2048];
            //读取字节时，read方法返回值为int类型，该返回值主要用于判断是否读到文件尾
            int size;
            if (inputStream != null) {
                while ((size = inputStream.read(bytes, 0, bytes.length)) != -1) {
                    //调用String类的构造方法将字节流转化为字符串
                    String string = new String(bytes, 0, size);
                    System.out.println(string);
                    outputStream.write(bytes, 0, size);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 2、BufferedInputStream、BufferedInputStream（缓存字节流）
     */
    public static void BufferedInput_OutputStream() {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {

            /**调用FileBase类的GetAbsolutePath（）方法*/
            String filepath = FileBase.GetAbsolutePath();
            System.out.println("filepath:" + filepath);
            File file = new File(filepath + File.separator + "kpy" + File.separator + "FileBase01.java");
            /**每次重新运行时，删除之前已经存在的旧文件*/
            if (file.exists()) {
                file.delete();
            }

            /**将文件以缓存字节流的形式读到内存当中*/
            bufferedInputStream = new BufferedInputStream(new FileInputStream("H:\\CodeTools\\IdeaProject\\FileIO\\src\\cn\\kpy\\FileIO\\FileBase.java"));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            //读取数据,一次读取2KB大小的数据
            byte[] bytes = new byte[2048];
            int size;
            if (bufferedInputStream != null) {
                while ((size = bufferedInputStream.read(bytes, 0, bytes.length)) != -1) {
                    /*String string=new String(bytes,0,size);
                    System.out.println(string);*/
                    bufferedOutputStream.write(bytes, 0, size);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedInputStream.close();
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 3、InputStreamReader、OutputStreamWriter（字节流，不能指定字节长度写数据）
     */
    public static void InputStreamReader_OutputStreamWriter() {
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        try {

            /**调用FileBase类的GetAbsolutePath（）方法*/
            String filepath = FileBase.GetAbsolutePath();
            System.out.println("filepath:" + filepath);
            File file = new File(filepath + File.separator + "kpy" + File.separator + "FileBase02.java");
            /**每次重新运行时，删除之前已经存在的旧文件*/
            if (file.exists()) {
                file.delete();
            }

            FileInputStream fileInputStream = new FileInputStream("H:\\CodeTools\\IdeaProject\\FileIO\\src\\cn\\kpy\\FileIO\\FileBase.java");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            /*int size;
            while ((size = inputStreamReader.read()) != -1) {
                //System.out.println(size);
                outputStreamWriter.write(size);
            }*/

            //声明文件大小的字节数组
            byte[] bytes = new byte[fileInputStream.available()];
            int length;
            while ((length = fileInputStream.read(bytes, 0, bytes.length)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }

            /*System.out.println(length);
            for(int i=0;i<bytes.length;i++){
                System.out.println(bytes[i]);//因为java没有unsigned byte 类型，所以输出int类型
                System.out.println(Integer.toBinaryString(bytes[i]));//将int类型转化为byte字节类型
            }*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 4、PrintStream(字节流打印)
     */
    public static void PrintStream() {
        PrintStream printStream = null;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            /**调用FileBase类的GetAbsolutePath（）方法*/
            String filepath = FileBase.GetAbsolutePath();
            System.out.println("filepath:" + filepath);
            File file = new File(filepath + File.separator + "kpy" + File.separator + "FileBase03.java");
            /**每次重新运行时，删除之前已经存在的旧文件*/
            if (file.exists()) {
                file.delete();
            }
            /**调用读文件方法，将文件以字节流形式读入到内存当中*/
            fileInputStream = new FileInputStream("H:\\CodeTools\\IdeaProject\\FileIO\\src\\cn\\kpy\\FileIO\\FileBase.java");
            fileOutputStream = new FileOutputStream(file);

            /**创建字节流打印输出对象，准备向文件写入数据*/
            printStream = new PrintStream(fileOutputStream);
            try {

                //读取数据,一次读取2KB大小的数据
                byte[] bytes = new byte[2048];
                int size;
                while ((size = fileInputStream.read(bytes, 0, bytes.length)) != -1) {
                    /*String string=new String(bytes,0,size);
                    System.out.println(string);*/
                    printStream.write(bytes, 0, size);
                }

                /**
                 * 直接调用FileInputStream.read()方法出现乱码
                 * FileInputStream类read()方法每次读取文件都是按照1个字节读取的，而中文字符都是用两个字节表示的，输出时自然就乱码了。
                 * 另外，在Java语言中，中文和英文字符默认都被处理为unicode编码，unicode编码都是用两个字节表示一个字符，
                 * 既然中文和英文都是用2个字节表示一个字符，为什么英文字符输出没有问题呢？
                 * 原因是在unicode编码中，英文字符依然是ASCII编码，多出的一个字节值为0没有用到。*
                 *
                 * FileInputStream.read()返回一个unsigned byte [0 - 255],而java里面没有这个类型，所以用int接收。
                 * byte的范围是[-128,127]，所以如果read()返回的数在[128,255]的范围内时，则表示负数，即
                 * (byte)128=-128
                 * (byte)129=-127
                 * (byte)255=-1
                 * 所以如果read()返回的是byte的话，那就会有负数。而"返回-1意味着结束"，这个信息量用byte是无法表达的，所以必须用int
                 *
                 * FileInputStream.read()读取一个字节之后，指针移动到当前节点的尾部，下次执行read()方法时，从前一个节点尾部开始读取下一个字节
                 *
                 **/
                /*int temp;
                System.out.println((char)fileInputStream.read());
                while ((temp=fileInputStream.read()) != -1) {
                    System.out.print(temp);
                    printStream.write((char) temp);
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printStream.close();
            try {
                fileOutputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *5、InputStreamReader(字节流读取内存输入)
     */
    public static void InputSteamReader() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        try {
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     *  字符流文件读写
     **/

    /**
     * 1、BufferedReader、BufferedWriter（字符流）
     */
    public static void BufferedReader_Writer() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        String filepath = FileBase.GetAbsolutePath();
        System.out.println(filepath);
        File file = new File(filepath + File.separator + "kpy" + File.separator + "FileBase03.java");
        if (file.exists()) {
            file.delete();
        }

        try {
            FileReader fileReader = new FileReader("H:\\CodeTools\\IdeaProject\\FileIO\\src\\cn\\kpy\\FileIO\\FileBase.java");
            FileWriter fileWriter = new FileWriter(file);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                //System.out.println(string);
                //程序每次IO都要和设备进行通信，效率很低，因此缓冲区为了提高效率，当写入设备时，先写入缓冲区，等到缓冲区有足够多的数据时，就整体写入设备
                bufferedWriter.write(string);//将读取的数据放入缓冲区
                bufferedWriter.newLine();//换行
                bufferedWriter.flush();//刷新缓冲区，将缓冲区数据写入文件
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 2、InputStreamReader、OutputStreamWriter（字节流向字符流转化的桥梁）
     */
    public static void Byte_To_Buffer() {
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {

            /**调用FileBase类的GetAbsolutePath（）方法*/
            String filepath = FileBase.GetAbsolutePath();
            System.out.println("filepath:" + filepath);
            File file = new File(filepath + File.separator + "kpy" + File.separator + "FileBase04.java");
            /**每次重新运行时，删除之前已经存在的旧文件*/
            if (file.exists()) {
                file.delete();
            }
            FileInputStream fileInputStream = new FileInputStream("H:\\CodeTools\\IdeaProject\\FileIO\\src\\cn\\kpy\\FileIO\\FileBase.java");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 3、PrintWriter(字符流输出打印)
     * */
    public static void PrintWriter(){

        PrintWriter printWriter=null;

        String filepath = FileBase.GetAbsolutePath();
        System.out.println(filepath);
        File file = new File(filepath + File.separator + "kpy" + File.separator + "FileBase05.java");
        if (file.exists()) {
            file.delete();
        }

        try {
            FileReader fileReader = new FileReader("H:\\CodeTools\\IdeaProject\\FileIO\\src\\cn\\kpy\\FileIO\\FileBase.java");
            FileWriter fileWriter = new FileWriter(file);
            printWriter = new PrintWriter(fileWriter);
            int ch;
            while((ch=fileReader.read())!=-1){
                System.out.print((char)ch);
                printWriter.print((char)ch);
                printWriter.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            printWriter.close();
        }
    }
}

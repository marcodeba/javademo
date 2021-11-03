package com.demo.javademo.io.readwrite;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author：marco.pan
 * @ClassName：ImagesUtil
 * @Description：
 * @date: 2021年11月03日 2:50 下午
 */
public class ImagesUtil {
    public static final String PIC_PATH = "https://mmbiz.qpic.cn/mmbiz_png/UHKG18j8iasZm2XZtyI3tl6fVGBK6FoQupicB2zOv0D7aokicV7L1J63vSdXr5xbIGdwiaYTW2Eaqbkjy6IDzXGtLw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1";
    public static final String PIC_PATH2 = "https://mmbiz.qpic.cn/mmbiz_png/UHKG18j8iasZm2XZtyI3tl6fVGBK6FoQu8jvra9Q3mic9licwzpmqNjvYqeHJ1EdRJHzIqXQG8JEjuRK4wT9Ku5Qw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1";

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        download(PIC_PATH, "pic20211103.jpg");
        System.out.println("download复制文件所需的时间：" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        download2(PIC_PATH2, "pic20211104.jpg");
        System.out.println("download2复制文件所需的时间：" + (System.currentTimeMillis() - begin));
    }

    public static void download2(String urlString, String filename) throws Exception {
        //new一个URL对象
        URL url = new URL(urlString);
        //打开链接
        URLConnection conn = url.openConnection();
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        //File imageFile = new File(filename);
        //创建输出流
        //OutputStream outStream = response.getOutputStream();
        BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(filename));
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();
    }

    public static void download(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //超时响应时间为10秒
        con.setConnectTimeout(10 * 1000);
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        //OutputStream os =response.getOutputStream();
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(filename));
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}

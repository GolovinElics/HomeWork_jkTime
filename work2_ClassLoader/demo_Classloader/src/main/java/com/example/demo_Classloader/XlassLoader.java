package com.example.demo_Classloader;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author liangfeng
 * @version 1.0
 * @date 2021/11/7 19:55
 */
public class XlassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        final String classlo";
        final String methodName = "heName = "Helllo";

        ClassLoader classLoader = new XlassLoader();
        Class<?> clazz = classLoader.loadClass(className);

        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName() + "." + m.getName());
        }

        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String resourcePath = name.replace(".", "/");
        final String suffix = ".xlass";
        // 获取输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);
        try {
            assert inputStream != null;
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            // 转换
            byte[] classBytes = decode(byteArray);

            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
    }

    /**
     * 解码 (字节按（x=255-x）翻译)
     *
     * @param byteArray
     * @return
     */
    private byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

    /**
     * 关闭
     *
     * @param res
     */
    private static void close(Closeable res) {
        if (null != res) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

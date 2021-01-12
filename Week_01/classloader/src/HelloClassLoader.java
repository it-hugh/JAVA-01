import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 类加载练习
 */
public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args){
        Class c = new HelloClassLoader().findClass("Hello");
        try {
            Object obj = c.newInstance();
            Method method = c.getMethod("hello");
            method.invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name){
        String filePath = System.getProperty("user.dir")+"/ext/Hello.xlass";
        //读取文件到byte[]
        byte[] bytes = getBytes(filePath);
        return defineClass(name,bytes,0,bytes.length);
    }

    /**
     * 读取文件到byte[]
     *
     * @return
     */
    private static byte[] getBytes(String filePath) {
        File file = new File(filePath);
        try {
            int length = (int) file.length();
            byte[] fileBytes = new byte[length];
            FileInputStream fs = new FileInputStream(file);
            fs.read(fileBytes);
            for(int i=0;i<fileBytes.length;i++) {
                fileBytes[i] = (byte)(255-fileBytes[i]);
            }
            fs.close();
            return fileBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

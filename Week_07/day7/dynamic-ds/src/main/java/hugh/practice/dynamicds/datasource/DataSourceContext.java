package hugh.practice.dynamicds.datasource;


import java.util.ArrayList;
import java.util.List;

public class DataSourceContext {

    private static final List<String> dsList = new ArrayList<String>(){{
        add("master");
        add("slave");
    }};
    private static final ThreadLocal<String> container = new ThreadLocal<>();

    public static void setDataSource(String dsName){
        System.out.println("设置数据源："+dsName);
        container.set(dsName);
    }

    public static String getDataSource(){
        System.out.println("获取数据源："+container.get());
        return container.get();
    }

    public static void clearDataSource(){
        container.remove();
    }

    public static String randomDataSource() {
        int random = (int)(0+Math.random()*(dsList.size()));
        return dsList.get(random);
    }
}

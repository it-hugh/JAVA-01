package hugh.practice.dynamicds.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DataSourceContext.getDataSource();
        System.out.println("determineCurrentLookupKey ===> "+dataSource);
        return dataSource;
    }
}

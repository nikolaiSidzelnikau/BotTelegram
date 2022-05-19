package database;

import bot.DESDecrypter;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class PoolDateBase  {
    DESDecrypter desDecrypter = new DESDecrypter();
    PoolProperties p;
    public PoolProperties getP() {
        p = new PoolProperties();
        p.setUrl(desDecrypter.decrypt("Thikdkfejfn;laenfjlkijesabflAL",
                "DuMrQ53Z6MsG24jz2piuq1jGhvsydLWku6ktPjYTL7IvjHti1OCVM86YOj6KXcfZOPQbwX2klEM="));
        p.setDriverClassName(desDecrypter.decrypt("Thikdkfejfn;laenfjlkijesabflAL",
                "7PQ3+COaBHRZDKSXfAXVaJvMZ1AqtZK8"));
        p.setUsername(desDecrypter.decrypt("Thikdkfejfn;laenfjlkijesabflAL",
                "IArBC37Udn4peBcTwkwMKQ=="));
        p.setPassword(desDecrypter.decrypt("Thikdkfejfn;laenfjlkijesabflAL",
               "zZCpDTGm0vJS4YLwIaIfLw=="));
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(1);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        return p;
    }
}

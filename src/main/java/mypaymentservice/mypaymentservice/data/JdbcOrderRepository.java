package mypaymentservice.mypaymentservice.data;

import jakarta.annotation.PostConstruct;
import mypaymentservice.mypaymentservice.order.Order;
import mypaymentservice.mypaymentservice.order.OrderRepository;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

public class JdbcOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    void initDb() {
        jdbcClient.sql("""
                    create table if not exists orders (
                        id bigint not null, 
                        no varchar(255), 
                        total numeric(38,2), 
                        primary key (id)
                    );
                    alter table orders drop constraint if exists UK43egxxciqr9ncgmxbdx2avi8n;
                    alter table orders add constraint UK43egxxciqr9ncgmxbdx2avi8n unique (no);
                    create sequence if not exists orders_SEQ start with 1 increment by 50;
                """).update();

    }

    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select  next value  for orders_SEQ").query(Long.class).single();
        order.setId(id);

        jdbcClient.sql("INSERT INTO orders (no, total, id) values (?, ?, ?)")
                .params(order.getNo(), order.getTotal(), order.getId()).update();

    }
}

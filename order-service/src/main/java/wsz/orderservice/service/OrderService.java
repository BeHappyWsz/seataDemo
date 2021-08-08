package wsz.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsz.orderservice.entity.Order;
import wsz.orderservice.fegin.AccountFeignClient;
import wsz.orderservice.repository.OrderDAO;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class OrderService {
    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public void create(String userId, String commodityCode, Integer count) {

        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));

        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);

        orderDAO.save(order);

        accountFeignClient.debit(userId, orderMoney);

    }
}

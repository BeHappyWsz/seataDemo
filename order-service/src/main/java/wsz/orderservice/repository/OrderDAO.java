package wsz.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wsz.orderservice.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long> {
}

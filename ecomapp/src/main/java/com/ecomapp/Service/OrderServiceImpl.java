package com.ecomapp.Service;

import com.ecomapp.DTOs.OrderItemDTO;
import com.ecomapp.DTOs.OrderResponse;
import com.ecomapp.Enum.OrderStatus;
import com.ecomapp.Model.CartItem;
import com.ecomapp.Model.Order;
import com.ecomapp.Model.OrderItem;
import com.ecomapp.Model.User;
import com.ecomapp.Repository.CartRepository;
import com.ecomapp.Repository.OrderRepository;
import com.ecomapp.Repository.USerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final USerRepository userRepository;

    @Override
    public OrderResponse createOrder(String userId) {
        List<CartItem> cartItems= cartService.getCart(userId);
        if(cartItems.isEmpty()){

        }
       Optional<User> user=userRepository.findById(Long.valueOf(userId));
        if(user.isEmpty()){
            throw new RuntimeException("User not found with id " + userId);
        }
        BigDecimal totalAmount= cartItems.stream()
                .map(cartItem->cartItem.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order=new Order();
        order.setUser(user.get());
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.CONFIRMED);
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> {
                    OrderItem oi = new OrderItem();
                    oi.setProduct(item.getProduct());
                    oi.setQuantity(item.getQuantity());
                    oi.setPrice(item.getPrice());
                    oi.setOrder(order);
                    oi.setCreatedAt(item.getCreatedAt());
                    oi.setUpdatedAt(item.getUpdatedAt());
                    return oi;
                })
                .toList();



        order.setOrderItems(orderItems);
        Order save=orderRepository.save(order);

        cartService.clearCart(userId);


        return orderResponse(save);
    }

    private OrderResponse orderResponse(Order save) {
        return new OrderResponse(
                save.getId(),
                save.getTotalAmount(),
                save.getStatus(),
                save.getOrderItems()
                        .stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getId(),                                   // id
                                orderItem.getQuantity(),                             // quantity
                                orderItem.getPrice(),                                // price
                                orderItem.getProduct().getId(),                      // productId
                                orderItem.getPrice()
                                        .multiply(BigDecimal.valueOf(orderItem.getQuantity())) // subtotal
                        ))
                        .toList(),
                save.getCreatedAt()
        );
    }



    }



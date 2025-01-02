package xyz.ruhshan.redis_pubsub.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEvent implements Serializable {
    private int orderId;
    private int userId;
    private String productName;
    private int price;
    private int quantity;
}

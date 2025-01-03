package xyz.ruhshan.redis_pubsub.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent implements Serializable {
    private int paymentId;
    private String paymentMethod;
    private String amount;
    private String orderId;
}

package com.example.notification_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    BigDecimal currentPrice;
    BigDecimal volume;
    BigDecimal high;
    BigDecimal marketCap;
}

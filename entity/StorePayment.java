package kr.co.maptics.mapticslogin.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Data
@Entity
@Accessors(chain = true)
@NoArgsConstructor
public class StorePayment{

    @Id
    private int StorePaymentSeq;

    private int StoreSeq;

    private Date StartDate;
    private Date EndDate;

}

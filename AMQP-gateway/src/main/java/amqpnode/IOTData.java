package amqpnode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IOTData {
    private String id;
    private Date iotDate;
    private long unixTime;
    private float watValue;

    private int workLoad;
    private int plugId;
    private int houseHoldId;
    private int houseId;
}

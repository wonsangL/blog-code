import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction{
    private int id;
    private TransactionType type;
    private Integer value;
}

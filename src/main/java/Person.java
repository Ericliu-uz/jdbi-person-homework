import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {

    public enum Gender {
        FEMALE, MALE;
    }

    private int id;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String email;
    private String phone;
    private String profession;
    private boolean married;

}

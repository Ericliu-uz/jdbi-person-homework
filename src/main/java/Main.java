import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import com.github.javafaker.Faker;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test_person");
        jdbi.installPlugin(new SqlObjectPlugin());

        try (Handle handle = jdbi.open()) {
            PersonDao personDao = handle.attach((PersonDao.class));
            personDao.createTable();

            int num;
            num = Integer.parseInt(args[0]);

            var faker = new Faker();
            for (int i = 0; i < num; i++) {

                Person personInfo = fakePerson(i);
                personDao.insert(personInfo.getId(), personInfo.getName(), personInfo.getBirthDate(), personInfo.getGender(),
                        personInfo.getEmail(), personInfo.getPhone(), personInfo.getProfession(), personInfo.isMarried());
            }

            personDao.find(0).get();
            personDao.findAll().forEach(System.out::println);
            personDao.delete(1);
            personDao.findAll().forEach(System.out::println);
        }
    }

    public static Person fakePerson(int num) {
        var faker = new Faker();
        Date date;
        date = faker.date().birthday();
        var personInfo = Person.builder()
                .id(num)
                .name(faker.name().fullName())
                .birthDate(LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()))
                .gender(faker.options().option(Person.Gender.values()))
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .profession(faker.company().profession())
                .married(faker.bool().bool())
                .build();
        return personInfo;
    }

}

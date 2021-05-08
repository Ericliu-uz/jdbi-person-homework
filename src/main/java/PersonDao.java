import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Person.class)
public interface PersonDao {
    @SqlUpdate("""
        CREATE TABLE person(
            id INT PRIMARY KEY,
            name VARCHAR,
            birthDate DATE,
            gender VARCHAR,
            email VARCHAR,
            phone VARCHAR,
            profession VARCHAR,
            married BOOLEAN
        )
    """)
    void createTable();

    @SqlUpdate("INSERT INTO person VALUES (:id,:name,:birthDate,:gender,:email,:phone,:profession,:married)")
    void insert(@Bind("id") int id, @Bind("name") String name, @Bind("birthDate") LocalDate birthDate, @Bind("gender") Person.Gender gender, @Bind("email") String email, @Bind("phone") String phone, @Bind("profession") String profession, @Bind("married") boolean married);

    @SqlQuery("SELECT * FROM person WHERE id =:id")
    Optional<Person> find(@Bind("id") int id);

    @SqlUpdate("DELETE FROM person WHERE id =:id")
    void delete(@Bind("id") int id);

    @SqlQuery("SELECT * FROM person ORDER BY id")
    List<Person> findAll();


}

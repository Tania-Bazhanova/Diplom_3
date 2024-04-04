package stepsanddata;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class FakerDataForTests {
    Faker faker = new Faker(Locale.ENGLISH);

    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password(9, 12);
    private final String name = faker.name().firstName();
}

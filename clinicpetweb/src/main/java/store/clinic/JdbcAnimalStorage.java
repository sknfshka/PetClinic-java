package store.clinic;

import models.Animal;
import service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Client on 27.08.2017.
 */
public class JdbcAnimalStorage implements Storage<Animal> {
    private final Connection connection;

    public JdbcAnimalStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Collection<Animal> values() {
        final List<Animal> animals = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
            final ResultSet rs = statement.executeQuery("select * from pet as pet")) {
            while (rs.next()) {
                animals.add(getAnimal(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animals;
    }
    @Override
    public int add(Animal animal) {
        try (final PreparedStatement statement = this.connection.prepareStatement("insert into pet (name, kind, age, client_id) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, animal.getName());
            statement.setString(2, animal.getKind().toString());
            statement.setInt(3, animal.getAge());
            statement.setInt(4, animal.getClientId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public void edit(Animal animal) {
        try (final PreparedStatement statement = this.connection.prepareStatement("update pet set name = (?), kind = (?) , age = (?), client_id = (?) where pet.uid = (?)")) {
            statement.setString(1, animal.getName());
            statement.setString(2, animal.getKind().toString());
            statement.setInt(3, animal.getAge());
            statement.setInt(4, animal.getClientId());
            statement.setInt(5, animal.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Animal animal) throws IllegalStateException{
        if(animal == null) {
            throw new IllegalStateException();
        }

        try (final PreparedStatement statement = this.connection.prepareStatement("delete from pet as pet where pet.uid = (?)")) {
            statement.setInt(1, animal.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Animal get(int id) throws IllegalStateException{
        try (final PreparedStatement statement = this.connection.prepareStatement("select * from pet as pet where pet.uid = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return getAnimal(rs);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(String.format("Animal %s does not exists", id));
        }

        return null;
    }

    private Animal getAnimal(ResultSet rs) throws SQLException {
        Animal pet = null;

        String kind = rs.getString("kind");
        Animal.Kind animalKind = null;

        if(kind != null) {
            switch (kind.toUpperCase()) {
                case "DOG": {
                    animalKind = Animal.Kind.DOG;
                    break;
                }
                case "CAT" : {
                    animalKind = Animal.Kind.CAT;
                    break;
                }
            }

            pet = new Animal(rs.getInt("uid"), rs.getString("name"), rs.getInt("age"), animalKind, rs.getInt("client_id"));
        }

        return pet;
    }

    @Override
    public Collection<Animal> findByName(String name){
        final List<Animal> animals = new ArrayList<>();

        try (final PreparedStatement statement = this.connection.prepareStatement("select * from pet as pet where pet.name = (?)")) {
            statement.setString(1, name);

            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    animals.add(getAnimal(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animals;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

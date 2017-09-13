package ru.achrom.store;

import org.springframework.stereotype.Repository;
import ru.achrom.models.Animal;
import ru.achrom.models.Client;
import ru.achrom.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class JdbcClientStorage implements Storage<Client> {
    private final Connection connection;

    public JdbcClientStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Collection<Client> values() {
        final List<Client> clients = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
            final ResultSet rs = statement.executeQuery("select client.uid, client.name from client")) {
            while (rs.next()) {
                clients.add( new Client(rs.getInt("uid"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public int add(Client client) throws IllegalStateException, IllegalArgumentException{
        if(client == null) {
            throw new IllegalArgumentException();
        }

        try (final PreparedStatement statement = this.connection.prepareStatement("insert into client (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    @Override
    public void edit(Client client) throws IllegalArgumentException{
        if(client == null) {
            throw new IllegalArgumentException();
        }

        try (final PreparedStatement statement = this.connection.prepareStatement("update client set name = (?) where client.uid = (?)")) {
            statement.setString(1, client.getName());
            statement.setInt(2, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client client) throws IllegalArgumentException{
        if(client == null) {
            throw new IllegalArgumentException();
        }

        Collection<Animal> animals = AnimalCache.getInstance().findUserAnimals(client.getId());

        for (Animal animal: animals) {
            AnimalCache.getInstance().delete(animal);
        }

        try (final PreparedStatement statement = this.connection.prepareStatement("delete from client as client where client.uid = (?)")) {
            statement.setInt(1, client.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Client get(int id) throws IllegalStateException{
        try (final PreparedStatement statement = this.connection.prepareStatement("select client.uid as uid, pet.uid as petuid, client.name as name, pet.name as petname, pet.age as age, pet.kind as kind from client as client left join pet as pet on pet.client_id = client.uid where client.uid = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    return new Client(rs.getInt("uid"), rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(String.format("Client %s does not exists", id));
        }

        return null;
    }

    @Override
    public Collection<Client> findByName(String name){
        final List<Client> clients = new ArrayList<>();

        try (final PreparedStatement statement = this.connection.prepareStatement("select client.uid as uid, client.name as name from client where client.name = (?)")) {
            statement.setString(1, name);

            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    clients.add( new Client(rs.getInt("uid"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
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

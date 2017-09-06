package store.clinic;

import models.Animal;
import models.Client;
import service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Client on 27.08.2017.
 */
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
            final ResultSet rs = statement.executeQuery("select client.uid as uid, pet.uid as petuid, client.name as name, pet.name as petname, pet.age as age, pet.kind as kind from client as client left join pet as pet on pet.client_id = client.uid")) {
            collectClientAndHisAnimals(clients, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    private int addClient(Client client) {
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

        return -1;
    }

    private void addAnimalsToUser(int userId, List<Animal> animals) {
        for (Animal animal: animals) {
            try (final PreparedStatement statement = this.connection.prepareStatement("insert into pet (name, kind, age, client_id) values (?, ?, ?, ?)")) {
                statement.setString(1, animal.getName());
                statement.setString(2, animal.getKind().toString());
                statement.setInt(3, animal.getAge());
                statement.setInt(4, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public int add(Client client) {
        int createdClientId = addClient(client);

        if (createdClientId != -1) {
            addAnimalsToUser(createdClientId, client.getAnimals());
        } else {
            throw new IllegalStateException("Could not create new user");
        }

        return createdClientId;
    }

    @Override
    public void edit(Client client) {
        try (final PreparedStatement statement = this.connection.prepareStatement("update client set name = (?) where client.uid = (?)")) {
            statement.setString(1, client.getName());
            statement.setInt(2, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client client) throws IllegalStateException{
        if(client == null) {
            throw new IllegalStateException();
        }

        for (Animal animal: client.getAnimals()) {
            try (final PreparedStatement statement = this.connection.prepareStatement("delete from pet as pet where pet.uid = (?)")) {
                statement.setInt(1, animal.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
                Client currentClient = null;

                while (rs.next()) {
                    if (currentClient == null) {
                        currentClient = new Client(rs.getInt("uid"), rs.getString("name"));
                    }

                    Animal animal = getAnimal(rs);
                    currentClient.addAnimal(animal);
                }

                return currentClient;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(String.format("Client %s does not exists", id));
        }
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

            pet = new Animal(rs.getInt("petuid"), rs.getString("petname"), rs.getInt("age"), animalKind, rs.getInt("uid"));
        }

        return pet;
    }

    @Override
    public Collection<Client> findByName(String name){
        final List<Client> clients = new ArrayList<>();

        try (final PreparedStatement statement = this.connection.prepareStatement("select client.uid as uid, pet.uid as petuid, client.name as name, pet.name as petname, pet.age as age, pet.kind as kind from client as client left join pet as pet on pet.client_id = client.uid where client.name = (?)")) {
            statement.setString(1, name);

            try (final ResultSet rs = statement.executeQuery()) {
                collectClientAndHisAnimals(clients, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    private void collectClientAndHisAnimals(List<Client> clients, ResultSet rs) throws SQLException {
        int currentClientUid = -1;
        Client currentClient = null;

        while (rs.next()) {
            Animal animal = getAnimal(rs);

            if(currentClientUid != rs.getInt("uid")) {
                if(currentClientUid != -1) {
                    clients.add(currentClient);
                }

                currentClient = new Client(rs.getInt("uid"), rs.getString("name"));
                currentClient.addAnimal(animal);
                currentClientUid = rs.getInt("uid");
            } else {
                currentClient.addAnimal(animal);
            }
        }

        if (currentClient != null)
            clients.add(currentClient);
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

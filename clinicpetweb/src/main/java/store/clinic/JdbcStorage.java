package store.clinic;

import lessons.lesson_6.*;
import service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Client on 27.08.2017.
 */
public class JdbcStorage implements ClinicStorageInterface {
    private final Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Collection<Client> values() {
        final List<Client> users = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select client.uid as uid, pet.uid as petuid, client.name as name, pet.name as petname, pet.age as age, pet.kind as kind from client as client left join pet as pet on pet.uid = client.pet_id")) {
            while (rs.next()) {
                Pet pet = getPet(rs);

                users.add(new Client(rs.getInt("uid"), rs.getString("name"), pet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int add(Client user) {
        try (final PreparedStatement statement = this.connection.prepareStatement("insert into pet (name, kind, age) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getPetName());
            statement.setString(2, user.getPetKind());
            statement.setInt(3, 0);

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int petUid = generatedKeys.getInt(1);

                    try (final PreparedStatement clientStatement = this.connection.prepareStatement("insert into client (name, pet_id) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                        clientStatement.setString(1, user.getName());
                        clientStatement.setInt(2, petUid);
                        clientStatement.executeUpdate();
                        try (ResultSet generatedClientKeys = clientStatement.getGeneratedKeys()) {
                            if (generatedClientKeys.next()) {
                                return generatedClientKeys.getInt(1);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException("Could not create new user");
    }

    @Override
    public void edit(Client client) {
        delete(client.getId());
        add(client);
    }

    @Override
    public void delete(int id) throws IllegalStateException{
        Client client = get(id);

        try (final PreparedStatement statement = this.connection.prepareStatement("delete from client as client where client.uid = (?)")) {
            statement.setInt(1, client.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(client.getPet() != null)
            try (final PreparedStatement statement = this.connection.prepareStatement("delete from pet as pet where pet.uid = (?)")) {
                statement.setInt(1, Integer.parseInt(client.getPet().getId()));
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    @Override
    public Client get(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement("select client.uid as uid, pet.uid as petuid, client.name as name, pet.name as petname, pet.age as age, pet.kind as kind from client as client left join pet as pet on pet.uid = client.pet_id where client.uid = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Pet pet = getPet(rs);

                    return new Client(rs.getInt("uid"), rs.getString("name"), pet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Client %s does not exists", id));
    }

    private Pet getPet(ResultSet rs) throws SQLException {
        Pet pet = null;

        String kind = rs.getString("kind");
        if(kind != null) {
            switch (kind) {
                case "Dog": {
                    pet = new Dog(rs.getString("petuid"), rs.getString("petname"), rs.getInt("age"));
                    break;
                }
                case "Cat" : {
                    pet = new Cat(rs.getString("petuid"), rs.getString("petname"), rs.getInt("age"));
                    break;
                }
            }
        }
        return pet;
    }

    @Override
    public Collection<Client> findByName(String name) throws WrongInputDataException {
        return null;
    }

    @Override
    public Collection<Client> findByPetName(String petName) throws WrongInputDataException {
        return null;
    }

    @Override
    public int generateId() {
        return 0;
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

package com.barber.admin.repository.user;

import com.barber.admin.dto.user.UserFilterDTO;
import com.barber.admin.entity.user.UserEntity;
import com.barber.admin.enums.user.UserRole;
import com.barber.admin.interfaces.user.IUserRepository;
import com.barber.admin.presenters.user.UserPresenter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {

    public UserPresenter saveUser( Connection connection, UserEntity user) throws SQLException {

        String sql = "INSERT INTO users (id, name, email, role, password, created_at, active, deleted_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole().toString());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setBoolean(7, user.getActive());
            preparedStatement.setTimestamp(8, null);
            preparedStatement.setTimestamp(9, null);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar usuário", e);
        }


        return new UserPresenter(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getActive(),
                new Date(),
                null,
                null
        );
    }

    public List<UserPresenter> getPaginatedUsers(Connection connection, UserFilterDTO userFilter) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT id, name, email, role, active, created_at, deleted_at, updated_at FROM users WHERE deleted_at IS NULL");

        if (userFilter.getEmail() != null && !userFilter.getEmail().isEmpty()) {
            sql.append(" AND email LIKE ?");
        }
        if (userFilter.getActive() != null) {
            sql.append(" AND active = ?");
        }

        sql.append(" ORDER BY created_at DESC");

        sql.append(" LIMIT ? OFFSET ?");

        List<UserPresenter> users = new ArrayList<>();
        int offset = (userFilter.getPage() - 1) * userFilter.getPageSize();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
            int index = 1;

            if (userFilter.getEmail() != null && !userFilter.getEmail().isEmpty()) {
                preparedStatement.setString(index++, "%" + userFilter.getEmail() + "%");
            }
            if (userFilter.getActive() != null) {
                preparedStatement.setBoolean(index++, userFilter.getActive());
            }

            preparedStatement.setInt(index++, userFilter.getPageSize());
            preparedStatement.setInt(index, offset);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(new UserPresenter(
                            UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            UserRole.fromDisplayName(resultSet.getString("role")),
                            resultSet.getBoolean("active"),
                            resultSet.getTimestamp("created_at"),
                            resultSet.getTimestamp("deleted_at"),
                            resultSet.getTimestamp("updated_at")
                    ));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao buscar usuários", e);
            }
        }

        return users;
    }


    public UserPresenter findUserByEmail(Connection connection, String email) throws SQLException {
        String sql = "SELECT id, name, email, role, active, created_at, deleted_at, updated_at " +
                "FROM users WHERE email = ? AND deleted_at IS NULL";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserPresenter(
                            UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            UserRole.fromDisplayName(resultSet.getString("role")),
                            resultSet.getBoolean("active"),
                            resultSet.getTimestamp("created_at"),
                            resultSet.getTimestamp("deleted_at"),
                            resultSet.getTimestamp("updated_at")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}

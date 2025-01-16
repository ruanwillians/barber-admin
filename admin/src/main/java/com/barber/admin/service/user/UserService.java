package com.barber.admin.service.user;

import com.barber.admin.dto.user.UserDTO;
import com.barber.admin.dto.user.UserFilterDTO;
import com.barber.admin.entity.user.UserEntity;
import com.barber.admin.enums.database.DatabaseName;
import com.barber.admin.factory.user.UserFactory;
import com.barber.admin.interfaces.user.IUserService;
import com.barber.admin.presenters.user.UserPresenter;
import com.barber.admin.config.DatabaseConnectionManager;
import com.barber.admin.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final DatabaseConnectionManager databaseConnectionManager;

    @Autowired
    public UserService(UserRepository userRepository, DatabaseConnectionManager databaseConnectionManager) {
        this.userRepository = userRepository;
        this.databaseConnectionManager = databaseConnectionManager;
    }

    public UserPresenter createUser(UserDTO user) {
        try {
            DataSource dataSource = databaseConnectionManager.createDataSource(DatabaseName.ADMIN_DB.getDbName());
            Connection connection = dataSource.getConnection();

            UserEntity userEntity = UserFactory.createUser(user);

            UserPresenter userExist = userRepository.findUserByEmail(connection, userEntity.getEmail());

            if(userExist != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário com o email: " + userEntity.getEmail() +  "já existe");
            }

            return userRepository.saveUser(connection,userEntity);


        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o usuário", e);
        }
    }

    public List<UserPresenter> getAllUsers(UserFilterDTO userFilter) {
        try {
            DataSource dataSource = databaseConnectionManager.createDataSource(DatabaseName.ADMIN_DB.getDbName());
            Connection connection = dataSource.getConnection();

            return userRepository.getPaginatedUsers(connection, userFilter);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

package org.sm.entitygraph.service;

import com.cosium.spring.data.jpa.entity.graph.domain2.NamedEntityGraph;
import lombok.RequiredArgsConstructor;
import org.sm.entitygraph.constants.EntityGraphNames;
import org.sm.entitygraph.mapper.UserMapper;
import org.sm.entitygraph.model.User;
import org.sm.entitygraph.repository.UserRepository;
import org.sm.entitygraph.repository.entity.UserEntity;
import org.sm.entitygraph.repository.spec.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static Specification<UserEntity> getUserSpecification(Set<String> classNames, int rollNumber) {
        Specification<UserEntity> specification = where(UserSpecification.isRollNumber(rollNumber));
        specification = specification.and(UserSpecification.inClassNames(classNames));
        return specification;
    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.mapUserEntitiesToUsers(userEntities);
    }

    @Override
    public User getUserById(int userId) {
        UserEntity userEntity = userRepository.findById(userId);
        return userMapper.mapUserEntityToUser(userEntity);
    }

    @Override
    public List<User> getUserByClassName(String className) {
        List<UserEntity> userEntities = userRepository.findAllByClassName(className, NamedEntityGraph.fetching(EntityGraphNames.USER_STATUS));
        return userMapper.mapUserEntitiesToUsers(userEntities);
    }

    @Override
    public List<User> getUserByRollNumberAndClassName(Set<String> classNames, int rollNumber) {
        List<UserEntity> userEntities = userRepository.findAll(getUserSpecification(classNames, rollNumber), NamedEntityGraph.fetching(EntityGraphNames.USER_STATUS));
        return userMapper.mapUserEntitiesToUsers(userEntities);
    }
}

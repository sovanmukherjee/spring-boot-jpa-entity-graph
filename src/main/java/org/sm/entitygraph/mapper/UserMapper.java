package org.sm.entitygraph.mapper;

import org.sm.entitygraph.repository.entity.AddressEntity;
import org.sm.entitygraph.repository.entity.StatusEntity;
import org.sm.entitygraph.repository.entity.SubStatusEntity;
import org.sm.entitygraph.repository.entity.UserEntity;
import org.sm.entitygraph.model.Address;
import org.sm.entitygraph.model.Status;
import org.sm.entitygraph.model.SubStatus;
import org.sm.entitygraph.model.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Component
public class UserMapper {
    public List<User> mapUserEntitiesToUsers(final List<UserEntity> userEntities) {
        List<User> users = new ArrayList<>();
        Optional.ofNullable(userEntities).orElse(Collections.emptyList()).forEach(userEntity-> users.add(User.builder().id(userEntity.getId())
                        .name(userEntity.getName())
                        .roll(userEntity.getRoll())
                        .status(getStatuses(userEntity.getStatus()))
                        .address(getAddress(userEntity.getAddress()))
                .build()));
        return users;
    }

    private Address getAddress(AddressEntity address) {
        if(Objects.nonNull(address)){
            return Address.builder().id(address.getId()).city(address.getCity()).state(address.getState()).pin(address.getPin()).build();
        }
        return Address.builder().build();
    }

    private List<Status> getStatuses(List<StatusEntity> statusEntities) {
        List<Status> statuses = new ArrayList<>();
        Optional.ofNullable(statusEntities).orElse(Collections.emptyList()).forEach(status-> statuses.add(Status.builder().id(status.getId())
                        .code(status.getCode())
                        .desc(status.getDesc())
                        .subStatuses(getSubStatuses(status.getSubStatuses()))
                .build()));
        return statuses;
    }

    private List<SubStatus> getSubStatuses(Set<SubStatusEntity> subStatusEntities) {
        List<SubStatus> subStatuses = new ArrayList<>();
        Optional.ofNullable(subStatusEntities).orElse(Collections.emptySet()).forEach(subStatus-> subStatuses.add(SubStatus.builder().id(subStatus.getId()).code(subStatus.getCode()).desc(subStatus.getDesc()).build()));
       return subStatuses;
    }

    public User mapUserEntityToUser(UserEntity userEntity) {
        return User.builder().id(userEntity.getId())
                .name(userEntity.getName())
                .roll(userEntity.getRoll())
                .address(getAddress(userEntity.getAddress()))
                .build();
    }
}

package org.sm.entitygraph.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.NamedEntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.sm.entitygraph.constants.EntityGraphNames;
import org.sm.entitygraph.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends EntityGraphJpaRepository<UserEntity, Integer>,
        EntityGraphJpaSpecificationExecutor<UserEntity> {

    @EntityGraph(value = EntityGraphNames.USER_DETAILS, type = EntityGraph.EntityGraphType.FETCH)
    UserEntity findById(int id);

    @EntityGraph(value = EntityGraphNames.USER_STATUS, type = EntityGraph.EntityGraphType.FETCH)
    List<UserEntity> findAll();

    List<UserEntity> findAllByClassName(String className, NamedEntityGraph fetching);
}

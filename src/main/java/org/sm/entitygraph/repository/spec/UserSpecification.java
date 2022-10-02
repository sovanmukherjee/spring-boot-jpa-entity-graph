package org.sm.entitygraph.repository.spec;

import org.sm.entitygraph.repository.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

public class UserSpecification {

    private static final String ROLL = "roll";
    private static final String CLASS_NAME = "className";

    private UserSpecification() {
    }

    public static Specification<UserEntity> isRollNumber(int rollNumber) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(ROLL), rollNumber);
    }

    public static Specification<UserEntity> inClassNames(Set<String> classNames) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaBuilder.In<String> inClassNames = criteriaBuilder.in(root.get(CLASS_NAME));
            classNames.forEach(inClassNames::value);
            return inClassNames;
        };
    }
}

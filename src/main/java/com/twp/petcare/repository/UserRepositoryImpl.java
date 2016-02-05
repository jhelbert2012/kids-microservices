package com.twp.petcare.repository;

import com.twp.petcare.bean.Card;
import com.twp.petcare.bean.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@RequiredArgsConstructor(onConstructor = @__(
        @Autowired))
class UserRepositoryImpl implements UserRepositoryCustom {

    private final MongoOperations operations;

    public User getUserFor(Card card) {
        AggregationResults<User> results = operations.aggregate(newAggregation(Card.class, //
                match(where("id").is(card.getId()))//
        ), User.class);

        return results.getUniqueMappedResult();
    }
}

package com.twp.petcare.repository;

import com.twp.petcare.bean.Card;
import com.twp.petcare.bean.User;

public interface UserRepositoryCustom {
    User getUserFor(Card card);
}

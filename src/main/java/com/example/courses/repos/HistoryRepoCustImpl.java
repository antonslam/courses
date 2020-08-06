package com.example.courses.repos;

import com.example.courses.entities.History;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepoCustImpl implements HistoryRepoCust {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<History> findByFilter(String date, Long first_Currency, Long second_Currency) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<History> query = cb.createQuery(History.class);
        Root<History> historyRoot = query.from(History.class);

        Path<String> Path_date = historyRoot.get("date");
        Path<String> Path_first_Currency = historyRoot.get("first_Currency");
        Path<String> Path_second_Currency = historyRoot.get("second_Currency");

        List<Predicate> predicates = new ArrayList<>();

        if (date != null && !date.isEmpty()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);

            predicates.add(cb.equal(Path_date, localDate));
        }
        if (first_Currency != null){
            predicates.add(cb.equal(Path_first_Currency, first_Currency));
        }
        if (second_Currency != null){
            predicates.add(cb.equal(Path_second_Currency, second_Currency));
        }

        query.select(historyRoot)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}

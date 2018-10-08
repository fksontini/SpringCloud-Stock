package tn.fiksit.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.fiksit.stock.dbservice.model.Quote;

public interface QuotesRepository extends JpaRepository<Quote,Integer > {
List<Quote> findByUserName(String username);
}

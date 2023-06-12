package com.supauction.auction_supmti.Repository;

import com.supauction.auction_supmti.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid,Integer> {
}

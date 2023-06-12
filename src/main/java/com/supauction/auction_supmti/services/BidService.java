package com.supauction.auction_supmti.services;

import com.supauction.auction_supmti.model.Bid;
import com.supauction.auction_supmti.Repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {

    private final BidRepository bidRepository;

    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Bid getBidById(Integer id) {
        return bidRepository.findById(id).orElse(null);
    }

    public Bid createBid(Bid bid) {
        return bidRepository.save(bid);
    }

    public Bid updateBid(Integer id, Bid updatedBid) {
        Bid existingBid = getBidById(id);
        if (existingBid != null) {
            // Update the existing bid with the new data
            existingBid.setAmount(updatedBid.getAmount());
            // Update other fields as needed

            return bidRepository.save(existingBid);
        }
        return null;
    }

    public boolean deleteBid(Integer id) {
        Bid existingBid = getBidById(id);
        if (existingBid != null) {
            bidRepository.delete(existingBid);
            return true;
        }
        return false;
    }
}
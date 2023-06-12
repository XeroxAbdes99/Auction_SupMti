package com.supauction.auction_supmti.controllers;

import com.supauction.auction_supmti.model.Bid;
import com.supauction.auction_supmti.services.BidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping
    public ResponseEntity<List<Bid>> getAllBids() {
        List<Bid> bids = bidService.getAllBids();
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable("id") Integer id) {
        Bid bid = bidService.getBidById(id);
        if (bid != null) {
            return new ResponseEntity<>(bid, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Bid> createBid(@RequestBody Bid bid) {
        Bid createdBid = bidService.createBid(bid);
        return new ResponseEntity<>(createdBid, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bid> updateBid(@PathVariable("id") Integer id, @RequestBody Bid updatedBid) {
        Bid bid = bidService.updateBid(id, updatedBid);
        if (bid != null) {
            return new ResponseEntity<>(bid, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")//endpoint
    public ResponseEntity<Void> deleteBid(@PathVariable("id") Integer id) {
        boolean deleted = bidService.deleteBid(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
package com.supauction.auction_supmti.services;

import com.supauction.auction_supmti.Repository.ItemRepository;
import com.supauction.auction_supmti.model.Bid;
import com.supauction.auction_supmti.model.Item;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item updatedItem) {
        Item existingItem = getItemById(id);
        if (existingItem != null) {
            // Update the existing item with the new data
            existingItem.setName(updatedItem.getName());
            existingItem.setDescription(updatedItem.getDescription());
            // Update other fields as needed

            return itemRepository.save(existingItem);
        }
        return null;
    }

    public boolean deleteItem(Long id) {
        Item existingItem = getItemById(id);
        if (existingItem != null) {
            itemRepository.delete(existingItem);
            return true;
        }
        return false;
    }

    public String determineWinner(Long itemId) {
        Item item = getItemById(itemId);
        if (item != null && item.getBids() != null && !item.getBids().isEmpty()) {
            // Sort the bids in descending order based on the amount
            List<Bid> bids = item.getBids();
            bids.sort(Comparator.comparing(Bid::getAmount).reversed());

            // The highest bid will be the winner
            Bid highestBid = bids.get(0);
            User winner = highestBid.getUser();

            // Return the winner's name or any identifier that represents the winner
            return winner.getUsername();
        }

        // No bids or item not found, return null or appropriate message
        return null;
    }
}

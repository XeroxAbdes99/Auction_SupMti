package com.supauction.auction_supmti.services;

import com.supauction.auction_supmti.Repository.NotificationsRepository;
import com.supauction.auction_supmti.model.Notifications;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationsRepository notificationRepository;

    public NotificationService(NotificationsRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notifications> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notifications getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notifications createNotification(Notifications notification) {
        return notificationRepository.save(notification);
    }

    public Notifications updateNotification(Long id, Notifications updatedNotification) {
        Notifications existingNotification = getNotificationById(id);
        if (existingNotification != null) {
            // Update the existing notification with the new data
            existingNotification.setCreatedAt(updatedNotification.getCreatedAt());
            existingNotification.setMessage(updatedNotification.getMessage());
            // Update other fields as needed

            return notificationRepository.save(existingNotification);
        }
        return null;
    }

    public boolean deleteNotification(Long id) {
        Notifications existingNotification = getNotificationById(id);
        if (existingNotification != null) {
            notificationRepository.delete(existingNotification);
            return true;
        }
        return false;
    }
}
package com.ftn.sbnz.service.services.menuItem;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.service.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteMenuItem {
    private final GetMenuItemById getMenuItemById;
    private final MenuItemRepository menuItemRepository;


    public void execute(UUID id) {
        MenuItem menuItem = getMenuItemById.execute(id);
        menuItemRepository.delete(menuItem);
    }
}

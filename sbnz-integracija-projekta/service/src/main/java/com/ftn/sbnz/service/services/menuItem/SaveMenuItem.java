package com.ftn.sbnz.service.services.menuItem;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.service.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveMenuItem {
    private final MenuItemRepository menuItemRepository;

    public MenuItem execute(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
}

package com.ftn.sbnz.service.services.menuItem;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.service.exception.MenuItemNotFoundException;
import com.ftn.sbnz.service.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMenuItemById {
    private final MenuItemRepository menuItemRepository;

    @Transactional(readOnly = true)
    public MenuItem execute(UUID id) {
        return menuItemRepository.findById(id).orElseThrow(MenuItemNotFoundException::new);
    }
}

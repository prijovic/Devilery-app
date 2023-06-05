package com.ftn.sbnz.service.services.menuItem;

import com.ftn.sbnz.model.models.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMenuItemsById {
    private final GetMenuItemById getMenuItemById;

    public List<MenuItem> execute(List<UUID> ids) {
        List<MenuItem> menuItems = new ArrayList<>();
        for (UUID id : ids) {
            menuItems.add(getMenuItemById.execute(id));
        }
        return menuItems;
    }
}

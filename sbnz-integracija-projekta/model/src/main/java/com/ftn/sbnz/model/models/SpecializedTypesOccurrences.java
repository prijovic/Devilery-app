package com.ftn.sbnz.model.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpecializedTypesOccurrences {
    private Restaurant restaurant;
    private List<Map.Entry<MenuItemType, Integer>> occurrences;
}

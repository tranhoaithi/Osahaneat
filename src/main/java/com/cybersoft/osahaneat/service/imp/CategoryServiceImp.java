package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.CategoryDTO;


import java.util.List;
import java.util.Set;

public interface CategoryServiceImp {
    boolean addCategory(String categoryName);
    List<CategoryDTO> getAllCategories();
}

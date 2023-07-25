package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.vo.SetmealVO;

public interface SetmealDishService {

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void save(SetmealDTO setmealDTO);

    /**
     * 根据id查询套餐
     * @param id
     */
    SetmealVO getById(Long id);

}

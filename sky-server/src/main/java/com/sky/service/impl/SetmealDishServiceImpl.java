package com.sky.service.impl;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealDishService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealDishServiceImpl implements SetmealDishService {

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * 新增套餐
     * @param setmealDTO
     */
    public void save(SetmealDTO setmealDTO) {

        Setmeal setmeal = new Setmeal();
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        setmealMapper.insert(setmeal);

        Long setmealId = setmeal.getId();

        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });

        setmealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    public SetmealVO getById(Long id) {

        SetmealVO setmealVO = new SetmealVO();

        Setmeal setmeal = setmealMapper.getById(id);
        BeanUtils.copyProperties(setmeal, setmealVO);

        List<SetmealDish> setmealDishes = setmealDishMapper.getById(id);
        setmealVO.setSetmealDishes(setmealDishes);

        return setmealVO;
    }

}

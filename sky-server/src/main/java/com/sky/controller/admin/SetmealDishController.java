package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.result.Result;
import com.sky.service.SetmealDishService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "套餐相关接口")
@RequestMapping("/admin/setmeal")
public class SetmealDishController {

    @Autowired
    private SetmealDishService setmealDishService;

    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO){

        log.info("新增套餐:{}",setmealDTO);
        setmealDishService.save(setmealDTO);

        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id){

        log.info("根据id查询套餐:{}", id);
        SetmealVO setmealVO = setmealDishService.getById(id);
        return Result.success(setmealVO);
    }
}

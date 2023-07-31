package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealDishService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "套餐相关接口")
@RequestMapping("/admin/setmeal")
public class SetmealDishController {

    @Autowired
    private SetmealDishService setmealDishService;

    @PostMapping
    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
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

    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){

        log.info("套餐分页查询:{}", setmealPageQueryDTO);

        PageResult pageResult = setmealDishService.pageQuery(setmealPageQueryDTO);

        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("批量删除套餐")
    @CacheEvict(cacheNames = "setmealName", allEntries = true)
    public Result deleteBatch(@RequestParam List<Long> ids){

        log.info("批量删除套餐:{}", ids);
        setmealDishService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改套餐")
    @CacheEvict(cacheNames = "setmealName", allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO){

        log.info("修改套餐:{}", setmealDTO);
        setmealDishService.update(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售停售")
    @CacheEvict(cacheNames = "setmealName", allEntries = true)
    public Result startOrStop(@PathVariable Integer status, Long id){

        log.info("套餐起售停售:{},{}", status,id);
        setmealDishService.startOrstop(status, id);
        return Result.success();
    }
}

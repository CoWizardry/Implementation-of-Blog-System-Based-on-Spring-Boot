package com.site.blog.my.core.controller.admin;

import com.site.blog.my.core.service.CategoryService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.Result;
import com.site.blog.my.core.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request) {
        request.setAttribute("path", "categories");
        return "admin/category";
    }

    /**
     * 分类列表
     */
//    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    @GetMapping("/categories/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(categoryService.getBlogCategoryPage(pageUtil));
    }

    /**
     * 分类添加
     */
//    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @PostMapping("/categories/save")
    @ResponseBody
    public Result save(@RequestParam("categoryName") String categoryName,
                       @RequestParam("categoryIcon") String categoryIcon) {
        if (!StringUtils.hasText(categoryName)) {
            return ResultGenerator.genFailResult("请输入分类名称！");
        }
        if (!StringUtils.hasText(categoryIcon)) {
            return ResultGenerator.genFailResult("请选择分类图标！");
        }
        int saveResult = categoryService.saveCategory(categoryName, categoryIcon);
        if (saveResult == 1) {
            return ResultGenerator.genSuccessResult();
        } else if(saveResult == 0){
            return ResultGenerator.genFailResult("保存失败");
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }


    /**
     * 分类修改
     */
//    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    @PostMapping("/categories/update")
    @ResponseBody
    public Result update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam("categoryIcon") String categoryIcon) {
        if (!StringUtils.hasText(categoryName)) {
            return ResultGenerator.genFailResult("请输入分类名称！");
        }
        if (!StringUtils.hasText(categoryIcon)) {
            return ResultGenerator.genFailResult("请选择分类图标！");
        }
        int updateResult = categoryService.updateCategory(categoryId, categoryName, categoryIcon);
        if (updateResult == 1) {
            return ResultGenerator.genSuccessResult();
        } else if(updateResult == 0){
            return ResultGenerator.genFailResult("更新失败");
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }


    /**
     * 分类删除
     */
//    @RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
    @PostMapping("/categories/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (categoryService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

}

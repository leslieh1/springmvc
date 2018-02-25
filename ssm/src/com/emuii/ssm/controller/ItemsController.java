package com.emuii.ssm.controller;

import com.emuii.ssm.controller.validation.ValidGroup2;
import com.emuii.ssm.po.ItemsCustom;
import com.emuii.ssm.po.ItemsQueryVo;
import com.emuii.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Create by Leslie on 2018\1\8 0008.<br>
 */
@Controller
// 定义url的根路径，访问时根路径+方法的url，窄化请求映射
@RequestMapping("/item")
public class ItemsController {

    // 注入Service
    @Autowired
    private ItemsService itemsService;


    // 重写controller里的initBinder方法，解决日期类型不一致问题
    /*@InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/
    // 两种都可以
    /*@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/

    // 查询所有商品
    // method 限制url请求的http方法
    @RequestMapping(value = "/queryItems" , method = RequestMethod.GET)
    public ModelAndView queryItems() throws Exception {

        // 调用Service查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);

        modelAndView.setViewName("itemsList");

        return modelAndView;
    }

    // 批量修改商品查询
    @RequestMapping(value = "/editItemsList" , method = RequestMethod.GET)
    public ModelAndView editItemsList() throws Exception {

        // 调用Service查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);

        modelAndView.setViewName("editItemsList");

        return modelAndView;
    }

    // 批量修改商品查询提交
    @RequestMapping("/editItemsListSubmit")
    public String editItemsListSubmit(ItemsQueryVo itemsQueryVo){

        return "success";
    }


    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] delete_id) throws Exception{

        // 调用Service删除
        // ....
        System.out.println(delete_id.length);
        return "success";
    }

    // RESTful接口根据id查询商品，返回json数据
    // @RequestMapping中指定restful方式中url的参数，参数需要用{}，括起来多个  /{name}
    @RequestMapping("/viewItems/{id}")
    public @ResponseBody ItemsCustom viewItems(@PathVariable Integer id) throws Exception{

        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        return itemsCustom;
    }

    // 修改商品
    /*@RequestMapping("/editItems")
    public ModelAndView editItems() throws Exception {

        // 修改商品先查询获得要修改的商品
        ItemsCustom itemsCustom = itemsService.findItemsById(1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item", itemsCustom);
        modelAndView.setViewName("editItem");

        return modelAndView;
    }*/

    @RequestMapping("/editItems")
    public String editItems(Model model, Integer id) throws Exception {

        model.addAttribute("id", id);

        // 修改商品先查询获得要修改的商品
        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        model.addAttribute("item", itemsCustom);

        return "editItem";
//        return "editItem2";
    }
    /*@RequestMapping("/editItems")
    public void editItems(HttpServletRequest request,
                          HttpServletResponse response,
                          *//*@RequestParam(value = "item_id",required = false,defaultValue = "1") Integer id*//*
                          Integer id ) throws Exception {

        // 修改商品先查询获得要修改的商品
        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        request.setAttribute("item", itemsCustom);
        request.getRequestDispatcher("/WEB-INF/jsp/editItem2.jsp").forward(request, response);
    }*/

    @RequestMapping("/editItemSubmit")
    public String editItemSubmit(Model model, Integer id,
                                 // 在要校验的pojo前加上@Validated
                                 @Validated(value = ValidGroup2.class) @ModelAttribute(value = "item") ItemsCustom itemsCustom,
                                 // 每个pojo要校验的pojo后面要加BindingResult接收错误信息
                                 BindingResult bindingResult,
                                 ItemsQueryVo itemsQueryVo,
                                 // 上传图片
                                 MultipartFile pictureFile) throws Exception{
        // 输出校验错误的信息
        if(bindingResult.hasErrors()){
            // 获取错误
            List<ObjectError> errors = bindingResult.getAllErrors();
            model.addAttribute("errors", errors);
            for(ObjectError error:errors){
                System.out.println(error.getDefaultMessage());
            }
        }

        // 进行id数据回显
        model.addAttribute("id", id);
//        model.addAttribute("item", itemsCustom);
        //进行图片上传
        if(pictureFile!=null && pictureFile.getOriginalFilename()!=null && pictureFile.getOriginalFilename().length()>0){
            //图片上传成功后，将图片的地址写到数据库
            String filePath = "D:\\IdeaProjects\\pic\\";
            //上传文件原始名称
            String originalFilename = pictureFile.getOriginalFilename();
            //新的图片名称
            String newFileName = UUID.randomUUID() +originalFilename.substring(originalFilename.lastIndexOf("."));
            //新文件
            File file = new java.io.File(filePath+newFileName);

            //将内存中的文件<!-- 错误信息 -->
            //<c:forEach items="${errors }" var="error">
            pictureFile.transferTo(file);
            // ${error.defaultMessage }<br 写入磁盘

            //图片上传成功，将新图片地址写入数据库
            itemsCustom.setPic(newFileName);
        }

        itemsService.updateItems(id, itemsCustom);

        return "editItem";
        // 转发
//        return "redirect:queryItems.action";
        // 请求
//        return "forward:queryItems.action";
    }
}

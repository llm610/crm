package com.powernode.controller;

import com.powernode.beans.Type;
import com.powernode.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    /*@RequestMapping("indexView")
    public String indexView(){

        return "settings/dictionary/type/index";

    }*/

    @RequestMapping("indexView")
    public String showView(Model model){
        List<Type> list = typeService.getAll();
        model.addAttribute("tList",list);
        return "settings/dictionary/type/index";
    }

    @RequestMapping("saveView")
    public String saveViewCo(){
        return "settings/dictionary/type/save";
    }

    @RequestMapping("save.do")
    public String saveView(Type type){
        typeService.insert(type);
        return "redirect:/type/indexView";
    }

    @RequestMapping("checkCode.do")
    @ResponseBody
    public boolean saveView(String code){
        Type type = typeService.getById(code);

        return type==null?false:true;
    }

    //前端传回的数值名和后端需要的命名需要一致
    @RequestMapping("editView")
    public String edit(String code,Model model){
        Type type = typeService.getById(code);
        model.addAttribute("type",type);
        return "settings/dictionary/type/edit";
    }

    @RequestMapping("edit.do")
    public String editDo(Type type){
        typeService.update(type);

        return "redirect:/type/indexView";
    }

    @RequestMapping("deleteView")
    public String delete(String[] ids){
        typeService.delete(ids);
        return "redirect:/type/indexView";
    }
}

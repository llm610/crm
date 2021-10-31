package com.powernode.controller;

import com.powernode.beans.Type;
import com.powernode.beans.Value;
import com.powernode.service.TypeService;
import com.powernode.service.ValueService;
import com.powernode.tools.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/value")
public class ValueController {

    @Autowired
     private ValueService valueService;

    @Autowired
    private TypeService typeService;

    @RequestMapping("valueView")
    public String valueView(Model model){
        List<Value> list = valueService.getAll();
        model.addAttribute("vList",list);
        return "settings/dictionary/value/index";

    }

    @RequestMapping("saveView")
    public String saveView(Model model){
        List<Type> list = typeService.getAll();
        model.addAttribute("tList",list);
        return "settings/dictionary/value/save";
    }

    @RequestMapping("save.do")
    public String saveDo(Value value){
        String id = UUIDUtils.getUUID();
        value.setId(id);
        valueService.insert(value);
        return "redirect:/value/valueView";
    }


    @RequestMapping("editView")
    public String editView(String id,Model model){
        List<Type> list = typeService.getAll();
        Value value = valueService.getById(id);
        model.addAttribute("value",value);
        model.addAttribute("tList",list);
        return "settings/dictionary/value/edit";
    }

    @RequestMapping("update.do")
    public String updateDo(Value value){
        int update = valueService.update(value);
        return "redirect:/value/valueView";
    }


    @RequestMapping("deleteView")
    public String delete(String[] ids){
        valueService.delete(ids);
        return "redirect:/value/valueView";
    }


}

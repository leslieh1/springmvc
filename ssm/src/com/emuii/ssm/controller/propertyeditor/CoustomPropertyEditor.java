package com.emuii.ssm.controller.propertyeditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期属性编辑器
 * Create by Leslie on 2018\1\8 0008.<br>
 */
public class CoustomPropertyEditor implements PropertyEditorRegistrar{
    @Override
    public void registerCustomEditors(PropertyEditorRegistry binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

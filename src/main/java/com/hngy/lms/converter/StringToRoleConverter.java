package com.hngy.lms.converter;

import com.hngy.lms.entity.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        Role role = null;
        for(Role r :Role.values()){
            if (r.name().equalsIgnoreCase(source)) {
                role = r;
                break;
            }
        }
        return role;
    }
}

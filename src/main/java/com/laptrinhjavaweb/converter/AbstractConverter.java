package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;


/**
 * Converter support class. Allows for simpler Converter implementations.
 *
 * @param <DTO>    source type
 * @param <Entity> destination type
 * @author le quang nhu
 */
@Component// nó hiểu đây là 1 module. và bảo IoC container tạo một object duy nhất (singleton)
public abstract class AbstractConverter<DTO, Entity> {

    @Autowired
    protected ModelMapper modelMapper;

    /**
     * Converts {@code source} to an instance of type {@code D}.
     */
    public final <D, S> D convertSpecial(S source, D dest) {
        //get Type of class
        String className = dest.getClass().getTypeName();
        try {
            Class<?> clazz = Class.forName(className);

            //convert
            dest = this.modelMapper.map(source, (Type) clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dest;
    }

    public abstract DTO convertEntityToDTO(Entity entity);

    public abstract Entity convertDTOToEntity(DTO dto);
}

package com.laptrinh.carparkex.Mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ConfigMapperModel {
    private static ModelMapper modelMapper;
    public static ModelMapper getMapper(){
        if(modelMapper == null){
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        }
        return modelMapper;
    }
}

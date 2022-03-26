package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.example.demo.vo.Student;

@Component
@Mapper(componentModel = "spring")
public interface StrudentMapper {

	@Mappings({ @Mapping(source = "name", target = "name"), @Mapping(source = "id", target = "id") })
	Student toVo(com.example.demo.entity.Student student);

	@Mappings({ @Mapping(source = "name", target = "name"), @Mapping(source = "id", target = "id") })
	com.example.demo.entity.Student toEntityStudent(Student student);
}

package br.com.eco.userservice.mappers;

import br.com.eco.userservice.domains.User;
import br.com.eco.userservice.to.UserTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserTO fromUser(User user);
}

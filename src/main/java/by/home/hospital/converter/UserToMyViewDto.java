package by.home.hospital.converter;

import by.home.hospital.domain.User;
import by.home.hospital.dto.MyViewDto;
import by.home.hospital.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToMyViewDto implements Converter<String, MyViewDto> {
    @Autowired
    private UserService userService;

    @Override
    public MyViewDto convert(String source) {
        User user = this.userService.getUserByCredentialsEmail(source);
        MyViewDto myViewDto = new MyViewDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPosition().toString(),
                user.getCredentials().getEmail()
        );
        return myViewDto;
    }
}

package novice.present.domain.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Data
public class LoginForm {

    @NotBlank(message = "{login.form.id}")
    private String loginId;

    @NotBlank(message = "{login.form.password}")
    private String password;
}

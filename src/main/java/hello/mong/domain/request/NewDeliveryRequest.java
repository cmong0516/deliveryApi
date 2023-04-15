package hello.mong.domain.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewDeliveryRequest {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}

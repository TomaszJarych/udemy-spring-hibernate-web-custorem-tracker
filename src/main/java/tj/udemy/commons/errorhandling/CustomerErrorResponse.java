package tj.udemy.commons.errorhandling;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerErrorResponse {

    private int code;
    private String message;
    private Long timseStamp;
}

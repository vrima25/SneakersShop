package com.enigma.ecommerce.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter @Setter
@NoArgsConstructor
public class SuccessResponse<T> extends CommonResponse {
    private T data;

    public SuccessResponse(String message, T data) {
        super.setCode("200");
        super.setStatus(HttpStatus.OK.name());
        super.setMessage(message);
        this.data = data;
    }
}

package com.dcl.accommodate.shared;

import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private String error;

}

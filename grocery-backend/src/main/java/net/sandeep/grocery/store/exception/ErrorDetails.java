package net.sandeep.grocery.store.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime localDateTime;
    private String message;
    private String url;
}

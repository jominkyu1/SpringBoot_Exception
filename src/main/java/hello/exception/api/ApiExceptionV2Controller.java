package hello.exception.api;

import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class ApiExceptionV2Controller {

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {

        switch (id) {
            case "ex":
                throw new RuntimeException("My Runtime");
            case "bad":
                throw new IllegalArgumentException("My IllegalArgument");
            case "user-ex":
                throw new UserException("My UserException");
        }

        return new MemberDto(id, "hello" + id);
    }


    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
